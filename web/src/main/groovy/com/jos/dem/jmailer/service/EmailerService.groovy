package com.jos.dem.jmailer.service

import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

import com.jos.dem.integration.MessageService
import com.jos.dem.jmailer.exception.EmailerException

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class EmailerService {

  @Autowired
  MessageService messageDispatcher

  Log log = LogFactory.getLog(this.class)

	String getTitle(String name) {
    log.debug "GETTING title with name : ${name}"
    "Hello ${name}"
	}

  String getDescription() {
    log.debug "GETTING description"
    "Jmailer is a service for delivering emails"
	}

  def sendEmail(Command command){
    log.debug 'Sending email'
    messageDispatcher.message(command)
  }

}
