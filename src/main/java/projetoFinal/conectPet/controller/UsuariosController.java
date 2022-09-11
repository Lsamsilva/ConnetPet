package projetoFinal.conectPet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoFinal.conectPet.domain.dto.DoacaoCreateRequest;
import projetoFinal.conectPet.domain.dto.DoacaoUpdateRequest;
import projetoFinal.conectPet.domain.dto.UsuarioCreateRequest;
import projetoFinal.conectPet.domain.dto.UsuarioUpdateRequest;
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
    public ResponseEntity<UsuarioEntity> criarUsuario (@RequestBody @Valid UsuarioCreateRequest usuario){
        var doacaoResponse = service.criarUsuario(usuario);
        return ResponseEntity.ok(doacaoResponse);
    }

    //Lista de todos os Usuarios
    @GetMapping(value = "api/usuarios")
    public ResponseEntity<List<UsuarioEntity>> listar (){
        var listaDeUsuarios = service.listar();

        return ResponseEntity.ok(listaDeUsuarios);
    }

    //Consulta de usuario por ID
    @GetMapping(value = "api/usuario/{id}")
    public ResponseEntity<UsuarioEntity> buscarPorId(@PathVariable int id ){
        var doacaoResponse = service.buscarPorId(id);

        return ResponseEntity.ok(doacaoResponse);
    }

    //Consulta de usuario por Nome
    @GetMapping(value = "api/usuario/nome/{nomeParam}")
    public ResponseEntity<UsuarioEntity> buscarPorNome(@PathVariable String nomeParam){
        var usuarioResponse = service.buscarPorNome(nomeParam);

        return  ResponseEntity.ok(usuarioResponse);
    }

    //Deletar
    @DeleteMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity<UsuarioEntity> deletarUsuario (@PathVariable int idUsuario){
        var usuario = service.deletarUsuario(idUsuario);
        return ResponseEntity.ok(usuario);
    }

    //Atualizar campo de cadastro do Pet e criar método no Service
    @PutMapping(value = "api/usuario/{idUsuario}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(
            @PathVariable int idUsuario,
            @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest){
        var usuario = service.atualizarUsuario(idUsuario , usuarioUpdateRequest);
        return ResponseEntity.ok(usuario);
    }
}
