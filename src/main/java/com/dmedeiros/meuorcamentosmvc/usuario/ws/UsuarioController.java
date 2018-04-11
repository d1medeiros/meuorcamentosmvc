package com.dmedeiros.meuorcamentosmvc.usuario.ws;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.validation.Valid;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;
import com.dmedeiros.meuorcamentosmvc.usuario.servico.UsuarioService;
import com.dmedeiros.meuorcamentosmvc.usuario.utils.UsuarioUtil;
import com.dmedeiros.meuorcamentosmvc.utils.TokenGenerator;

@RestController()
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method=RequestMethod.GET, path="/usuario/all")
	public Usuario getAll() {
		return UsuarioUtil.generateUsuario(1l, true, "diego medeiros", "diego", "1234");
	}

	@RequestMapping(method=RequestMethod.POST, path="/usuario/gerar")
	public Response geraSenha(@Valid Usuario usuario) {
		boolean validar = usuarioService.inserir(usuario);
		if(validar) {
			return Response.ok().build();
		}else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT, path="/usuario/login")
	public Response getUsuario(@Valid Usuario usuario) {
		Optional<String> usuarioValido = usuarioService.loga(usuario);
		if(usuarioValido.isPresent()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.putOpt("authtoken", usuarioValido.get());
			return Response.ok(jsonObject.toString()).build();
		}else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
	
	
	@RequestMapping(method=RequestMethod.GET, path="/usuario/verificar")
	public Response verificarUsuario(@HeaderParam("XTOKEN")String token) throws IllegalArgumentException, UnsupportedEncodingException {
		Optional<Usuario> usuarioValido = usuarioService.valida(token);
		Optional<String> newGenerateHash = usuarioValido.isPresent() ? TokenGenerator.generateHash(usuarioValido.get()) : null;
		if(newGenerateHash.isPresent()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.putOpt("authtoken", newGenerateHash.get());
			return Response.ok(jsonObject.toString()).build();
		}else {
			return Response.status(Status.FORBIDDEN).build();
		}
	}
	
	
	
	
}
