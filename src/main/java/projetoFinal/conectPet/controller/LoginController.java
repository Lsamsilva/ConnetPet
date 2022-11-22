package projetoFinal.conectPet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoFinal.conectPet.domain.dto.LoginResponse;
import projetoFinal.conectPet.repository.UsuariosRepository;
import projetoFinal.conectPet.service.LoginService;

import javax.validation.Valid;

@RestController
public class LoginController {

    private UsuariosRepository usuariosRepository;

    private LoginService service;

    public LoginController(final LoginService service){this.service = service;}

    /*@GetMapping(value = "/")
    @CrossOrigin
    public ResponseEntity<LoginResponse> buscarLogin(@PathVariable String senha , Integer login ){
        var loginResponse = service.buscarPorLogin(senha , login);

        return new ResponseEntity<>(HttpStatus.OK);
    }*/

    @PostMapping(value = "/")
    @CrossOrigin
    public ResponseEntity<LoginResponse> buscarLogin(@RequestBody @Valid String login ,
                                                     @RequestBody @Valid Integer senha){
        var loginResponse = service.buscarPorLogin(login , senha);

        return ResponseEntity.ok(loginResponse);
    }

}
