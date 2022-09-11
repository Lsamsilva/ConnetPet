package projetoFinal.conectPet.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "doacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false , length = 150)
    private String nome;

    @Column(nullable = false , length = 10)
    private String especie;

    @Column
    private Integer idade;

    @Column(nullable = false , name = "nivel_fofura")
    private Integer nivelDeFofura;

    @Column( nullable = false , name = "nivel_carencia")
    private Integer nivelDeCarencia;


    //relacionamento de muitas doacoes para 1 usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;
}