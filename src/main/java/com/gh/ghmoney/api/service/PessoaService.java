package com.gh.ghmoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gh.ghmoney.api.model.Pessoa;
import com.gh.ghmoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizarPessoa( Long codigo, Pessoa pessoa) {
		 Pessoa pessoaSalva = buscaPessoaPorCodigo(codigo);
		  BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		 return pessoaRepository.save(pessoaSalva);
	}


	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {

		Pessoa pessoaSalva = this.buscaPessoaPorCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
	}

	public  Pessoa buscaPessoaPorCodigo(Long codigo) {
		Optional<Pessoa> pessoaSalva = this.pessoaRepository.findById(codigo);
		return pessoaSalva.orElseThrow(() -> new EmptyResultDataAccessException(1));
	}
}
