package projetoFinal.conectPet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoFinal.conectPet.domain.dto.*;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.service.UsuariosService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsuariosController {

    private final UsuariosService service;

    public UsuariosController (final UsuariosService service){
        this.service = service;
    }

    //Criar Cadastro Usuario
    @PostMapping(value = "api/usuario")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario (@RequestBody @Valid UsuarioCreateRequest usuario){
        var usuarioResponse = service.criarUsuario(usuario);
        return ResponseEntity.ok(usuarioResponse);
    }

    //Lista de todos os Usuarios
    /*@GetMapping(value = "api/usuarios")
    public ResponseEntity<List<UsuarioResponse>> listar (){
        var listaDeUsuarios = service.listar();

        return ResponseEntity.ok(listaDeUsuarios);
    }*/

    //Consulta de usuario por ID
    @GetMapping(value = "api/usuario/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable int id ){
        var usuarioResponse = service.buscarPorId(id);

        return ResponseEntity.ok(usuarioResponse);
    }

    //Consulta de usuario por Nome
    @GetMapping(value = "api/usuario/nome/{nomeParam}")
    public ResponseEntity<UsuarioResponse> buscarPorNome(@PathVariable String nomeParam){
        var usuarioResponse = service.buscarPorNome(nomeParam);

        return  ResponseEntity.ok(usuarioResponse);
    }

    //Deletar
    /*@DeleteMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity<UsuarioResponse> deletarUsuario (@PathVariable Integer idUsuario){
        var usuario = service.deletarUsuario(idUsuario);
        return ResponseEntity.ok(usuario);
    }*/

    //Atualizar campo de cadastro do Pet e criar método no Service
    @PutMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable int idUsuario,
            @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest){
        var usuario = service.atualizarUsuario(idUsuario , usuarioUpdateRequest);
        return ResponseEntity.ok(usuario);
    }
}
