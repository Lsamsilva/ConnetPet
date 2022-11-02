package projetoFinal.conectPet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private String cidade;
    private String estado;

}
