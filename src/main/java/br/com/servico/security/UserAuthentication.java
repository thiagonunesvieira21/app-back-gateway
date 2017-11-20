package br.com.servico.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication implements Authentication {

	private static final long serialVersionUID = 1L;
	private final SecurityUser user;
      private boolean authenticated = true;

      public UserAuthentication(SecurityUser user) {
          this.user = user;
      }

     @Override
     public String getName() {
         return user.getUsername();
     }

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
         return user.getAuthorities();
     }

     @Override
     public Object getCredentials() {
         return user.getPassword();
     }

    @Override
     public SecurityUser getDetails() {
         return user;
     }

     @Override
     public Object getPrincipal() {
         return user.getUsername();
     }

     @Override
     public boolean isAuthenticated() {
         return authenticated;
     }

     @Override
     public void setAuthenticated(boolean authenticated) {
         this.authenticated = authenticated;
     }
 }
