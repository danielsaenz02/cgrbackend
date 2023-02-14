package com.cgr.App.controller;

import com.cgr.App.entity.User;
import com.cgr.App.exception.BadRequestCustom;
import com.cgr.App.exception.ConflictException;
import com.cgr.App.service.UserService;
import com.cgr.App.validator.Control;
import com.cgr.App.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin({"*"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/listar")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception{
        try {
            UserValidator.validateEntity(user);
            UserValidator.validateStringSize(user);
            UserValidator.validateEmptyField(user);
            User newUser = UserValidator.trimAttributes(user);
            Control.validateEmail(newUser.getEmail());
            if(userService.findByEmail(newUser.getEmail()).isPresent()){
                throw new ConflictException("Ya existe un usuario con este correo");
            }
            userService.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }catch (BadRequestCustom badMessage){
            return new ResponseEntity<>(badMessage.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (ConflictException badConflictMessage){
            return new ResponseEntity<>(badConflictMessage.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody User user) throws Exception {
        try {
            Control.validateEmail(user.getEmail());
            User userLogin = userService.findByEmail(user.getEmail()).orElseThrow(() ->  new BadRequestCustom("El correo no se encuentra registrado. Por favor intente de nuevo y verifique el correo."));
            if (userLogin.getPassword().equals(user.getPassword())) {
                return new ResponseEntity<>(userLogin, HttpStatus.OK);
            } else {
                throw new BadRequestCustom("La contraseña es incorrecta. Por favor intente de nuevo y verifique la contraseña.");
            }
        } catch (BadRequestCustom badMessage) {
            return new ResponseEntity<>(badMessage.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
