package br.com.servico.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

public class TokenAuthenticationService {

    public static final String AUTH_HEADER_NAME = "Authorization";

    private final TokenHandler tokenHandler;
    private String secret = "3Cpy6bMTornGJXXAxmjzgeL7g2UXeFuXyKkNElVFwNcFiVK7tiKa8flZ7cz28BB";
    private Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

    public TokenAuthenticationService(UserService userService) {
        tokenHandler = new TokenHandler(secret, userService);
    }

    public String addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final SecurityUser user = authentication.getDetails();
        String userToken = tokenHandler.createTokenForUser(user);
        return userToken;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTH_HEADER_NAME);
        Authentication authentication = null;
        if (token != null) {
            token = token.replace("Bearer ", "");
            try {
                final SecurityUser user = tokenHandler.parseUserFromToken(token, request.getSession());
                if (user != null) {
                    authentication =  new UserAuthentication(user);
                }

            } catch(Exception e) {
                logger.debug("Erro na autenticação do usuário", e);
            }
        }
        return authentication;
    }
}