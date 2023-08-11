package com.gh.ghmoney.api.repository.lancamento;

import java.util.List;

import com.gh.ghmoney.api.model.Lancamento;
import com.gh.ghmoney.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
		
	

}
