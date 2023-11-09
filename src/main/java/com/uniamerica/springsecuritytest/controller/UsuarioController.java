package com.uniamerica.springsecuritytest.controller;

import com.uniamerica.springsecuritytest.entity.Usuario;
import com.uniamerica.springsecuritytest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public String userPath(){
        return "<h1>USER</h1>";
    }
    @GetMapping("/admin")
    public String admPath(){
        return "<h1>ADMIN</h1>";
    }
    @GetMapping("/cliente")
    @PreAuthorize("hasRole('CLIENTE')")
    public String clientePath(){
        return "<h1>CLIENTE</h1>";
    }


    @PostMapping("/post")
    public String create(@RequestBody Usuario usuario){
        try{
            return service.Create(usuario);
        }catch (Exception e){
            return  e.getMessage();
        }
    }

}
