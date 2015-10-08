package com.jos.dem.helloworld.web

import java.util.Map

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

import com.jos.dem.helloworld.service.HelloWorldService

@Controller
class WelcomeController {

  Logger log = LoggerFactory.getLogger(this.class)

  @Autowired
  HelloWorldService helloWorldService

	@RequestMapping(value = '/', method = RequestMethod.GET)
	String index(Map<String, Object> model) {
		log.debug 'index() is executed!'

		model.put('title', helloWorldService.getTitle('World'))
		model.put('msg', helloWorldService.getDesc())

		return 'index'
	}

}
