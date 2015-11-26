package com.jos.dem.jmailer.service

import spock.lang.Specification

import org.springframework.test.context.ContextConfiguration
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.command.MessageType

@ContextConfiguration(locations = ["classpath:/mail-appctx.xml", "classpath:/jms-appctx.xml", "classpath:/test-appctx.xml"])
class NotificationServiceSpec extends Specification {

  @Autowired
  NotificationService notificationService
  @Autowired
  Properties properties

  String email = 'joseluis.delacruz@gmail.com'

  void "should send an email"(){
    given:"A MessageCommand"
     def messageCommand = new MessageCommand(email:email, message:'Hello from spock', type:MessageType.MESSAGE)
    when:"We send notification"
     def result = notificationService.sendNotification(messageCommand)
    then:"We expect email sent"
     result
  }
}
