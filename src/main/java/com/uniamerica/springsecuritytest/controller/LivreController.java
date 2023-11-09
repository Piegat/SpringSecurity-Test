package com.uniamerica.springsecuritytest.controller;

import com.uniamerica.springsecuritytest.entity.Usuario;
import com.uniamerica.springsecuritytest.repository.UsuarioRepository;
import com.uniamerica.springsecuritytest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livre")
public class LivreController {
    @Autowired
    UsuarioService service;
    @Autowired
    UsuarioRepository repository;
//    @GetMapping("/cadastrar")
//    public String criarUsario(){
//        service.Create();
//        return "Usuarios criados";
//    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(repository.findAll());
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