package com.jos.dem.jmailer.service

import com.jos.dem.jmailer.command.Command

interface NotificationService {

  void sendNotification(Command command)

}
