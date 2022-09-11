package projetoFinal.conectPet.domain.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoacaoUpdateRequest {

    private String nome;

    private String especie;

    private Integer idade;

    private Integer nivelDeFofura;

    private Integer nivelDeCarencia;

}
