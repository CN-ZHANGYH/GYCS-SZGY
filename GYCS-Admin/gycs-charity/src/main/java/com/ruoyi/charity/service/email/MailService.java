package com.ruoyi.charity.service.email;

import com.ruoyi.charity.domain.dto.EmailMessageDto;
import com.ruoyi.common.core.domain.AjaxResult;

import javax.mail.MessagingException;

public interface MailService {


    /**
     *
     * @param emailMessageDto 发送邮件的信息
     */
    void sendAuditEmailMessage(EmailMessageDto emailMessageDto);

    AjaxResult sendEmailCode(String email) throws MessagingException;
}
