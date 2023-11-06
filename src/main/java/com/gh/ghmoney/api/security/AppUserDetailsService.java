package com.gh.ghmoney.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gh.ghmoney.api.model.Usuario;
import com.gh.ghmoney.api.repository.UsuariorRepository;


@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuariorRepository usuariorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuariorRepository.findByEmail(email);
		Usuario usuario  = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario ou senha incorretos"));
		
		return new UsuarioSistema(usuario,  getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		usuario.getPermissoes().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao())));
		return authorities;
	}

}
