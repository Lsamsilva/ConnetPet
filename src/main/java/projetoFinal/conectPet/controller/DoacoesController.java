package projetoFinal.conectPet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetoFinal.conectPet.domain.dto.DoacaoCreateRequest;
import projetoFinal.conectPet.domain.dto.DoacaoResponse;
import projetoFinal.conectPet.domain.dto.DoacaoUpdateRequest;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.repository.DoacaoRepository;
import projetoFinal.conectPet.service.DoacoesService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class DoacoesController {

    private final DoacoesService service;

    private DoacaoRepository doacaoRepository;

    public DoacoesController(final DoacoesService service){
        this.service = service;
    }

    //Criar Cadastro Pet
    @PostMapping(value = "api/doacoes")
    @CrossOrigin
    public ResponseEntity<DoacaoResponse> criarDoacao (@RequestBody @Valid DoacaoCreateRequest doacao){
        var doacaoResponse = service.criarDoacao(doacao);
        return ResponseEntity.ok(doacaoResponse);
    }


    //Lista de todos os Pets para adoção
    @GetMapping(value = "api/doacoes/listar")
    @CrossOrigin
    public ResponseEntity<List<DoacaoEntity>> getAll(){
        List<DoacaoEntity> doacoes = new ArrayList<>();
        doacoes = doacaoRepository.findAll();

        return new ResponseEntity<>(doacoes , HttpStatus.OK);
    }

    //Consulta dos animais por ID
    @GetMapping(value = "api/doacoes/{id}")
    @CrossOrigin
    public ResponseEntity<DoacaoResponse> buscarPorId(@PathVariable int id ){
        var doacaoResponse = service.buscarPorId(id);

        return ResponseEntity.ok(doacaoResponse);
    }

    //Consulta dos animais por Nome
    @GetMapping(value = "api/doacoes/nome/{nomeParam}")
    @CrossOrigin
    public ResponseEntity<DoacaoResponse> buscarPorNome(@PathVariable String nomeParam){
        var doacaoResponse = service.buscarPorNome(nomeParam);

        return ResponseEntity.ok(doacaoResponse);
    }

    /*Consulta dos animais por Usuario  FAZER!!!
    @GetMapping(value = "api/doacoes/usuario/{id_usuario}")
    public ResponseEntity<DoacaoEntity> buscarPorUsuario(@PathVariable String id_usuario){
        var doacaoResponse = service.buscarDoacaoPorUsuario(id_usuario);

        return ResponseEntity.ok(doacaoResponse);

    }*/


    //Deletar
    @DeleteMapping(value = "api/doacao/{idDoacao}")
    @CrossOrigin
    public ResponseEntity<Optional<DoacaoEntity>> deletarDoacao (@PathVariable Integer idDoacao){
        try{
            doacaoRepository.deleteById(idDoacao);
            return new ResponseEntity<Optional<DoacaoEntity>>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Optional<DoacaoEntity>>(HttpStatus.NOT_FOUND);
        }
    }


    //Atualizar campos
    @PutMapping(value = "api/doacao/{idDoacao}")
    @CrossOrigin
    public ResponseEntity<DoacaoResponse> atualizarDoacao(
            @PathVariable int idDoacao,
            @RequestBody @Valid DoacaoUpdateRequest doacaoUpdateRequest){
        var doacao = service.atualizarDoacao(idDoacao , doacaoUpdateRequest);
        return ResponseEntity.ok(doacao);
    }

}


