package projetoFinal.conectPet.domain.dto;

import lombok.Data;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DoacaoCreateRequestDTO {

    @NotEmpty(message = "Nome deve ser definido.")
    private String nome;

    @NotEmpty(message = "Tipo deve ser definido.")
    private String especie;

    @NotNull(message = "Idade deve ser definida.")
    private Integer idade;

    @NotNull(message = "Nível de Fofura deve ser definido.")
    private Integer nivelDeFofura;

    @NotNull(message = "Nível de Fofura deve ser definido.")
    private Integer nivelDeCarencia;

    private UsuarioEntity usuario;

}
