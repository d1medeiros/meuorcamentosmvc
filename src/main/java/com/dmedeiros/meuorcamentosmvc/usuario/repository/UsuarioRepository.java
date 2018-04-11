package com.dmedeiros.meuorcamentosmvc.usuario.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	Usuario findByLoginAndSenha(String login, String senha);

}
