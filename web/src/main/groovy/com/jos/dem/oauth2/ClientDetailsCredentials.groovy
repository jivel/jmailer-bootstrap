package com.jos.dem.oauth2

import org.springframework.security.oauth2.provider.client.BaseClientDetails

interface ClientDetailsCredentials {
  BaseClientDetails getBaseClientDetails()
}

