package com.dmedeiros.meuorcamentosmvc.conta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long>{

}
