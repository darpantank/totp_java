package com.example.totp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.totp.dto.LoginIncomingDto;
import com.example.totp.dto.SignUpIncomingDto;
import com.example.totp.model.Employee;
import com.example.totp.service.EmployeeService;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.util.Utils;

@Controller
@RequestMapping("/mfa")
public class MfaSetupController {

    private SecretGenerator secretGenerator = new DefaultSecretGenerator();

    @Autowired
    private QrDataFactory qrDataFactory;

    @Autowired
    private QrGenerator qrGenerator;
    
    @Autowired
    private CodeVerifier verifier;

    @Autowired
    private EmployeeService service;
    
    @GetMapping("/login")
    public String loginView(Model m) {
    	m.addAttribute("loginForm",new LoginIncomingDto());
    	return "login";
    }
    @PostMapping("/login")
    public String loginSubmit(Model m,LoginIncomingDto dto) {
    	int pointer=dto.getPassword().length()-6;
    	String otp=dto.getPassword().substring(pointer);
    	String password =dto.getPassword().substring(0,pointer);
    	dto.setPassword(password);
    	Optional<Employee> employee = service.validateEmployee(dto);
    	if(employee.isPresent()) {
    		if (verifier.isValidCode(employee.get().getSecretKey(), otp)) {
                return "success";
            }
    	}
    	return "notfound";
    }
    @GetMapping("/signup")
    public String loginForm(Model m) {
    	m.addAttribute("signupForm", new SignUpIncomingDto());
    	return "signupform";
    }
    @PostMapping("/signup")
    public String submitForm(@ModelAttribute SignUpIncomingDto dto,Model m) throws QrGenerationException {
    	String secretKey=secretGenerator.generate();
    	Employee emp=dto.DtoToEmployee();
    	emp.setSecretKey(secretKey);
    	emp=service.saveEmployeeData(emp);
    	if(emp.getEmployeeId()!=0) {
    		m.addAttribute("qr",qrGenerateMethod(emp.getSecretKey()));
    		return "showqr";
    	}
    	return "notfound";
    }
    
    private String qrGenerateMethod(String secretKey) throws QrGenerationException {
        QrData data = qrDataFactory.newBuilder()
            .label("DemoApplication")
            .secret(secretKey)
            .issuer("TatvaDemo")
            .build();

        // Generate the QR code image data as a base64 string which
        // can be used in an <img> tag:
        String qrCodeImage = Utils.getDataUriForImage(
          qrGenerator.generate(data), 
          qrGenerator.getImageMimeType()
        );
        return qrCodeImage;
    }
}
