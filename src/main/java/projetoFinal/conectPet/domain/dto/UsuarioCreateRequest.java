package projetoFinal.conectPet.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class UsuarioCreateRequest {

    @NotEmpty(message = "Nome deve ser definido.")
    private String nome;

    @NotEmpty(message = "Número do documento deve ser definido.")
    private String cpf;

    @NotEmpty(message = "Data de Nascimento deve ser definida.")
    private String dataNascimento;

    @NotEmpty(message = "E-mail deve ser definido.")
    private String email;

    @NotEmpty(message = "Cidade deve ser definida.")
    private String cidade;

    @NotEmpty(message = "Estado deve ser definido.")
    private String estado;

}
