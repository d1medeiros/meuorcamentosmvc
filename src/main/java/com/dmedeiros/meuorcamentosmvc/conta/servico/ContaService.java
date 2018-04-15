package com.dmedeiros.meuorcamentosmvc.conta.servico;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Carteira;
import com.dmedeiros.meuorcamentosmvc.conta.modelo.Conta;
import com.dmedeiros.meuorcamentosmvc.conta.repository.CarteiraRepository;
import com.dmedeiros.meuorcamentosmvc.conta.repository.ContaRepository;
import com.dmedeiros.meuorcamentosmvc.usuario.exception.UsuarioException;
import com.dmedeiros.meuorcamentosmvc.usuario.modelo.Usuario;
import com.dmedeiros.meuorcamentosmvc.usuario.servico.UsuarioService;
import com.dmedeiros.meuorcamentosmvc.utils.TokenGenerator;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private CarteiraRepository carteiraRepository;
    @Autowired
    private UsuarioService usuarioService;

    private Usuario getUsuario(Usuario usuario) {
        Usuario usuarioEncontrado = usuarioService.buscar(usuario);
        return usuarioEncontrado;
    }

    private Carteira getCarteira(Usuario usuario) throws UsuarioException {
        Usuario usuarioNotEmpty = usuario.usuarioIsNotEmpty();
        Carteira carteira = getUsuario(usuarioNotEmpty).getCarteira();
        return carteira;
    }

    private synchronized void salvaContaNormalOuParaUmAno(Conta conta, int anoVigente, Carteira carteira) {
        if (conta.isRepetir()) {
            for (int i = 0; i < 13; i++) {
                Conta geraContasParaDozeMeses = Conta.geraContasParaDozeMeses(i, conta);
                if (geraContasParaDozeMeses.getDataPagamento().getYear() > anoVigente) {
                    break;
                } else {
                    carteira.setConta(geraContasParaDozeMeses);
                }
            }
            carteiraRepository.save(carteira);
        } else {
            carteira.setConta(conta);
            carteiraRepository.save(carteira);
        }
    }

    public synchronized void inserir(Conta conta, String token) {

        int anoVigente = conta.getDataPagamento().getYear();
        conta.setChaveGrupoContas(TokenGenerator.generateToken(conta.getNome()));

        try {

            Usuario usuarioValido = usuarioService.valida(token);
            Carteira carteira = getCarteira(usuarioValido);
            salvaContaNormalOuParaUmAno(conta, anoVigente, carteira);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (UsuarioException e) {
            e.printStackTrace();
        }

    }

}
