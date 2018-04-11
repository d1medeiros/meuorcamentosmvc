package com.dmedeiros.meuorcamentosmvc;



import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Carteira;
import com.dmedeiros.meuorcamentosmvc.conta.modelo.Conta;
import com.dmedeiros.meuorcamentosmvc.conta.modelo.TipoConta;
import com.dmedeiros.meuorcamentosmvc.conta.repository.CarteiraRepository;
import com.dmedeiros.meuorcamentosmvc.conta.utils.ContaUtil;
import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;
import com.dmedeiros.meuorcamentosmvc.usuario.repository.UsuarioRepository;

@SpringBootApplication
public class MeuorcamentosmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeuorcamentosmvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UsuarioRepository repository, CarteiraRepository carteiraRepository) {
		return (args) -> {
			
			Carteira carteira = new Carteira();
			
			Usuario usuario = new Usuario();
			usuario.setNome("diego medeiros");
			usuario.setLogin("diego");
			usuario.setSenha("12345");
			usuario.setCarteira(carteira);
			repository.save(usuario);
			
			Conta c1 = ContaUtil.generateConta("vivo", false, false, TipoConta.GASTOS, 120.0);
			Conta c2 = ContaUtil.generateConta("academia", false, false, TipoConta.GASTOS, 320.0);
			Conta c3 = ContaUtil.generateConta("carro", false, false, TipoConta.GASTOS, 1200.0);
			Conta c4 = ContaUtil.generateConta("seguro", false, false, TipoConta.GASTOS, 620.0);
			
			carteira.setContas(Arrays.asList(c1, c2, c3, c4));
			carteiraRepository.save(carteira);


		};
	}
	

	
	
	
}