package projetoFinal.conectPet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoFinal.conectPet.domain.dto.*;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.repository.UsuariosRepository;
import projetoFinal.conectPet.service.UsuariosService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UsuariosController {

    private final UsuariosService service;

    private UsuariosRepository usuariosRepository;

    public UsuariosController (final UsuariosService service){
        this.service = service;
    }


    //Criar Cadastro Usuario
    @PostMapping(value = "api/usuario")
    @CrossOrigin
    public ResponseEntity<UsuarioResponse> cadastrarUsuario (@RequestBody @Valid UsuarioCreateRequest usuario){
        var usuarioResponse = service.criarUsuario(usuario);
        return ResponseEntity.ok(usuarioResponse);
    }

    //Lista de todos os Usuarios
    @GetMapping(value = "api/usuarios/listar")
    @CrossOrigin
    public ResponseEntity<List<UsuarioEntity>> getAll(){
        List<UsuarioEntity> usuarios = new ArrayList<>();
        usuarios = usuariosRepository.findAll();

        return new ResponseEntity<> (usuarios , HttpStatus.OK);
    }

    //Consulta de usuario por ID
    @GetMapping(value = "api/usuario/{id}")
    @CrossOrigin
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable int id ){
        var usuarioResponse = service.buscarPorId(id);

        return ResponseEntity.ok(usuarioResponse);
    }

    //Consulta de usuario por Nome
    @GetMapping(value = "api/usuario/nome/{nomeParam}")
    @CrossOrigin
    public ResponseEntity<UsuarioResponse> buscarPorNome(@PathVariable String nomeParam){
        var usuarioResponse = service.buscarPorNome(nomeParam);

        return  ResponseEntity.ok(usuarioResponse);
    }

    //Deletar por Id
    @DeleteMapping(value = "api/usuario/{idUsuario}")
    @CrossOrigin
    public ResponseEntity<Optional<UsuarioEntity>> deletarUsuario (@PathVariable Integer idUsuario){
        try{
            usuariosRepository.deleteById(idUsuario);
            return new ResponseEntity<Optional<UsuarioEntity>>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Optional<UsuarioEntity>>(HttpStatus.NOT_FOUND);
        }

    }

    //Atualizar campo
    @PutMapping(value = "api/usuario/{idUsuario}")
    @CrossOrigin
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable int idUsuario,
            @RequestBody @Valid UsuarioUpdateRequest usuarioUpdateRequest){
        var usuario = service.atualizarUsuario(idUsuario , usuarioUpdateRequest);
        return ResponseEntity.ok(usuario);
    }
}
