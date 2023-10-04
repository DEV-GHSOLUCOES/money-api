package com.gh.ghmoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gh.ghmoney.api.model.Usuario;

public interface UsuariorRepository  extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);

}
