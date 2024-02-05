package com.afdevelopment.biblioteca.controller;


import com.afdevelopment.biblioteca.model.User;
import com.afdevelopment.biblioteca.response.DetailResponse;
import com.afdevelopment.biblioteca.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final String OPCORRECTA = "Operación correcta";
    private final String DETAIL = "detailResponse";
    private final String SUCCESSCODE = "SUC-002";
    private final String RESPONSEDETAIL = "userDetail";
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public ResponseEntity<Map<String, Object>> newUser(@RequestBody User user){
        logger.info("Inicia controlador de registro de usuarios");
        User userResponse = userService.saveUser(user);
        DetailResponse responseOk = new DetailResponse();
        responseOk.setCode(SUCCESSCODE);
        responseOk.setBussinessMeaning(OPCORRECTA);
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put(DETAIL, responseOk);
        jsonResponse.put(RESPONSEDETAIL, userResponse);
        logger.info("Finaliza controlador de registro de usuarios");
        return (new ResponseEntity<>(jsonResponse, new HttpHeaders(), HttpStatus.OK));
    }
    @GetMapping("/curp/{curp}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable String curp){
        logger.info("Inicia controlador de búsqueda de usuarios por curp");
        User user = userService.findByCURP(curp);
        DetailResponse responseOk = new DetailResponse();
        responseOk.setCode(SUCCESSCODE);
        responseOk.setBussinessMeaning(OPCORRECTA);
        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put(DETAIL, responseOk);
        jsonResponse.put(RESPONSEDETAIL, user);
        logger.info("Finaliza controlador de búsqueda de usuarios por curp");
        return (new ResponseEntity<>(jsonResponse, new HttpHeaders(), HttpStatus.OK));
    }
}
