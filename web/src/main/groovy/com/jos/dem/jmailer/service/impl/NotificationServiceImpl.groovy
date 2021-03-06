package com.jos.dem.jmailer.service.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

import com.jos.dem.jmailer.integration.MailService
import com.jos.dem.jmailer.service.NotificationService
import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.constant.ApplicationConstants

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class NotificationServiceImpl implements NotificationService {

  @Autowired
  MailService mailService
  @Autowired
  Properties properties

  private Log log = LogFactory.getLog(getClass())

  @Override
  Boolean sendNotification(MessageCommand messageCommand) {
    def (subject, templateName) = obtainSubjectAndResourceToSendNotification(messageCommand)
    def data = [email:messageCommand.email, subject:subject, templateName:templateName]
    mailService.sendMailWithTemplate(data, messageCommand.properties, data.templateName)
  }

  def obtainSubjectAndResourceToSendNotification(MessageCommand messageCommand){
    String templateKey = "${messageCommand.type.toString()}_PATH"
    println templateKey
    String subjectKey = "${messageCommand.type.toString()}_SUBJECT"

    String templateName = properties.getProperty(templateKey)
    println templateName
    String subject = properties.getProperty(subjectKey)

    log.info "Sending email with subject: ${subject}"
    [subject, templateName]
  }

}
