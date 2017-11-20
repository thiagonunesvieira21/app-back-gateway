package br.com.servico.repository;

import br.com.entity.AcessoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoUsuarioRepository extends JpaRepository<AcessoUsuario, Integer>{

	AcessoUsuario findByDeLogin(String login);
}
