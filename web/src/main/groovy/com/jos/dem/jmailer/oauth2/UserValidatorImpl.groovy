package com.jos.dem.jmailer.oauth2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.jos.dem.jmailer.constant.ApplicationConstants

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

@Component
class UserValidatorImpl implements UserValidator {

  @Autowired
  Properties oauth2

  Log log = LogFactory.getLog(getClass())

  @Override
  Boolean isValidUser(String user, String password) {
    log.info "LOGIN WITH CREDENTIALS: username: ${username} password: ${password}"

    String clientUsername = oauth2.getProperty(ApplicationConstants.OAUTH2_USERNAME)
    String clientPassword = oauth2.getProperty(ApplicationConstants.OAUTH2_PASSWORD)

    return (user.equals(clientUsername) && password.equals(clientPassword))
  }

}

