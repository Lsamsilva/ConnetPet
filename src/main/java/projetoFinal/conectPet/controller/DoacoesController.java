package projetoFinal.conectPet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projetoFinal.conectPet.domain.dto.DoacaoCreateRequest;
import projetoFinal.conectPet.domain.dto.DoacaoResponse;
import projetoFinal.conectPet.domain.dto.DoacaoUpdateRequest;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.repository.DoacaoRepository;
import projetoFinal.conectPet.service.DoacoesService;

import javax.validation.Valid;
import java.io.IOException;
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
    @RequestMapping(value="api/doacoes", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    public ResponseEntity<DoacaoResponse> criarDoacao (
            @RequestParam(required=true, value="image") MultipartFile image,
            @RequestParam(required=true, value="jsonData") String jsonData
    ) throws IOException {
        var doacaoResponse = service.criarDoacao(image, jsonData);
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

    /*Consulta dos animais por Usuario
    @GetMapping(value = "api/doacoes/usuario/{idUsuario}")
    public ResponseEntity<DoacaoResponse> buscarPorUsuario(@PathVariable Integer idUsuario){
        var doacaoResponse = service.buscarDoacaoPorUsuario(idUsuario);

        return ResponseEntity.ok(doacaoResponse);
    }*/


    //Deletar
    @DeleteMapping(value = "api/doacao/{idDoacao}")
    @CrossOrigin
    public ResponseEntity<Optional<DoacaoEntity>> deletarDoacao (@PathVariable Integer idDoacao){
        try{
            doacaoRepository.deleteById(idDoacao);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Atualizar campos
    //@PutMapping(value = "api/doacao/{idDoacao}")
    @RequestMapping(value="api/doacao/{idDoacao}", method=RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    public ResponseEntity<DoacaoResponse> atualizarDoacao(
            @RequestParam(required=true, value="idDoacao") Integer idDoacao,
            @RequestParam(required=true, value="image") MultipartFile image,
            @RequestParam(required=true, value="jsonData") String jsonData) throws IOException{
        var doacao = service.atualizarDoacao(idDoacao , image, jsonData);
        return ResponseEntity.ok(doacao);
    }
}


