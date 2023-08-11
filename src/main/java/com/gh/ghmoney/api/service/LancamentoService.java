package com.gh.ghmoney.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gh.ghmoney.api.model.Lancamento;
import com.gh.ghmoney.api.model.Pessoa;
import com.gh.ghmoney.api.repository.LancamentoRepository;
import com.gh.ghmoney.api.repository.PessoaRepository;
import com.gh.ghmoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento salvar( Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (!pessoa.isPresent() || !pessoa.get().getAtivo()) {
			throw new PessoaInexistenteOuInativaException();
			
		}
		
		return lancamentoRepository.save(lancamento);
	}
	
	

}
