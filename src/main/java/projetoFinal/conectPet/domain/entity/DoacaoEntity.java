package projetoFinal.conectPet.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "tb_doacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false , length = 150)
    private String nome;

    @Column(nullable = false , length = 10)
    private String especie;

    @Column
    private Integer idade;

    @Column(nullable = false , name = "sexo")
    private String sexo;

    @Email
    @Column(nullable = false)
    private String email;

    @Column( nullable = false , name = "imagem")
    private String imagem;

    //relacionamento de muitas doacoes para 1 usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
}
