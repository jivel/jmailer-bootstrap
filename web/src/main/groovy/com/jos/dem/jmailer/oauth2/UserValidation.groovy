package com.jos.dem.jmailer.oauth2

interface UserValidator {
  Boolean isValidUser(String user, String password)
}

