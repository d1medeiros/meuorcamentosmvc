package com.dmedeiros.meuorcamentosmvc.usuario.utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Carteira;
import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;
import com.dmedeiros.meuorcamentosmvc.utils.TokenGenerator;

public class UsuarioUtil {

	public static Usuario generateUsuario(Long id, boolean estado, String nome, String login, String senha) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setEstado(estado);
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		return usuario;
	}
	
	public static Usuario prepararUsuario(Usuario usuario) {
		Carteira carteira = new Carteira();
		try {
			String hash = TokenGenerator.digester(usuario.getSenha());
			usuario.setSenha(hash);
			usuario.setCarteira(carteira);
			usuario.setEstado(true);
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
}
