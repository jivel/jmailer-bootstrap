package com.jos.dem.jmailer.service.impl

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.jos.dem.jmailer.integration.MailService
import com.jos.dem.jmailer.service.NotificationService
import com.jos.dem.jmailer.command.MessageCommand

@Service
class NotificationServiceImpl implements NotificationService {

  @Autowired
  Properties emailProperties
  @Autowired
  MailService mailService

  private Log log = LogFactory.getLog(getClass())

  @Override
  void sendNotification(MessageCommand messageCommand) {
    log.info "messageCommand: ${messageCommand.dump()}"
    def (subject, templateName) = obtainSubjectAndResourceToSendNotification(messageCommand)
    def data = [subject:subject, templateName:templateName, bean:messageCommand]
    sendNotificationWithData(data)
  }

  private def obtainSubjectAndResourceToSendNotification(MessageCommand messageCommand){
    log.info "emailProperties: ${emailProperties.dump()}"
    String templateKey = "${messageCommand.type.toString()}_PATH"
    String subjectKey = "${messageCommand.type.toString()}_SUBJECT"

    String templateName = emailProperties.getProperty(templateKey)
    String subject = emailProperties.getProperty(subjectKey)

    log.info("Sending email with subject: " + subject)

    [subject, templateName]
  }

  private void sendNotificationWithData(emailData){
    String templateName = emailData.templateName
    def bean = emailData.bean
    String subject = emailData.subject
    mailService.sendMailWithEngine(bean.email, bean.properties, subject, templateName)
  }
}
