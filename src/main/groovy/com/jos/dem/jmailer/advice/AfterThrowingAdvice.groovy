package com.jos.dem.jmailer.advice

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
@Component
class AfterThrowingAdvice {

  Logger log = LoggerFactory.getLogger(this.class)

  @AfterThrowing(pointcut="execution(* com.jos.dem.jmailer.controller.**.*(..))",throwing="customNameException")
  def afterReturningMethod(JoinPoint joinPoint, RuntimeException customNameException) {
    def buffer = "Ha ocurrido un error en ${joinPoint.getSignature().getName()}"
    buffer << "de ${joinPoint.getTarget().getClass().getName()}"
    buffer << " - Argumentos:"
    joinPoint.getArgs().each {
      buffer << it
    }
    buffer << " y el error ${customNameException.getMessage()}"
    log.error buffer.toString()
  }

}


