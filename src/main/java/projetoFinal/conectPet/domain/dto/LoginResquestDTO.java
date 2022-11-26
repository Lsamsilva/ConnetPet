package projetoFinal.conectPet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResquestDTO {

    @NotEmpty(message = "Nome deve ser definido.")
    private String cpf;

    @NotNull(message = "Senha deve ser definida.")
    private Integer senha;

}
