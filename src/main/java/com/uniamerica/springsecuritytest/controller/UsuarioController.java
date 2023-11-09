package com.uniamerica.springsecuritytest.controller;

import com.uniamerica.springsecuritytest.entity.Usuario;
import com.uniamerica.springsecuritytest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> admPath(){
            return ResponseEntity.ok("Acesso concedido apenas para ADMIN");
        }
    @GetMapping("/cliente")
    @PreAuthorize("hasAnyAuthority('CLIENTE', 'ADMIN')")
    public ResponseEntity<String> clientePath(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verifica se o usuário possui a função (role) desejada
//        if (authentication != null && authentication.getAuthorities().stream()
//                .anyMatch(authority -> "CLIENTE".equals(authority.getAuthority()))) {
            // O usuário tem a função (role) "CLIENTE"
            return ResponseEntity.ok("Acesso concedido apenas para CLIENTE");
//        } else {
//            // O usuário não tem permissão
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
//        }
    }


    @PostMapping("/post")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String create(@RequestBody Usuario usuario){
        try{
            return service.Create(usuario);
        }catch (Exception e){
            return  e.getMessage();
        }
    }

}
