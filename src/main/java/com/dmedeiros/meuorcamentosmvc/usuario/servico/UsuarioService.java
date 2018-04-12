package com.dmedeiros.meuorcamentosmvc.usuario.servico;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;
import com.dmedeiros.meuorcamentosmvc.usuario.repository.UsuarioRepository;
import com.dmedeiros.meuorcamentosmvc.usuario.utils.UsuarioUtil;
import com.dmedeiros.meuorcamentosmvc.utils.TokenGenerator;
import com.dmedeiros.meuorcamentosmvc.utils.ValidacaoUtil;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public boolean inserir(Usuario usuario) {
		try {
			Usuario prepararUsuario = UsuarioUtil.prepararUsuario(usuario);
			prepararUsuario = usuarioRepository.save(prepararUsuario);
		} catch (Exception e) { return false; }
		return true;	
	}

	public Optional<String> loga(Usuario usuario) {
		Optional<String> generateHash = null;
		Usuario usuarioPreparado = UsuarioUtil.prepararUsuario(usuario);
		usuarioPreparado.setUltimoAcesso(LocalDate.now());
		//se o usuario foi ativado, mudando o seu estado
		if(usuarioPreparado.isEstado()) {
			usuarioRepository.save(usuarioPreparado);
			generateHash = TokenGenerator.generateHash(usuarioPreparado);
		}
		return generateHash;
	}

	public Optional<Usuario> valida(String token) throws IllegalArgumentException, UnsupportedEncodingException {
		Optional<Usuario> optional = null;
		DecodedJWT hashIsValid = TokenGenerator.ValidateHash(token);
		Claim claim = hashIsValid.getClaim("login");
		String login = claim.isNull() ? null : claim.asString();
		String subject = hashIsValid.getSubject();
		if (ValidacaoUtil.isAllNotNull(login, subject)) {
			Usuario findByLoginSenha = usuarioRepository.findByLoginAndSenha(login, subject);
			optional = Optional.ofNullable(findByLoginSenha);
		}
		return optional;
	}
	
	
	
}
