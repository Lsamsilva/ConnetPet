package projetoFinal.conectPet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoResponseDTO {

    private Integer id;
    private String nome;
    private String especie;
    private Integer idade;
    private String sexo;
    private String email;
    private String imagem;
    private UsuarioEntity usuario;

}
