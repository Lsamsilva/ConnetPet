package projetoFinal.conectPet.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projetoFinal.conectPet.domain.dto.DoacaoCreateRequest;
import projetoFinal.conectPet.domain.dto.DoacaoResponse;
import projetoFinal.conectPet.domain.dto.DoacaoUpdateRequest;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.exception.DoacaoInvalidoException;
import projetoFinal.conectPet.exception.DoacaoNaoEncontradaException;
import projetoFinal.conectPet.repository.DoacaoRepository;
import projetoFinal.conectPet.repository.UsuariosRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoacoesService {
    //caminho do destino das imagens
    private final String FOLDER_PATH="c://Users/Acer/Fotos/";
    private final DoacaoRepository repository;

    ObjectMapper objectMapper = new ObjectMapper();
    public DoacoesService (final DoacaoRepository repository){this.repository = repository;}

    public DoacaoResponse criarDoacao (MultipartFile image, String jsonData) throws IOException {
        //caminho do destino com o arquivo
        String filePath = FOLDER_PATH+image.getOriginalFilename();
        DoacaoResponse doacaoRequest = objectMapper.readValue(jsonData, DoacaoResponse.class);
        doacaoRequest.setImagem(filePath);

        var novaDoacao = new DoacaoEntity();
        novaDoacao.setNome(doacaoRequest.getNome());
        novaDoacao.setEspecie(doacaoRequest.getEspecie());
        novaDoacao.setIdade(doacaoRequest.getIdade());
        novaDoacao.setNivelDeFofura(doacaoRequest.getNivelDeFofura());
        novaDoacao.setNivelDeCarencia(doacaoRequest.getNivelDeCarencia());
        novaDoacao.setImagem(doacaoRequest.getImagem());
        novaDoacao.setUsuario(doacaoRequest.getUsuario());

        var doacaoSalva = repository.save(novaDoacao);

        //salva imagem no caminho local
        image.transferTo(new File(filePath));

        return new DoacaoResponse(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getNivelDeFofura(),
                doacaoSalva.getNivelDeCarencia(),
                doacaoSalva.getImagem(),
                doacaoSalva.getUsuario()
        );

    }

    public DoacaoResponse buscarPorId (Integer idDoacao){
        var doacaoEncontrada = repository.findById(idDoacao);

        if (doacaoEncontrada.isEmpty()){
            //throw new DoacaoNaoEncontradoException();
        }

        var doacaoSalva = doacaoEncontrada.get();
        return new DoacaoResponse(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getNivelDeFofura(),
                doacaoSalva.getNivelDeCarencia(),
                doacaoSalva.getImagem(),
                doacaoSalva.getUsuario()
        );
    }

    /*public DoacaoResponse buscarDoacaoPorUsuario (Integer id){
        var usuarioEncontrado = repository.findByUsuario(id);

        var doacaoUsuario = usuarioEncontrado.getUsuario();

        return new DoacaoResponse(

        );

    }*/

    public DoacaoResponse buscarPorNome (String nome){

        try{
            var doacaoEncontrada = repository.findByNome(nome);

            return new DoacaoResponse(
                    doacaoEncontrada.getId(),
                    doacaoEncontrada.getNome(),
                    doacaoEncontrada.getEspecie(),
                    doacaoEncontrada.getIdade(),
                    doacaoEncontrada.getNivelDeFofura(),
                    doacaoEncontrada.getNivelDeCarencia(),
                    doacaoEncontrada.getImagem(),
                    doacaoEncontrada.getUsuario()
            );
        }catch (DoacaoNaoEncontradaException e){
            throw new DoacaoNaoEncontradaException();
        }
    }

    public DoacaoResponse atualizarDoacao (Integer idDoacao , MultipartFile image, String jsonData) throws IOException{
        //caminho do destino com o arquivo
        String filePath = FOLDER_PATH+image.getOriginalFilename();
        DoacaoResponse doacaoRequest = objectMapper.readValue(jsonData, DoacaoResponse.class);
        doacaoRequest.setImagem(filePath);

        var doacaoEncontrada = repository.findById(idDoacao);

        if(doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        var doacaoAtualizada = doacaoEncontrada.get();
        doacaoAtualizada.setNome(doacaoRequest.getNome());
        doacaoAtualizada.setEspecie(doacaoRequest.getEspecie());
        doacaoAtualizada.setIdade(doacaoRequest.getIdade());
        doacaoAtualizada.setNivelDeFofura(doacaoRequest.getNivelDeFofura());
        doacaoAtualizada.setNivelDeCarencia(doacaoRequest.getNivelDeCarencia());
        doacaoAtualizada.setImagem(doacaoRequest.getImagem());

        var doacaoSalva = repository.save(doacaoAtualizada);

        return new DoacaoResponse(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getNivelDeFofura(),
                doacaoSalva.getNivelDeCarencia(),
                doacaoSalva.getImagem(),
                doacaoSalva.getUsuario()
        );
    }


}
