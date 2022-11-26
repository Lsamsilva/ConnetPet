package projetoFinal.conectPet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoUpdateRequestDTO {

    private String nome;
    private String especie;
    private Integer idade;
    private Integer nivelDeFofura;
    private Integer nivelDeCarencia;

}
