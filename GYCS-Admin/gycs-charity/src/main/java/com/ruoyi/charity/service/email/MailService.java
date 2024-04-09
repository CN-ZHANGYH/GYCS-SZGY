package com.ruoyi.charity.service.email;

import com.ruoyi.charity.domain.dto.EmailMessageDto;

public interface MailService {


    /**
     *
     * @param emailMessageDto 发送邮件的信息
     */
    void sendAuditEmailMessage(EmailMessageDto emailMessageDto);
}
