package br.com.servico.security;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	private HttpSession session;

	@Override
	public final SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {

		SecurityUser user = (SecurityUser) session.getAttribute("sec-user");
		if (user.getNome() == null) {
			throw new UsernameNotFoundException("user not found");
		}
		detailsChecker.check(user);

		return user;
	}

	public void addUser(SecurityUser user) {
		session.setAttribute("sec-user", user);
		session.setMaxInactiveInterval(30*60);
		
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}