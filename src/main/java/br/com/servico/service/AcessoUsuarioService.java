package br.com.servico.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.entity.AcessoUsuario;
import br.com.servico.repository.AcessoUsuarioRepository;
import br.com.servico.security.TokenAuthenticationService;
import br.com.servico.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.servico.security.SecurityUser;
import br.com.servico.security.UserService;

@Service
public class AcessoUsuarioService extends GenericService<AcessoUsuario, Integer>{
	
	private AcessoUsuarioRepository usuarioRepo;
	
	private String userToken;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private HttpSession session;
	
	@Autowired
	public AcessoUsuarioService(AcessoUsuarioRepository usuarioRepo) {
		super(usuarioRepo);
		this.usuarioRepo = usuarioRepo;
	}

	public AcessoUsuario findByLoginAndSenha(String login, String senha) {
		AcessoUsuario usuario = usuarioRepo.findByDeLogin(login.trim());
		if(usuario != null && isPassMatch(senha, usuario)) {
			return usuario;
		}
		return null;
	}
	
	public AcessoUsuario doLogin(HttpServletRequest request, HttpServletResponse response, String login, String senha) {
		AcessoUsuario usuario = this.findByLoginAndSenha(login, senha);
		if(usuario == null) {
			return null;
		}
		makeAuthenication(request, response, usuario);
		return usuario;
	}

	public String makeAuthenication(HttpServletRequest request, HttpServletResponse response, AcessoUsuario usuario) {
		SecurityUser securityUser = new SecurityUser(usuario);
		UserService userService = new UserService();
		userService.setSession(session);
		userService.addUser(securityUser);

		TokenAuthenticationService authenticationService = new TokenAuthenticationService(userService);
		UserAuthentication userAuthentication = new UserAuthentication(securityUser);
		userToken = authenticationService.addAuthentication(response, userAuthentication);
		Authentication authentication = authenticationService.getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return userToken;
	}

	private String encodePassword(String senha){
		return passwordEncoder.encode(senha);
	}

	private boolean isPassMatch(String senha, AcessoUsuario usuario) {
		return BCrypt.checkpw(senha, usuario.getSenha());
	}

	public String getUserToken() {
		return userToken;
	}
}