package com.dmedeiros.meuorcamentosmvc.conta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dmedeiros.meuorcamentosmvc.conta.modelo.Carteira;

@Repository
public interface CarteiraRepository extends CrudRepository<Carteira, Long>{

}
