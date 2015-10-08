package com.jos.dem.jmailer.advice

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class AfterThrowingAdvice {

  Log log = LogFactory.getLog(AfterThrowingAdvice.class)

  @AfterThrowing(pointcut="execution(* com.jos.dem.controller.**.*(..))",throwing="customNameException")
  def afterReturningMethod(JoinPoint joinPoint, RuntimeException customNameException) {
    StringBuffer buffer = new StringBuffer("Ha ocurrido un error en " + joinPoint.getSignature().getName() + " ")
    buffer.append("de " + joinPoint.getTarget().getClass().getName() + " - Argumentos:")
    for(Object o:joinPoint.getArgs()){
      buffer.append(o + " ")
    }
    buffer.append(" y el error " + customNameException.getMessage())
    log.error(buffer.toString())
  }

}


