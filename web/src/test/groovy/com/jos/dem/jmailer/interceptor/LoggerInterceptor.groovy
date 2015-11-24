package com.jos.dem.jmailer.interceptor

import spock.lang.Specification

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import com.jos.dem.jmailer.PropertiesMock
import com.jos.dem.jmailer.service.LoggerService
import com.jos.dem.jmailer.constant.ApplicationConstants

class LoggerInterceptorSpec extends Specification {

  def interceptor = new LoggerInterceptor()

  def request = Mock(HttpServletRequest)
  def response = Mock(HttpServletResponse)
  def loggerService = Mock(LoggerService)

  def setup(){
    def map = ['white.list':'127.0.0.1']
    def properties = new PropertiesMock(map)
    interceptor.properties = properties
    interceptor.loggerService = loggerService
    interceptor.setup()
  }

  void "should accept localhost"(){
    given:"An ip address"
      String localhost = '127.0.0.1'
    when:"We preHandle request"
     def result = interceptor.preHandle(request, response, new Object())
    then:"We expect access"
     result
  }
}

