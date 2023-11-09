package com.uniamerica.springsecuritytest.service;

import com.uniamerica.springsecuritytest.entity.Usuario;
import com.uniamerica.springsecuritytest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository repository;
    @Autowired
    PasswordEncoder encoder;

    @Transactional
    public String Create(Usuario usuario){
        Assert.notNull(usuario, "Usuário não pode ser nulo");
        Assert.notNull(usuario.getUsername(), "Username não pode ser nulo");
        Assert.notNull(usuario.getPassword(), "Password não pode ser nulo");
        Assert.notNull(usuario.getRole(), "Role não pode ser nulo");

        Usuario newUsuario = new Usuario();

        newUsuario.setPassword(encoder.encode(usuario.getPassword()));
        newUsuario.setUsername(usuario.getUsername());
        newUsuario.setRole(usuario.getRole());


        repository.save(newUsuario);

//        Usuario admin = new Usuario();
//        Usuario user = new Usuario();
//        Usuario cliente = new Usuario();
//
//        admin.setUsername("admin");
//        admin.setPassword(encoder.encode("admin"));
//        admin.setRole(Role.ADMIN);
//
//        user.setUsername("user");
//        user.setPassword(encoder.encode("user"));
//        user.setRole(Role.USER);
//
//        cliente.setUsername("cliente");
//        cliente.setPassword(encoder.encode("cliente"));
//        cliente.setRole(Role.CLIENTE);
//
//        repository.save(admin);
//        repository.save(user);
//        repository.save(cliente);
//
        return "Cadastrado com sucesso!";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        return usuario;
    }
}