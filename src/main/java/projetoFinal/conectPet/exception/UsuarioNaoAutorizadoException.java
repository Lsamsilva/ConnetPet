package projetoFinal.conectPet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
public class UsuarioNaoAutorizadoException extends RuntimeException {
    public UsuarioNaoAutorizadoException(){
        super("Não foi possível cadastrar usuário por questão legais");
    }
}
