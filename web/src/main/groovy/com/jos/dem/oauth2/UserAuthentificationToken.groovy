package com.jos.dem.oauth2

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority


class UserAuthenticationToken extends AbstractAuthenticationToken {

  static final Long serialVersionUID = -1092219614309982278L
  final Object principal
  Object credentials

  UserAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(authorities)
    this.principal = principal
    this.credentials = credentials
    super.setAuthenticated(true)
  }

}

