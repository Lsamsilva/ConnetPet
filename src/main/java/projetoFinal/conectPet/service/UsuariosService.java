package projetoFinal.conectPet.service;

import org.springframework.stereotype.Service;
import projetoFinal.conectPet.domain.dto.UsuarioCreateRequest;
import projetoFinal.conectPet.domain.dto.UsuarioResponse;
import projetoFinal.conectPet.domain.dto.UsuarioUpdateRequest;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.exception.UsuarioNaoEncontradoException;
import projetoFinal.conectPet.repository.UsuariosRepository;

@Service
public class UsuariosService {

    private final UsuariosRepository repository;

    public UsuariosService (final UsuariosRepository repository){this.repository = repository;}

    public UsuarioResponse criarUsuario (UsuarioCreateRequest usuarioCreateRequest){

        var novoUsuario = new UsuarioEntity();
        novoUsuario.setNome(usuarioCreateRequest.getNome());
        novoUsuario.setCpf(usuarioCreateRequest.getCpf());
        novoUsuario.setEmail(usuarioCreateRequest.getEmail());
        novoUsuario.setCidade(usuarioCreateRequest.getCidade());
        novoUsuario.setEstado(usuarioCreateRequest.getEstado());


        var usuarioSalvo = repository.save(novoUsuario);
        return new UsuarioResponse(
                usuarioSalvo.getId(),
                usuarioCreateRequest.getNome(),
                usuarioCreateRequest.getCpf(),
                usuarioCreateRequest.getDataNascimento(),
                usuarioCreateRequest.getEmail(),
                usuarioCreateRequest.getCidade(),
                usuarioCreateRequest.getEstado()
        );
    }

    public UsuarioResponse buscarPorId(Integer idUsuario) {
        var usuarioEncontrado = repository.findById(idUsuario);

        if(usuarioEncontrado.isEmpty()){
           //throw new UsuarioNaoEncontradoException();
        }

       var usuarioSalvo = usuarioEncontrado.get();
        return new UsuarioResponse(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getCpf(),
                usuarioSalvo.getDataNascimento(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getCidade(),
                usuarioSalvo.getEstado()
        );
    }

    public UsuarioResponse buscarPorNome(String nome){

        try{
            var nomeEncontrado = repository.findByNome(nome);

            return new UsuarioResponse(
                    nomeEncontrado.getId(),
                    nomeEncontrado.getNome(),
                    nomeEncontrado.getCpf(),
                    nomeEncontrado.getDataNascimento(),
                    nomeEncontrado.getEmail(),
                    nomeEncontrado.getCidade(),
                    nomeEncontrado.getEstado()
            );
        }catch (UsuarioNaoEncontradoException e){
            throw new UsuarioNaoEncontradoException();
        }
    }


    /*public void deletarUsuario(Integer idUsuario ){

        try{
            projetoFinal.conectPet.repository.deleteById(idUsuario);

            return UsuarioResponse(

            );
        }
        catch (UsuarioNaoEncontradoException e){
            throw new UsuarioNaoEncontradoException();
        }
    }*/

    public UsuarioResponse atualizarUsuario(Integer idUsuario , UsuarioUpdateRequest usuarioUpdateResquest){

        var usuarioEncontrado = repository.findById(idUsuario);

        if (usuarioEncontrado.isEmpty()){
            throw new UsuarioNaoEncontradoException();
        }

        var usuarioAtualizado = usuarioEncontrado.get();
        usuarioAtualizado.setEmail(usuarioUpdateResquest.getEmail());
        usuarioAtualizado.setCidade(usuarioUpdateResquest.getCidade());
        usuarioAtualizado.setEstado(usuarioUpdateResquest.getEstado());

        var usuarioSalvo = repository.save(usuarioAtualizado);
        return new UsuarioResponse(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getDataNascimento(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getCidade(),
                usuarioSalvo.getEstado()
        );

    }


}
