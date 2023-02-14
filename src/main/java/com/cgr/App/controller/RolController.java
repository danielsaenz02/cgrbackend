package com.cgr.App.controller;

import com.cgr.App.entity.Rol;
import com.cgr.App.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin({"*"})
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/listar")
    public ResponseEntity<List<Rol>> getAllRoles(){
        List<Rol> roles = rolService.getAll();
        return new ResponseEntity<List<Rol>>(roles, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Rol> saveRol(@RequestBody Rol rol){
        Rol newRol = rolService.save(rol);
        return new ResponseEntity<Rol>(newRol, HttpStatus.OK);
    }
}
