package com.jos.dem.jmailer.exception

import java.lang.RuntimeException

class EmailerException extends RuntimeException {

  EmailerException(String message){
    super(message)
  }

  @Override
  String getMessage() {
    "Emailer exception"
  }

}
