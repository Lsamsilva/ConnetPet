package projetoFinal.conectPet.domain.dto;

import lombok.Data;

@Data
public class UsuarioUpdateRequest {

    private String email;

    private String cidade;

    private String estado;

}
