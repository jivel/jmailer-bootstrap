package com.jos.dem.jmailer.web

import java.util.Map

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

import com.jos.dem.jmailer.service.EmailerService

@Controller
class EmailerController {

  Logger log = LoggerFactory.getLogger(this.class)

  @Autowired
  EmailerService emailerService

	@RequestMapping(value = '/', method = RequestMethod.GET)
	String index(Map<String, Object> model) {
		log.debug 'index() is executed!'

		model.put('title', emailerService.getTitle('World'))
		model.put('msg', emailerService.getDesc())

		return 'index'
	}

}
