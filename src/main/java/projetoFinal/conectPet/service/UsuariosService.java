package projetoFinal.conectPet.service;

import org.springframework.stereotype.Service;
import projetoFinal.conectPet.domain.dto.UsuarioCreateRequestDTO;
import projetoFinal.conectPet.domain.dto.UsuarioResponseDTO;
import projetoFinal.conectPet.domain.dto.UsuarioUpdateRequestDTO;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;
import projetoFinal.conectPet.exception.UsuarioNaoAutorizadoException;
import projetoFinal.conectPet.exception.UsuarioNaoEncontradoException;
import projetoFinal.conectPet.repository.UsuariosRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Service
public class UsuariosService {

    private final UsuariosRepository repository;

    public UsuariosService(final UsuariosRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponseDTO criarUsuario(UsuarioCreateRequestDTO usuarioCreateRequest) {

        var novoUsuario = new UsuarioEntity();
        novoUsuario.setNome(usuarioCreateRequest.getNome());
        novoUsuario.setCpf(usuarioCreateRequest.getCpf());
        novoUsuario.setEmail(usuarioCreateRequest.getEmail());
        novoUsuario.setDataNascimento(usuarioCreateRequest.getDataNascimento());
        novoUsuario.setCidade(usuarioCreateRequest.getCidade());
        novoUsuario.setEstado(usuarioCreateRequest.getEstado());
        novoUsuario.setSenha(usuarioCreateRequest.getSenha());

        Date data = novoUsuario.getDataNascimento();
        int idadeAtual = calcularIdade(data);

        if (idadeAtual < 18) {
            throw new UsuarioNaoAutorizadoException();
        }

        var usuarioSalvo = repository.save(novoUsuario);
        return new UsuarioResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getCpf(),
                usuarioSalvo.getDataNascimento(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getCidade(),
                usuarioSalvo.getEstado()
        );

    }

    private int calcularIdade(Date dataNascimento) {
        LocalDate today = LocalDate.now();
        LocalDate birthdate = dataNascimento.toLocalDate();
        Period period = Period.between(birthdate, today);

        return period.getYears();
    }


    /*public static int calculaIdade(java.sql.Date dataNasc){

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
    }*/

    public UsuarioResponseDTO buscarPorId(Integer idUsuario) {

        var usuarioEncontrado = repository.findById(idUsuario);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        var usuarioSalvo = usuarioEncontrado.get();
        return new UsuarioResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getCpf(),
                usuarioSalvo.getDataNascimento(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getCidade(),
                usuarioSalvo.getEstado()
        );
    }

    public UsuarioResponseDTO verificarLogin(String cpf, Integer senha) {
        var usuarioEncontrado = repository.findByCpfAndSenha(cpf, senha);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioNaoAutorizadoException();
        }

        var usuario = usuarioEncontrado.get();
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getEmail(),
                usuario.getCidade(),
                usuario.getEstado()
        );

    }

    public UsuarioResponseDTO buscarPorNome(String nome) {

        try {
            var nomeEncontrado = repository.findByNome(nome);

            return new UsuarioResponseDTO(
                    nomeEncontrado.getId(),
                    nomeEncontrado.getNome(),
                    nomeEncontrado.getCpf(),
                    nomeEncontrado.getDataNascimento(),
                    nomeEncontrado.getEmail(),
                    nomeEncontrado.getCidade(),
                    nomeEncontrado.getEstado()
            );
        } catch (UsuarioNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException();
        }
    }


    public UsuarioResponseDTO atualizarUsuario(Integer idUsuario, UsuarioUpdateRequestDTO usuarioUpdateResquest) {

        var usuarioEncontrado = repository.findById(idUsuario);

        if (usuarioEncontrado.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        var usuarioAtualizado = usuarioEncontrado.get();
        usuarioAtualizado.setEmail(usuarioUpdateResquest.getEmail());
        usuarioAtualizado.setCidade(usuarioUpdateResquest.getCidade());
        usuarioAtualizado.setEstado(usuarioUpdateResquest.getEstado());

        var usuarioSalvo = repository.save(usuarioAtualizado);
        return new UsuarioResponseDTO(
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
