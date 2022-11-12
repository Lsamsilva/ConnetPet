package projetoFinal.conectPet.service;


import org.springframework.stereotype.Service;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class DoacoesService {

    private final DoacaoRepository repository;

    public DoacoesService (final DoacaoRepository repository){this.repository = repository;}

    public DoacaoResponse criarDoacao (DoacaoCreateRequest doacaoCreateRequest){

        var novaDoacao = new DoacaoEntity();
        novaDoacao.setNome(doacaoCreateRequest.getNome());
        novaDoacao.setEspecie(doacaoCreateRequest.getEspecie());
        novaDoacao.setIdade(doacaoCreateRequest.getIdade());
        novaDoacao.setNivelDeFofura(doacaoCreateRequest.getNivelDeFofura());
        novaDoacao.setNivelDeCarencia(doacaoCreateRequest.getNivelDeCarencia());
        novaDoacao.setUsuario(doacaoCreateRequest.getUsuario());

        var doacaoSalva = repository.save(novaDoacao);
        return new DoacaoResponse(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getNivelDeFofura(),
                doacaoSalva.getNivelDeCarencia(),
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
                    doacaoEncontrada.getUsuario()
            );
        }catch (DoacaoNaoEncontradaException e){
            throw new DoacaoNaoEncontradaException();
        }
    }

    public DoacaoResponse atualizarDoacao (Integer idDoacao , DoacaoUpdateRequest doacaoUpdateRequest){

        var doacaoEncontrada = repository.findById(idDoacao);

        if(doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        var doacaoAtualizada = doacaoEncontrada.get();
        doacaoAtualizada.setNome(doacaoUpdateRequest.getNome());
        doacaoAtualizada.setEspecie(doacaoUpdateRequest.getEspecie());
        doacaoAtualizada.setIdade(doacaoUpdateRequest.getIdade());
        doacaoAtualizada.setNivelDeFofura(doacaoUpdateRequest.getNivelDeFofura());
        doacaoAtualizada.setNivelDeCarencia(doacaoUpdateRequest.getNivelDeCarencia());

        var doacaoSalva = repository.save(doacaoAtualizada);

        return new DoacaoResponse(
                doacaoSalva.getId(),
                doacaoSalva.getNome(),
                doacaoSalva.getEspecie(),
                doacaoSalva.getIdade(),
                doacaoSalva.getNivelDeFofura(),
                doacaoSalva.getNivelDeCarencia(),
                doacaoSalva.getUsuario()
        );
    }


}
