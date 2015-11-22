package com.jos.dem.oauth2

interface UserValidator {
  Boolean isValidUser(String user, String password)
}

