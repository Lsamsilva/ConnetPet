package projetoFinal.conectPet.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import projetoFinal.conectPet.domain.dto.LoginResponse;
import projetoFinal.conectPet.exception.LoginNaoAutorizadoException;
import projetoFinal.conectPet.repository.UsuariosRepository;

@Service
public class LoginService {

    private final UsuariosRepository repository;

    private LoginService (final UsuariosRepository repository){this.repository = repository;}

    public LoginResponse buscarPorLogin (String login , Integer senha){

         try {
             var cpfEncontrado = repository.findByCpf(login);
             var senhaEncontrada = repository.findBySenha(senha);

             return new LoginResponse(
                     cpfEncontrado.getCpf(),
                     senhaEncontrada.getSenha()
             );
         }catch (LoginNaoAutorizadoException e){
             throw new LoginNaoAutorizadoException();
         }

    }

}
