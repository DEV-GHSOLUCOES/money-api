package com.gh.ghmoney.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.gh.ghmoney.api.model.Lancamento;
import com.gh.ghmoney.api.model.Lancamento_;
import com.gh.ghmoney.api.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		
		CriteriaBuilder builder =  entityManager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteriaQuery  =  builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
		
			
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteriaQuery.where(predicates);
		
		TypedQuery<Lancamento> query =  entityManager.createQuery(criteriaQuery);
		
		adicionarRestricoesDePaginacao(query, pageable);
		
		return  new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter)); 
	}

	private Long total(LancamentoFilter lancamentoFilter) {
		
		CriteriaBuilder builder =  entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query =  builder.createQuery(Long.class);
		Root<Lancamento> root = query.from(Lancamento.class);
		
		Predicate[] predicates =  criarRestricoes(lancamentoFilter, builder, root);
		query.where(predicates);
		
		query.select(builder.count(root));
		return entityManager.createQuery(query).getSingleResult();
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina =  paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		
		List<Predicate> predicates  =  new ArrayList<>();

		if (!ObjectUtils.isEmpty(lancamentoFilter.getDescricao())) {
            predicates.add(builder.like(
            		builder.lower(root.get(Lancamento_.descricao)), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}

		if (!ObjectUtils.isEmpty(lancamentoFilter.getDataVencimentoDe())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoDe()));
		}

		if (!ObjectUtils.isEmpty(lancamentoFilter.getDataVencimentoAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento), lancamentoFilter.getDataVencimentoAte()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
