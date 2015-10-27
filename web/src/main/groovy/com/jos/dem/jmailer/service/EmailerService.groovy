package com.jos.dem.jmailer.service

import com.jos.dem.jmailer.command.MessageCommand

interface EmailerService {

	String getTitle(String name)
  String getDescription()
  def sendEmail(MessageCommand command)

}
