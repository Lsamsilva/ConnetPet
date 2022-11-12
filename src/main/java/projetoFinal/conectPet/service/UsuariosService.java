package projetoFinal.conectPet.service;

import org.springframework.stereotype.Service;
import projetoFinal.conectPet.domain.dto.UsuarioCreateRequest;
import projetoFinal.conectPet.domain.dto.UsuarioResponse;
import projetoFinal.conectPet.domain.dto.UsuarioUpdateRequest;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.exception.UsuarioNaoAutorizadoException;
import projetoFinal.conectPet.exception.UsuarioNaoEncontradoException;
import projetoFinal.conectPet.repository.UsuariosRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class UsuariosService {

    private final UsuariosRepository repository;

    public UsuariosService (final UsuariosRepository repository){this.repository = repository;}

    public UsuarioResponse criarUsuario (UsuarioCreateRequest usuarioCreateRequest){

        var novoUsuario = new UsuarioEntity();
        novoUsuario.setNome(usuarioCreateRequest.getNome());
        novoUsuario.setCpf(usuarioCreateRequest.getCpf());
        novoUsuario.setEmail(usuarioCreateRequest.getEmail());
        novoUsuario.setDataNascimento(usuarioCreateRequest.getDataNascimento());
        novoUsuario.setCidade(usuarioCreateRequest.getCidade());
        novoUsuario.setEstado(usuarioCreateRequest.getEstado());

        Date data = novoUsuario.getDataNascimento();
        int idadeAtual = calculaIdade(data);

        if (idadeAtual < 18){
            throw new UsuarioNaoAutorizadoException();
        }

        var usuarioSalvo = repository.save(novoUsuario);
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

    public static int calculaIdade(java.util.Date dataNasc){

        Calendar dataNascimento = Calendar.getInstance();
        dataNascimento.setTime(dataNasc);

        Calendar hoje = Calendar.getInstance();

        int idade = hoje.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (hoje.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH)){
            idade--;
        }else{
            if (hoje.get(Calendar.MONTH) == dataNascimento.get(Calendar.MONTH) && hoje.get(Calendar.DAY_OF_MONTH)
                    < dataNascimento.get(Calendar.DAY_OF_MONTH)){
                idade--;
            }
        }

        return idade;
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
