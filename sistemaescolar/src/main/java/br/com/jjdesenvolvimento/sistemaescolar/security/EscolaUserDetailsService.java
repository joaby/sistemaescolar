package br.com.jjdesenvolvimento.sistemaescolar.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.jjdesenvolvimento.sistemaescolar.model.Professor;
import br.com.jjdesenvolvimento.sistemaescolar.model.Secretario;
import br.com.jjdesenvolvimento.sistemaescolar.model.TipoUsuario;
import br.com.jjdesenvolvimento.sistemaescolar.service.AlunoService;
import br.com.jjdesenvolvimento.sistemaescolar.service.ProfessorService;
import br.com.jjdesenvolvimento.sistemaescolar.service.SecretarioService;

@Component
public class EscolaUserDetailsService implements UserDetailsService{
	
	@Autowired
	ProfessorService professorService;
	@Autowired
	AlunoService alunoService;
	@Autowired
	SecretarioService secretarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String arr[] = username.split(":");
		
		if(arr[1].equals(TipoUsuario.PROFESSOR.name())){
			Professor professor = professorService.buscarPorLogin(arr[0]);
			if (professor == null) {
				throw new UsernameNotFoundException("Professor não encontrado!");
			}else {
				return new UsuarioSistema(professor.getNome(), TipoUsuario.PROFESSOR, professor.getLogin(), professor.getSenha(), authorities(TipoUsuario.PROFESSOR));
			}
		}else if(arr[1].equals(TipoUsuario.SECRETARIO.name())){
			Secretario secretario = secretarioService.buscarPorLogin(arr[0]);
			if(secretario == null) {
				throw new UsernameNotFoundException("Secretario não encontrado!");
			}else {
				return new UsuarioSistema(secretario.getNome(), TipoUsuario.SECRETARIO, secretario.getLogin(), secretario.getSenha(), authorities(TipoUsuario.SECRETARIO));
			}
		}else {
			throw new UsernameNotFoundException("Professor não encontrado!");
		}	
		
	}
	
	public Collection<? extends GrantedAuthority> authorities(TipoUsuario tipo) {
		Collection<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("ROLE_" + tipo.name()));
		return auths;
	}

}
