package projetoFinal.conectPet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoFinal.conectPet.domain.dto.*;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.repository.UsuariosRepository;
import projetoFinal.conectPet.service.UsuariosService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuariosController {

    private final UsuariosService service;

    private UsuariosRepository usuariosRepository;

    public UsuariosController (final UsuariosService service){
        this.service = service;
    }


    //Criar Cadastro Usuario
    @PostMapping
    @CrossOrigin
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario (@RequestBody @Valid UsuarioCreateRequestDTO usuario){
        var usuarioResponse = service.criarUsuario(usuario);

        return ResponseEntity.ok(usuarioResponse);
    }

    //Verificação de Login
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<UsuarioResponseDTO> verificarLogin (@RequestBody @Valid LoginResquestDTO loginResquestDTO){
        var usuarioResponse = service.verificarLogin(loginResquestDTO.getCpf() ,
                loginResquestDTO.getSenha());

        return ResponseEntity.ok(usuarioResponse);
    }


    //Lista de todos os Usuarios
    @GetMapping("/listar")
    @CrossOrigin
    public ResponseEntity<List<UsuarioEntity>> getAll(){
        List<UsuarioEntity> usuarios = new ArrayList<>();
        usuarios = usuariosRepository.findAll();

        return new ResponseEntity<> (usuarios , HttpStatus.OK);
    }

    //Consulta de usuario por ID
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable int id ){
        var usuarioResponse = service.buscarPorId(id);

        return ResponseEntity.ok(usuarioResponse);
    }

    //Consulta de usuario por Nome
    @GetMapping("/{nomeParam}")
    @CrossOrigin
    public ResponseEntity<UsuarioResponseDTO> buscarPorNome(@PathVariable String nomeParam){
        var usuarioResponse = service.buscarPorNome(nomeParam);

        return  ResponseEntity.ok(usuarioResponse);
    }

    //Deletar por Id
    @DeleteMapping("/{idUsuario}")
    @CrossOrigin
    public ResponseEntity<Optional<UsuarioEntity>> deletarUsuario (@PathVariable Integer idUsuario){
        try{
            usuariosRepository.deleteById(idUsuario);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //Atualizar campos
    @PutMapping("/{idUsuario}")
    @CrossOrigin
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable int idUsuario,
            @RequestBody @Valid UsuarioUpdateRequestDTO usuarioUpdateRequest){
        var usuario = service.atualizarUsuario(idUsuario , usuarioUpdateRequest);

        return ResponseEntity.ok(usuario);
    }
}
