package projetoFinal.conectPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuarioEntity , Integer> {

    UsuarioEntity findByNome(String nome);

    @Query(value = "SELECT * FROM tb_usuario WHERE cpf = :cpf AND senha = :senha" , nativeQuery = true)
    Optional<UsuarioEntity> findByCpfAndSenha(@Param("cpf") String cpf ,
                                              @Param("senha") Integer senha);
}
