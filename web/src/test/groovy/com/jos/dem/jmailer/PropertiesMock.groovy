package com.jos.dem.jmailer

class PropertiesMock {

  def map = [:]

  PropertiesMock(def map){
    this.map = map
  }

  String getProperty(String key){
    return map[key]
  }

}
