package com.ruoyi.web.controller.charity.backend;


import com.ruoyi.charity.service.email.MailService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private MailService mailService;


    @PostMapping("/sendCode")
    public AjaxResult sendEmailCode(@RequestParam("email") String email) throws MessagingException {
        return mailService.sendEmailCode(email);
    }

}
