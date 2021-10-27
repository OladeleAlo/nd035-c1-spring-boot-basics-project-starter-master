package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {
    private CredentialsService credentialsService;
    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;

    public CredentialsController(CredentialsService credentialsService, UserService userService,EncryptionService encryptionService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
        this.encryptionService=encryptionService;
    }

    @PostMapping
    public String addCredentials(Credentials credentials, Authentication authentication, Model model){
        try {
            int userid=userService.getUser(authentication.getName()).getUserid();
            credentials.setUserid(userid);
            int count=0;

            if(credentials.getCredentialid()==0) {
                credentials.setKey("859B168BC6E10C17");
                credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(),credentials.getKey()));
                count=credentialsService.addCredentials(credentials);
                System.out.println("added cred");
            }else {
                System.out.println("upfate callinh");
                Credentials cred = credentialsService.getCredentials(credentials.getCredentialid());
                credentials.setKey(cred.getKey());
                credentials.setUsername(cred.getUsername());
                credentials.setPassword(encryptionService.encryptValue(credentials.getPassword(),credentials.getKey()));
                count = credentialsService.updateCredentials(credentials);
                System.out.println("updated cred");
            }

            if(count>0){
                model.addAttribute("success1",true);
                System.out.println("added success1 attr");

            }else {
                model.addAttribute("error",true);
                System.out.println("errye ayyr");
            }
        }
        catch(Exception e){
            model.addAttribute("success1", false);
        }
        // return "result";
        return  "redirect:/result?success";
    }
    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") String credentialId) {
        if (Integer.parseInt(credentialId) > 0) {
            credentialsService.deleteCredentials(Integer.parseInt(credentialId));
            return "redirect:/home";
        }
        return "redirect:/home";
    }

}
