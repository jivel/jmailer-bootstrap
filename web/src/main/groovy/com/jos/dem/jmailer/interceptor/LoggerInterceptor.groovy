package com.jos.dem.jmailer.interceptor

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.annotation.PostConstruct

import com.jos.dem.jmailer.service.LoggerService
import com.jos.dem.jmailer.constant.ApplicationConstants

class LoggerInterceptor implements HandlerInterceptor {

  @Autowired
  LoggerService loggerService
  @Autowired
  Properties properties

  def whiteList = []

  @PostConstruct
  public void setup(){
    whiteList = properties.getProperty(ApplicationConstants.WHITE_LIST).tokenize(',')
  }

  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    def data = [:]
    data.remoteHost = request.remoteHost
    data.timeInMillis = System.currentTimeMillis()
    data.method = request.method
    data.requestURL = request.requestURL
    data.parameters = request.parameterMap

    if(!whiteList.contains(request.remoteHost)){
      data.warn = "UNAUTORIZED IP was detected in attempt to access to resource"
      loggerService.notifyRequest(data)
      return false;
    }

    loggerService.notifyRequest(data)
      return true
  }


  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
  }


  void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
  }

}
