package com.dmedeiros.meuorcamentosmvc.teste;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Carteira;
import com.dmedeiros.meuorcamentosmvc.conta.modelo.Conta;
import com.dmedeiros.meuorcamentosmvc.conta.modelo.TipoConta;
import com.dmedeiros.meuorcamentosmvc.conta.utils.ContaUtil;
import com.dmedeiros.meuorcamentosmvc.usuario.exception.UsuarioException;
import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;
import com.dmedeiros.meuorcamentosmvc.utils.TokenGenerator;

public class Teste {

    public static void main(String[] args) {

        Conta conta1 = new Conta();
        conta1.setId(10l);
        conta1.setNome("bingo");
        conta1.setChaveGrupoContas(TokenGenerator.generateToken("BINGO"));
        conta1.setDataPagamento(LocalDate.now());
        conta1.setEstado(false);
        conta1.setRepetir(false);
        conta1.setTipoConta(TipoConta.GASTOS);
        conta1.setValor(311.0);
        Conta conta2 = new Conta();
        conta2.setNome("bingo");
        conta2.setChaveGrupoContas(conta1.getChaveGrupoContas());
        conta2.setDataPagamento(LocalDate.now());
        conta2.setEstado(false);
        conta2.setRepetir(false);
        conta2.setTipoConta(TipoConta.GASTOS);
        conta2.setValor(311.0);

        System.out.println(conta1.equals(conta2));

        Carteira carteira = new Carteira();
        Usuario usuario = new Usuario();
        usuario.setNome("diego medeiros");
        usuario.setLogin("diego");
        usuario.setSenha("12345");
        usuario.setCarteira(carteira);

        Conta c1 = conta1;
        Conta c2 = ContaUtil.generateConta("academia", false, false, TipoConta.GASTOS, 320.0);
        Conta c3 = ContaUtil.generateConta("carro", false, true, TipoConta.GASTOS, 1200.0);
        Conta c4 = ContaUtil.generateConta("seguro", false, false, TipoConta.GASTOS, 620.0);

        carteira.setContas(Arrays.asList(c1, c2, c3, c4));

//usuario.getCarteira().getContas().stream().filter(c -> c.equals(conta2)).forEach(System.out::println);
        Conta x = usuario.getCarteira().getContas().stream().filter(c -> c.equals(conta2)).findFirst().get();
        // Conta x = usuario.getCarteira().getContas().stream().reduce(c3, (r1, r2) -> r1);

//        System.out.println(x);

    }

    public static void isAllNotNull2(int count, String... valor) {
        boolean x = Stream.of(valor).filter(v -> Optional.ofNullable(v).isPresent() && !v.isEmpty())
                .count() == valor.length;
        System.out.println("Cahamada " + count + " " + x);
        // .forEach(System.out::println);
    }

    public static boolean isAllNotNull(String... valor) {
        return Stream.of(valor).filter(v -> Optional.ofNullable(v).isPresent()).count() == 0l;
    }

}
