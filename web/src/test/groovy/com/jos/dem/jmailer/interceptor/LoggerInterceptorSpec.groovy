package com.jos.dem.jmailer.interceptor

import spock.lang.Specification

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.jos.dem.jmailer.service.LoggerService

class LoggerInterceptorSpec extends Specification {

  def interceptor = new LoggerInterceptor()

  def request = Mock(HttpServletRequest)
  def response = Mock(HttpServletResponse)
  def loggerService = Mock(LoggerService)
  def properties = new Properties()

  def setup(){
    properties.setProperty('white.list','127.0.0.1')
    interceptor.loggerService = loggerService
    interceptor.properties = properties
    interceptor.setup()
  }

  void "should accept localhost"(){
    given:"An ip address"
      String localhost = '127.0.0.1'
    when:"We preHandle request"
     request.remoteHost >> localhost
     def result = interceptor.preHandle(request, response, new Object())
    then:"We expect access"
     result
     1 * loggerService.notifyRequest(_ as Map)
  }

  void "should not accept strangers"(){
    given:"An ip address"
      String localhost = '127.0.0.1'
    when:"We preHandle request"
     request.remoteHost >> '189.217.63.188'
     def result = interceptor.preHandle(request, response, new Object())
    then:"We expect access denied"
     !result
     1 * loggerService.notifyRequest(_ as Map)
  }

}

