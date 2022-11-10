package projetoFinal.conectPet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoResponse {

    private Integer id;
    private String nome;
    private String especie;
    private Integer idade;
    private Integer nivelDeFofura;
    private Integer nivelDeCarencia;
    private UsuarioEntity usuario;

}
