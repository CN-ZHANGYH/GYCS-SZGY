package com.ruoyi.charity.service.impl.email;

import com.ruoyi.charity.domain.dto.EmailMessageDto;
import com.ruoyi.charity.service.email.MailService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.math.BigInteger;

@Service
public class MailServiceImpl implements MailService {
    /**
     * Java邮件发送器
     */
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String USERNAME;


    @SneakyThrows
    @Override
    public void sendAuditEmailMessage(EmailMessageDto emailMessageDto) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //邮箱发送者
        helper.setFrom(USERNAME);
        //收件人，可以为多个收件人，收件人之间用逗号隔开
        helper.setTo(emailMessageDto.getAccount());
        // 邮箱标题
        helper.setSubject("GivTech公益募资活动审核通知");
        String title = emailMessageDto.getTitle();
        String userAddress = emailMessageDto.getUserAddress();
        Integer status = emailMessageDto.getStatus();
        String content = null;
        if (status == 2) {
            content = "<!DOCTYPE html>\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<title>审核通过通知</title>\n" +
                    "<style>\n" +
                    "    /* 全局样式 */\n" +
                    "    body {\n" +
                    "        font-family: Arial, sans-serif;\n" +
                    "        background-color: #f0f0f0;\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "    }\n" +
                    "\n" +
                    "    /* 内联样式 */\n" +
                    "    .container {\n" +
                    "        max-width: 600px;\n" +
                    "        margin: 50px auto;\n" +
                    "        background-color: #fff;\n" +
                    "        border-radius: 10px;\n" +
                    "        padding: 30px;\n" +
                    "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .header {\n" +
                    "        text-align: center;\n" +
                    "        margin-bottom: 30px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .header h1 {\n" +
                    "        color: #333;\n" +
                    "        font-size: 32px;\n" +
                    "        font-weight: bold;\n" +
                    "        text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .content {\n" +
                    "        text-align: center;\n" +
                    "        padding: 20px;\n" +
                    "        background-color: #f9f9f9;\n" +
                    "        border-radius: 5px;\n" +
                    "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .content p {\n" +
                    "        color: #666;\n" +
                    "        margin-bottom: 15px;\n" +
                    "        line-height: 1.6;\n" +
                    "    }\n" +
                    "\n" +
                    "    .footer {\n" +
                    "        text-align: center;\n" +
                    "        margin-top: 30px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .footer p {\n" +
                    "        color: #888;\n" +
                    "        font-style: italic;\n" +
                    "        font-size: 14px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .success-icon {\n" +
                    "        margin-left: 45%;\n" +
                    "        display: flex;\n" +
                    "        justify-content: center;\n" +
                    "        align-items: center;\n" +
                    "        width: 50px;\n" +
                    "        height: 50px;\n" +
                    "        background-color: #4CAF50;\n" +
                    "        color: #fff;\n" +
                    "        border-radius: 50%;\n" +
                    "        font-size: 24px;\n" +
                    "        margin-bottom: 20px;\n" +
                    "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .success-icon::after {\n" +
                    "        content: \"\\2713\";\n" +
                    "    }\n" +
                    "\n" +
                    "    @media screen and (max-width: 480px) {\n" +
                    "        /* 在小屏幕设备上的样式 */\n" +
                    "        .container {\n" +
                    "            width: 100%;\n" +
                    "            border-radius: 0;\n" +
                    "        }\n" +
                    "        .header h1 {\n" +
                    "            font-size: 28px;\n" +
                    "        }\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">\n" +
                    "        <h1>审核通过通知</h1>\n" +
                    "    </div>\n" +
                    "    <div class=\"content\">\n" +
                    "        <div class=\"success-icon\"></div>\n" +
                    "        <p>尊敬的用户，您好，您当前发起的公益募资活动区块链账户地址为 <strong>" + userAddress +"</strong>，</p>\n" +
                    "        <p>您的募资活动标题为 <strong>" + title + "</strong> 已通过审核。</p>\n" +
                    "        <p>我们将尽快为您处理，请耐心等待。</p>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <p>如有任何疑问，请联系我们。</p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";
        }else {
            content = "<!DOCTYPE html>\n" +
                    "<html lang=\"zh-CN\">\n" +
                    "<head>\n" +
                    "<meta charset=\"UTF-8\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<title>审核未通过通知</title>\n" +
                    "<style>\n" +
                    "    /* 全局样式 */\n" +
                    "    body {\n" +
                    "        font-family: Arial, sans-serif;\n" +
                    "        background-color: #f0f0f0;\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "    }\n" +
                    "\n" +
                    "    /* 内联样式 */\n" +
                    "    .container {\n" +
                    "        max-width: 600px;\n" +
                    "        margin: 50px auto;\n" +
                    "        background-color: #fff;\n" +
                    "        border-radius: 10px;\n" +
                    "        padding: 30px;\n" +
                    "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .header {\n" +
                    "        text-align: center;\n" +
                    "        margin-bottom: 30px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .header h1 {\n" +
                    "        color: #333;\n" +
                    "        font-size: 32px;\n" +
                    "        font-weight: bold;\n" +
                    "        text-shadow: 2px 2px 2px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .content {\n" +
                    "        text-align: center;\n" +
                    "        padding: 20px;\n" +
                    "        background-color: #f9f9f9;\n" +
                    "        border-radius: 5px;\n" +
                    "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .content p {\n" +
                    "        color: #666;\n" +
                    "        margin-bottom: 15px;\n" +
                    "        line-height: 1.6;\n" +
                    "    }\n" +
                    "\n" +
                    "    .footer {\n" +
                    "        text-align: center;\n" +
                    "        margin-top: 30px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .footer p {\n" +
                    "        color: #888;\n" +
                    "        font-style: italic;\n" +
                    "        font-size: 14px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .failure-icon {\n" +
                    "        display: flex;\n" +
                    "        margin-left: 45%;\n" +
                    "        justify-content: center;\n" +
                    "        align-items: center;\n" +
                    "        width: 50px;\n" +
                    "        height: 50px;\n" +
                    "        background-color: #F44336;\n" +
                    "        color: #fff;\n" +
                    "        border-radius: 50%;\n" +
                    "        font-size: 24px;\n" +
                    "        margin-bottom: 20px;\n" +
                    "        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                    "    }\n" +
                    "\n" +
                    "    .failure-icon::after {\n" +
                    "        content: \"X\";\n" +
                    "    }\n" +
                    "\n" +
                    "    @media screen and (max-width: 480px) {\n" +
                    "        /* 在小屏幕设备上的样式 */\n" +
                    "        .container {\n" +
                    "            width: 100%;\n" +
                    "            border-radius: 0;\n" +
                    "        }\n" +
                    "        .header h1 {\n" +
                    "            font-size: 28px;\n" +
                    "        }\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">\n" +
                    "        <h1>审核未通过通知</h1>\n" +
                    "    </div>\n" +
                    "    <div class=\"content\">\n" +
                    "        <div class=\"failure-icon\"></div>\n" +
                    "        <p>尊敬的用户，您好，您当前发起的公益募资活动区块链账户地址为 <strong>" + userAddress + "</strong>，</p>\n" +
                    "        <p>您的募资活动标题为 <strong>"+ title +"</strong> 未通过审核。</p>\n" +
                    "        <p>请检查您的订单信息，并进行必要的修正后重新提交。</p>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <p>如有任何疑问，请联系我们。</p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";
        }

        helper.setText(content, true);
        mailSender.send(message);

    }
}
