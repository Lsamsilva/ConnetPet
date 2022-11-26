package projetoFinal.conectPet.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Date;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , length = 150)
    private String nome;

    @CPF
    @Column(nullable = false , length = 11)
    private String cpf;

    @Column(name = "data_nascimento" , nullable = false)
    private Date dataNascimento;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false , length = 50)
    private String cidade;

    @Column(nullable = false , length = 50)
    private String estado;

    @Column(nullable = false , length = 6)
    private Integer senha;

}
