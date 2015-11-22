package com.jos.dem.oauth2

import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.ClientRegistrationException
import org.springframework.security.oauth2.provider.NoSuchClientException

class GuestServiceImpl implements ClientDetailsService {

  String integraKey
  String integraSecret

  def credentials = new ClientDetailsCredentialsImpl()

  ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
    if (clientId.equals(credentials.integraKey)) {
      return integraCredentials.getBaseClientDetails()
    }
    else {
      throw new NoSuchClientException("No client recognized with key: " + clientId)
    }
  }
}

