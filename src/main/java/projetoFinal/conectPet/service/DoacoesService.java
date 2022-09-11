package projetoFinal.conectPet.service;


import org.springframework.stereotype.Service;
import projetoFinal.conectPet.domain.dto.DoacaoCreateRequest;
import projetoFinal.conectPet.domain.dto.DoacaoUpdateRequest;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.exception.DoacaoInvalidoException;
import projetoFinal.conectPet.exception.DoacaoNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoacoesService {

    private static List<DoacaoEntity> listaDeDoacao;

    private static int proximoId = 1;

    public DoacoesService(){
        if (listaDeDoacao == null){
            listaDeDoacao = new ArrayList<>();
            listaDeDoacao.add(new DoacaoEntity(proximoId++,"Scooby","cachorro", 1 ,5 , 4  , new UsuarioEntity()));
        }
    }


    public DoacaoEntity criarDoacao (DoacaoCreateRequest doacoesCreateRequest){

        if (!doacoesCreateRequest.getEspecie().equalsIgnoreCase("cachorro") && !doacoesCreateRequest.getEspecie().equalsIgnoreCase("gato")){
            throw new DoacaoInvalidoException("Espécie autorizadas são: Cachorro e Gato");
        }

        var novaDoacao = new DoacaoEntity
                (proximoId++,
                 doacoesCreateRequest.getNome(),
                 doacoesCreateRequest.getEspecie(),
                 doacoesCreateRequest.getIdade(),
                 doacoesCreateRequest.getNivelDeFofura(),
                 doacoesCreateRequest.getNivelDeCarencia(),
                 doacoesCreateRequest.getUsuario()
                 );
        listaDeDoacao.add(novaDoacao);

        return novaDoacao;
    }

    public List<DoacaoEntity> listar(){
        return listaDeDoacao;
    }


    public DoacaoEntity buscarPorId(int idDoacao) {
        var doacaoEncontrada = listaDeDoacao.stream()
                .filter(doacao -> doacao.getId() == idDoacao)
                .findFirst();

        if(doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        return doacaoEncontrada.get();
    }

    //metodo buscarPorNome usado no Controller
    public DoacaoEntity buscarPorNome(String nome){
        var doacaoEncontrada = listaDeDoacao.stream()
                .filter(doacao -> doacao.getNome().equals(nome))
                .findFirst();

        if (doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        return doacaoEncontrada.get();
    }

    //metodo buscarPorUsuario usado no Controller
    public DoacaoEntity buscarDoacaoPorUsuario(String usuario){
        var doacaoEncontrada = listaDeDoacao.stream()
                .filter(doacao -> doacao.getUsuario().equals(usuario))
                .findAny();

        if (doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        return doacaoEncontrada.get();
    }


    public DoacaoEntity deletarDoacao (int idDoacao){
        var doacaoEncontrada = listaDeDoacao.stream()
                .filter(doacao -> doacao.getId() == idDoacao)
                .findFirst();

        if (doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        var doacao = doacaoEncontrada.get();
        listaDeDoacao.remove(doacao);

        return doacao;
    }

    public DoacaoEntity atualizarDoacao (int idDoacao , DoacaoUpdateRequest doacaoUpdateRequest){

        var doacaoEncontrada = listaDeDoacao.stream()
                .filter(doacao -> doacao.getId() == idDoacao)
                .findFirst();

        if (doacaoEncontrada.isEmpty()){
            throw new DoacaoNaoEncontradaException();
        }

        var doacao = doacaoEncontrada.get();
        doacao.setNome(doacaoUpdateRequest.getNome());
        doacao.setEspecie(doacaoUpdateRequest.getEspecie());
        doacao.setIdade(doacaoUpdateRequest.getIdade());
        doacao.setNivelDeFofura(doacaoUpdateRequest.getNivelDeFofura());
        doacao.setNivelDeCarencia(doacaoUpdateRequest.getNivelDeCarencia());

        return doacaoEncontrada.get();

    }



}
