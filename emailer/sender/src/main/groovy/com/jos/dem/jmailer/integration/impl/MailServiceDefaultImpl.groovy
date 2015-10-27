package com.jos.dem.jmailer.integration.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Service
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils

import com.jos.dem.jmailer.integration.MailService
import com.jos.dem.jmailer.constant.ApplicationConstants

import freemarker.template.Configuration
import freemarker.template.Template
import javax.mail.internet.MimeMessage

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class MailServiceDefaultImpl implements MailService {

  @Autowired
  Configuration configuration
  @Autowired
  JavaMailSender javaMailSender
  @Autowired
  Properties properties

  Log log = LogFactory.getLog(getClass())

  void sendMailWithEngine(final String email, final Map model, final String subject, final String template) {
    MimeMessagePreparator preparator = new MimeMessagePreparator() {
      void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true)
        Template myTemplate = configuration.getTemplate(template)
        message.setTo(email)
        message.setFrom(properties.getProperty(ApplicationConstants.SENDER))
        message.setSubject(subject)
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(myTemplate, model)
        message.setText(text, true)
      }
    }
    this.javaMailSender.send(preparator)
  }

}
