package br.com.servico.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import br.com.entity.AcessoUsuario;
import br.com.entity.AcessoGrupo;
import br.com.entity.AcessoGrupoFuncionalidade;
import br.com.servico.util.StatusUsuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends AcessoUsuario implements UserDetails, Serializable {


	private static final long serialVersionUID = 9056130369100516212L;

	public SecurityUser(){};
	
	public SecurityUser(AcessoUsuario usuario) {
		super(usuario);
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(AcessoGrupo grupo : this.getAcessoGrupos()) {
			for(AcessoGrupoFuncionalidade funcionalidade: grupo.getAcessoGrupoFuncionalidades()) {
				String role = funcionalidade.getAcessoFuncionalidade().getRole();
				if(!authorities.contains(new SimpleGrantedAuthority(role))) {
					authorities.add(new SimpleGrantedAuthority(role));
				}
			}
		}
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return super.getSenha();
	}

	@Override
	public String getUsername() {
		return super.getDeLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.getStatus() == StatusUsuario.ATIVO.value();
	}

}