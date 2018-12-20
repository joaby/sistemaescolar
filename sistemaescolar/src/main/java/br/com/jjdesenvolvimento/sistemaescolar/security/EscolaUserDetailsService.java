package br.com.jjdesenvolvimento.sistemaescolar.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;
import br.com.jjdesenvolvimento.sistemaescolar.model.Role;
import br.com.jjdesenvolvimento.sistemaescolar.model.TipoUsuario;
import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;

@Component
public class EscolaUserDetailsService implements UserDetailsService{
	
	@Autowired
	ProfessorService professorService;
	AlunoService alunoService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String arr[] = username.split(":");
		if(arr[1].equals(TipoUsuario.PROFESSOR.name())){
			Professor professor = professorService.buscarPorLogin(arr[0]);
			if (professor == null) {
				throw new UsernameNotFoundException("Professor não encontrado!");
			}
			return new UsuarioSistema(professor.getNome(), professor.getLogin(), professor.getSenha(), authorities(professor.getRoles()));
		}else {
			throw new UsernameNotFoundException("Professor não encontrado!");
		}	
		
	}
	
	public Collection<? extends GrantedAuthority> authorities(List<Role> roles) {
		Collection<GrantedAuthority> auths = new ArrayList<>();
		
		for (Role role: roles) {
			auths.add(new SimpleGrantedAuthority("ROLE_" + role.getNome()));
		}
		return auths;
	}

}
