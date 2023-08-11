package com.gh.ghmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gh.ghmoney.api.model.Lancamento;
import com.gh.ghmoney.api.repository.lancamento.LancamentoRepositoryQuery;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long >, LancamentoRepositoryQuery {
	

}
