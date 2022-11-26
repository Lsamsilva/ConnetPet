package projetoFinal.conectPet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateRequestDTO {

    @NotEmpty(message = "Nome deve ser definido.")
    private String nome;

    @NotEmpty(message = "Número do documento deve ser definido.")
    private String cpf;

    @NotNull(message = "Data de Nascimento deve ser definida.")
    private Date dataNascimento;

    @NotEmpty(message = "E-mail deve ser definido.")
    private String email;

    @NotEmpty(message = "Cidade deve ser definida.")
    private String cidade;

    @NotEmpty(message = "Estado deve ser definido.")
    private String estado;

    @NotNull(message = "Senha deve ser definida.")
    private Integer senha;

}
