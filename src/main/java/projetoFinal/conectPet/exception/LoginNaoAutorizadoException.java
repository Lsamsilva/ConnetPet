package projetoFinal.conectPet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LoginNaoAutorizadoException extends RuntimeException{
    public LoginNaoAutorizadoException (){super("Login não autorizado.Tente novamente.");}
}
