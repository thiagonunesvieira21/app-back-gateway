package br.com.servico.security;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public final class TokenHandler {

      private final String secret;
      private final UserService userService;

      public TokenHandler(String secret, UserService userService) {
          this.secret = secret;
          this.userService = userService;
      }

     public SecurityUser parseUserFromToken(String token, HttpSession session) throws UsernameNotFoundException{
         Claims claims = Jwts.parser()
                 .setSigningKey(secret)
                 .parseClaimsJws(token)
                 .getBody();
         String username = (String) claims.get("nome");
         userService.setSession(session);
         return userService.loadUserByUsername(username);
     }

     public String createTokenForUser(SecurityUser user) {
    	 Claims claims = Jwts.claims();
    	 claims.put("nome", user.getNome());
    	 claims.put("idUsuario", user.getIdUsuario());
         return Jwts.builder()
        		 .setClaims(claims)
                 .signWith(SignatureAlgorithm.HS512, secret)
                 .compact();
         
     }
 }
