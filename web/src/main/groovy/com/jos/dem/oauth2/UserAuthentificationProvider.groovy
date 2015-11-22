package com.jos.dem.oauth2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

class UserAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  Properties oauth2
  @Autowired
  UserValidator userValidator

  Log log = LogFactory.getLog(getClass())

  Authentication authenticate(Authentication authentication) throws AuthenticationException {
    log.info "authentication: ${authentication.dump()}"
    Boolean result = isValidUser(authentication.getPrincipal().toString(), authentication.getCredentials().toString())
    log.info "RESULT IN AUTH: ${result}"
    if (result) {
      List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>()
      UserAuthenticationToken auth = new UserAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorities)
      return auth
    } else {
      throw new BadCredentialsException("Bad User Credentials")
    }
  }

  public boolean supports(Class<?> authentication) {
    return true
  }

  public boolean isValidUser(String user, String password) {
    return userValidator.isValidUser(user, password)
  }

}

