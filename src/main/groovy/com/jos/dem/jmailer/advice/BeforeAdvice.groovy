package com.jos.dem.jmailer.advice

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Aspect
@Component
class BeforeAdvice {

  Log log = LogFactory.getLog(this.class)

  @Before("execution(* message(..))")
  def beforeMethod() {
    log.info 'Before method advice'
    println "*************************************"
  }

}


