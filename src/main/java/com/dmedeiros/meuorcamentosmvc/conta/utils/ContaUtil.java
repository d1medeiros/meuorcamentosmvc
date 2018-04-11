package com.dmedeiros.meuorcamentosmvc.conta.utils;

import java.time.LocalDate;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Conta;
import com.dmedeiros.meuorcamentosmvc.conta.modelo.TipoConta;
import com.dmedeiros.meuorcamentosmvc.utils.TokenGenerator;

public class ContaUtil {

	public static Conta generateConta(String nome, boolean estado, boolean repetir, TipoConta tipo, Double valor) {
		Conta conta = new Conta();
		conta.setNome(nome);
		conta.setChaveGrupoContas(TokenGenerator.generateToken(nome));
		conta.setDataPagamento(LocalDate.now());
		conta.setEstado(estado);
		conta.setRepetir(repetir);
		conta.setTipoConta(tipo);
		conta.setValor(valor);
		return conta;
	}
	
}
