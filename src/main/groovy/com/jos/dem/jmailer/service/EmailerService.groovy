package com.jos.dem.jmailer.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class EmailerService {

	Logger log = LoggerFactory.getLogger(this.class)

	String getDesc() {
		log.debug "getDesc() is executed!"
	  "Gradle + Spring MVC"
	}

	String getTitle(String name) {
		log.debug "getTitle() is executed! name : ${name}"
    "Hello ${name}"
	}

}
