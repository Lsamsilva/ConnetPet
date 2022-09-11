package projetoFinal.conectPet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DoacaoNaoEncontradaException extends RuntimeException {
    public DoacaoNaoEncontradaException (){super("Não foi encontrado animal para adoção");}
}
