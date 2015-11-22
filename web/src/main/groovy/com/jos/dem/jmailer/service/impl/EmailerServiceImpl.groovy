package com.jos.dem.jmailer.service.impl

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.service.MessageService
import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.exception.EmailerException

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class EmailerServiceImpl implements EmailerService {

  @Autowired
  MessageService messageService

  Log log = LogFactory.getLog(this.class)

	String getTitle(String name) {
    log.debug "GETTING title with name : ${name}"
    "Hello ${name}"
	}

  String getDescription() {
    log.debug "GETTING description"
    "Jmailer is a service for delivering emails"
	}

  def sendEmail(MessageCommand command){
    log.info 'Sending email ${command.email}'
    messageService.message(command)
  }

}
