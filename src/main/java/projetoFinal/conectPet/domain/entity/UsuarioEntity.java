package projetoFinal.conectPet.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , length = 150)
    private String nome;

    @Column(nullable = false , length = 11)
    private String cpf;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false , length = 50)
    private String cidade;

    @Column(nullable = false , length = 50)
    private String estado;


}
