package com.jos.dem.jmailer.messengine

import javax.jms.Message
import javax.jms.MessageListener
import javax.jms.ObjectMessage

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Service
class LoggerMessageListener implements MessageListener {

  Log log = LogFactory.getLog(getClass())

  def void onMessage(Message message) {
    def data =  ((ObjectMessage) message).getObject()
    String dataToString = data.collect { k, v -> v }.join(';')
    log.info dataToString
  }

}
