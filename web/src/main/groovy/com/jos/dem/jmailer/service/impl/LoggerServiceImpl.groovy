package com.jos.dem.jmailer.service.impl

import javax.jms.Message
import javax.jms.ObjectMessage
import javax.jms.Session
import javax.jms.Destination
import javax.jms.JMSException

import org.springframework.jms.core.JmsTemplate
import org.springframework.jms.core.MessageCreator
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import com.jos.dem.jmailer.service.LoggerService

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class LoggerServiceImpl implements LoggerService {

  @Autowired
  JmsTemplate template

  @Autowired
  Destination logger

  Log log = LogFactory.getLog(getClass())

  void notifyRequest(requestParams) {
    log.info "CALLING Notifying"

      template.send(logger, new MessageCreator() {
        public Message createMessage(Session session) throws JMSException {
          ObjectMessage message = session.createObjectMessage()
          message.setObject(requestParams)
          message
        }
      })
  }

}
