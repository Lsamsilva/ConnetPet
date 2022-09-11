package projetoFinal.conectPet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoFinal.conectPet.domain.dto.DoacaoCreateRequest;
import projetoFinal.conectPet.domain.dto.DoacaoUpdateRequest;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.service.DoacoesService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DoacoesController {

    private final DoacoesService service;

    public DoacoesController(final DoacoesService service){
        this.service = service;
    }

    //Criar Cadastro Pet
    @PostMapping(value = "api/doacoes")
    public ResponseEntity<DoacaoEntity> criarDoacao (@RequestBody @Valid DoacaoCreateRequest doacao){
        var doacaoResponse = service.criarDoacao(doacao);
        return ResponseEntity.ok(doacaoResponse);
    }


    //Lista de todos os Pets para adoção
    @GetMapping(value = "api/doacoes")
    public ResponseEntity<List<DoacaoEntity>> listar (){
        var listaDeDoacao = service.listar();

        return ResponseEntity.ok(listaDeDoacao);
    }

    //Consulta dos animais por ID
    @GetMapping(value = "api/doacoes/{id}")
    public ResponseEntity<DoacaoEntity> buscarPorId(@PathVariable int id ){
        var doacaoResponse = service.buscarPorId(id);

        return ResponseEntity.ok(doacaoResponse);
    }

    //Consulta dos animais por Nome
    @GetMapping(value = "api/doacoes/nome/{nomeParam}")
    public ResponseEntity<DoacaoEntity> buscarPorNome(@PathVariable String nomeParam){
        var doacaoResponse = service.buscarPorNome(nomeParam);

        return ResponseEntity.ok(doacaoResponse);
    }

    //Consulta dos animais por Usuario
    @GetMapping(value = "api/doacoes/usuario/{id_usuario}")
    public ResponseEntity<DoacaoEntity> buscarPorUsuario(@PathVariable String id_usuario){
        var doacaoResponse = service.buscarDoacaoPorUsuario(id_usuario);

        return ResponseEntity.ok(doacaoResponse);

    }


    //Deletar
    @DeleteMapping(value = "api/doacao/{idDoacao}")
    public ResponseEntity<DoacaoEntity> deletarDoacao(@PathVariable int idDoacao){
        var doacao = service.deletarDoacao(idDoacao);
        return ResponseEntity.ok(doacao);
    }


    //Atualizar campo de cadastro do Pet e criar método no Service
    @PutMapping(value = "api/doacao/{idDoacao}")
    public ResponseEntity<DoacaoEntity> atualizarDoacao(
            @PathVariable int idDoacao,
            @RequestBody @Valid DoacaoUpdateRequest doacaoUpdateRequest){
        var doacao = service.atualizarDoacao(idDoacao , doacaoUpdateRequest);
        return ResponseEntity.ok(doacao);
    }

}


