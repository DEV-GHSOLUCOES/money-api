package com.gh.ghmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gh.ghmoney.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
