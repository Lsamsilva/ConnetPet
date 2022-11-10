package projetoFinal.conectPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetoFinal.conectPet.domain.entity.UsuarioEntity;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuarioEntity , Integer> {

    UsuarioEntity findByNome(String nome);

}
