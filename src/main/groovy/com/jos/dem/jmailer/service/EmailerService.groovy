package com.jos.dem.jmailer.service

import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import com.jos.dem.jmailer.exception.EmailerException

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class EmailerService {

  Log log = LogFactory.getLog(this.class)

	String getDesc() {
		log.debug "getDesc() is executed!"
	  "Jmailer home"
	}

	String getTitle(String name) {
		log.debug "getTitle() is executed! name : ${name}"
    "Welcome ${name}"
	}

  def sendEmail(){
    log.debug 'Sending email'
    throw new EmailerException()
  }

}
