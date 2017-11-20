package br.com.json.bean;

import io.swagger.annotations.ApiModelProperty;

public class Login {
	
	public Login() {}
	
	public Login(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	@ApiModelProperty(required=true)
	private String login, senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}