package com.jos.dem.jmailer.oauth2

import org.springframework.security.oauth2.provider.client.BaseClientDetails
import org.springframework.stereotype.Component

@Component
class ClientDetailsCredentialsImpl implements ClientDetailsCredentials {

  String integraKey
  String integraSecret

  def authorizedGrantTypes = ['password', 'refresh_token', 'client_credentials']
  def scopes = ['read,write']

  @Override
  BaseClientDetails getBaseClientDetails() {
    BaseClientDetails clientDetails = new BaseClientDetails()
    clientDetails.clientId = integraKey
    clientDetails.clientSecret = integraSecret
    clientDetails.authorizedGrantTypes = authorizedGrantTypes
    clientDetails.scope = scopes
    clientDetails
  }
}
