package projetoFinal.conectPet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DoacaoInvalidoException extends RuntimeException{
    public DoacaoInvalidoException(String message){super(message);}
}
