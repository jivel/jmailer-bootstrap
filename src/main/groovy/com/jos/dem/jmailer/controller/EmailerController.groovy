package com.jos.dem.jmailer.controller

import java.util.Map

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

import static org.springframework.web.bind.annotation.RequestMethod.GET

import com.jos.dem.jmailer.service.EmailerService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Controller
class EmailerController {

  @Autowired
  EmailerService emailerService

  Log log = LogFactory.getLog(this.class)

  @RequestMapping(value = '/', method = GET)
  String index(Map<String, Object> model) {
    log.debug 'Calling index'
    model.put('title', emailerService.getTitle('World!'))
    model.put('msg', emailerService.getDescription())

    return 'index'
  }
}

