/**
 * @Package com.itcast.utils
 * @author 小白
 * @date 2020/4/13 0013 20:03
 * @version V1.0
 * @Copyright © 2018-2019 中金慈云健康科技（北京）有限公司
 */
package com.itcast.utils;

import com.itcast.domain.User;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 〈发送邮件工具类〉
 *
 * @author xiaobai
 * @create 2020/4/13 0013
 * @since 1.0.0
 */
public class MailUtils implements Runnable {
    // 初始化用户
    private User user;
    private String emailHead;
    private String emailMsg;

    /**
     * @param user      注册用户的信息，包含接收人信息（邮箱）
     * @param emailHead 邮件主题
     * @param emailMsg  邮件内容
     */
    public MailUtils(User user, String emailHead, String emailMsg) throws IOException {
        this.user = user;
        this.emailHead = emailHead;
        this.emailMsg = emailMsg;
    }

    @Override
    public void run() {
        try {
            // 创建Properties 配置文件对象
            Properties properties = properties = new Properties();
            // 将配置文件信息读入输入流中
            InputStream inputStream = MailUtils.class.getClassLoader().getResourceAsStream("email.properties");
            // properties 对象加载 输入流信息
            properties.load(inputStream);

            // 获取properties中的参数
            String mainUsername = properties.getProperty("mail.username");
            String mailAuthorizationCode = properties.getProperty("mail.authorizationCode");
            String mailHost = properties.getProperty("mail.host");

            // 创建一个 session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                // 验证器
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mainUsername, mailAuthorizationCode);
                }
            });

            // 开启debug模式
            session.getDebug();
            // 获取连接对象
            Transport transport = session.getTransport();
            // 连接服务器
            transport.connect(mailHost, mainUsername, mailAuthorizationCode);

            // 创建邮件对象（相当于邮件整体内容）
            MimeMessage mimeMessage = new MimeMessage(session);
            // 邮件发送人
            mimeMessage.setFrom(new InternetAddress(mainUsername));
            // 设置发送方式及收件人
            mimeMessage.setRecipients(Message.RecipientType.TO, user.getEmail());
            //设置邮件主题
            mimeMessage.setSubject(emailHead);
            // 设置邮件主体内容,及编码格式
            mimeMessage.setContent(emailMsg, "text/html;charset=utf-8");
            // 发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            // 关闭连接
            transport.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
