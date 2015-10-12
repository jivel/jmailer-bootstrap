package com.jos.dem.jmailer.controller

import java.util.Map

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.servlet.ModelAndView
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.google.gson.Gson

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import com.jos.dem.jmailer.service.EmailerService
import com.jos.dem.jmailer.command.MessageCommand
import com.jos.dem.jmailer.exception.BusinessException

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Controller
class EmailerController {

  @Autowired
  EmailerService emailerService

  Log log = LogFactory.getLog(this.class)

  @RequestMapping(value = '/', method = GET)
  String index(Map<String, Object> model) {
    log.debug 'index() is executed!'
    model.put('title', emailerService.getTitle('Dear!'))
    model.put('msg', emailerService.getDesc())

    return 'index'
  }

  @RequestMapping(method = POST, value = "/message")
  @ResponseBody
  ResponseEntity<String> message(@RequestBody String json) {
    log.debug "json: ${json}"
    MessageCommand command = new Gson().fromJson(json, MessageCommand.class);
    log.info "Sending contact email: ${command.dump()}"
    try{
      emailerService.sendEmail()
      return new ResponseEntity<String>("OK", HttpStatus.OK);
    }catch (BusinessException be){
      return new ResponseEntity<String>(be.message, HttpStatus.BAD_REQUEST);
    }
  }

}
