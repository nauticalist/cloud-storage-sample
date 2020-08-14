package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value = "/credentials")
public class CredentialController {
    private final CredentialService credentialService;

    @Autowired
    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping()
    public String saveCredential(@ModelAttribute(name = "credential") Credential credential, Model model, Principal principal) {
        String errorMessage = null;
        if(credential.getCredentialId() == null) {
            try {
                credentialService.createCredential(credential, principal.getName());
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        } else {
            try {
                credentialService.updateCredential(credential);
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
        }
        if (errorMessage != null)
            model.addAttribute("errorMessage", errorMessage);
        else
            model.addAttribute("successMessage", "Your credential were successfully saved. Click <a id=\"success-link\" href=\"/#nav-credentials\">here</a> to continue.");
        return "result";
    }

    @GetMapping(value = "/delete/{credentialId}")
    public String deleteCredential(@PathVariable("credentialId") Integer credentialId, Model model) {
        String errorMessage = null;

        try {
            credentialService.deleteCredential(credentialId);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        if(errorMessage == null)
            model.addAttribute("successMessage", "Your credential were successfully deleted. Click <a id=\"success-link\" href=\"/#nav-credentials\">here</a> to continue.");
        else
            model.addAttribute("errorMessage",errorMessage);

        return "result";
    }
}
