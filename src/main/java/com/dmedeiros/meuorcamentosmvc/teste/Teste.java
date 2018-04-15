package com.dmedeiros.meuorcamentosmvc.teste;

import java.util.Optional;
import java.util.stream.Stream;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Carteira;
import com.dmedeiros.meuorcamentosmvc.usuario.exception.UsuarioException;
import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;

public class Teste {

	public static void main(String[] args) {

		
		Carteira carteira = new Carteira();
		
		Usuario usuario = new Usuario();
		usuario.setNome("diego medeiros");
		usuario.setLogin("diego");
		usuario.setSenha("12345");
		usuario.setCarteira(carteira);
//		usuario = null;
//		usuario = new Usuario();
		
		try {
			Usuario usuarioIsNotEmpty = Optional.ofNullable(usuario).get().usuarioIsNotEmpty();
		} catch (UsuarioException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	

	public static void isAllNotNull2(int count, String... valor) {
		boolean x = Stream.of(valor).filter(v -> Optional.ofNullable(v).isPresent() && !v.isEmpty()).count() == valor.length;
		System.out.println("Cahamada " + count + " " + x);
				//.forEach(System.out::println);
	}
	public static boolean isAllNotNull(String... valor) {
		return 
		Stream.of(valor).filter(v -> Optional.ofNullable(v).isPresent()).count() == 0l;
	}

	
}

