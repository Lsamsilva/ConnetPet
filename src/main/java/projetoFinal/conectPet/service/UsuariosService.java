package projetoFinal.conectPet.service;

import org.springframework.stereotype.Service;
import projetoFinal.conectPet.domain.dto.DoacaoUpdateRequest;
import projetoFinal.conectPet.domain.dto.UsuarioCreateRequest;
import projetoFinal.conectPet.domain.dto.UsuarioUpdateRequest;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.exception.DoacaoNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuariosService {

    private static List<UsuarioEntity> listaDeUsuarios;

    private static int proximoId = 1;

    public UsuariosService(){
        if (listaDeUsuarios == null){
            listaDeUsuarios = new ArrayList<>();
            listaDeUsuarios.add(new UsuarioEntity(proximoId++, "Amanda", "99999999999","01/01/2000","mail@mail.com","São Paulo","SP" ));
        }
    }

    public UsuarioEntity criarUsuario (UsuarioCreateRequest usuarioCreateRequest){

        var novoUsuario = new UsuarioEntity(
                proximoId++,
                usuarioCreateRequest.getNome(),
                usuarioCreateRequest.getCpf(),
                usuarioCreateRequest.getDataNascimento(),
                usuarioCreateRequest.getEmail(),
                usuarioCreateRequest.getCidade(),
                usuarioCreateRequest.getEstado()
        );
        listaDeUsuarios.add(novoUsuario);

        return novoUsuario;
    }

    public List<UsuarioEntity> listar(){
        return listaDeUsuarios;
    }

    public UsuarioEntity buscarPorId(int idUsuario) {
        var usuarioEncontrado = listaDeUsuarios.stream()
                .filter(usuario -> usuario.getId() == idUsuario)
                .findFirst();

        if(usuarioEncontrado.isEmpty()){
           //throw new UsuarioNaoEncontradoException(); - fazer classe exceção
        }

        return usuarioEncontrado.get();
    }

    public UsuarioEntity buscarPorNome(String nome){
        var usuarioEncontrado = listaDeUsuarios.stream()
                .filter(usuario -> usuario.getNome().equals(nome))
                .findFirst();

        if(usuarioEncontrado.isEmpty()){
            //throw new UsuarioNaoEncontradoException(); - fazer classe exceção
        }

        return usuarioEncontrado.get();
    }

    public UsuarioEntity deletarUsuario(int idUsuario){
        var usuarioEncontrado = listaDeUsuarios.stream()
                .filter(usuario -> usuario.getId() == idUsuario)
                .findFirst();

        if(usuarioEncontrado.isEmpty()){
            //throw new UsuarioNaoEncontradoException(); - fazer classe exceção
        }

        var usuario = usuarioEncontrado.get();
        listaDeUsuarios.remove(usuario);

        return usuario;
    }

    public UsuarioEntity atualizarUsuario (int idUsuario , UsuarioUpdateRequest usuarioUpdateRequest){

        var usuarioEncontrado = listaDeUsuarios.stream()
                .filter(usuario -> usuario.getId() == idUsuario)
                .findFirst();

        if(usuarioEncontrado.isEmpty()){
            //throw new UsuarioNaoEncontradoException(); - fazer classe exceção
        }

        var usuario = usuarioEncontrado.get();
            usuario.setCidade(usuarioUpdateRequest.getCidade());
            usuario.setEstado(usuarioUpdateRequest.getEstado());

        return usuarioEncontrado.get();

    }


}
