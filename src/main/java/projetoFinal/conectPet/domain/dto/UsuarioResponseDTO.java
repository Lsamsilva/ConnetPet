package projetoFinal.conectPet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String email;
    private String cidade;
    private String estado;

}
