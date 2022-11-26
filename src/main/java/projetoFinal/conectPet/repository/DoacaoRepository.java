package projetoFinal.conectPet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projetoFinal.conectPet.domain.entity.DoacaoEntity;

@Repository
public interface DoacaoRepository extends JpaRepository<DoacaoEntity , Integer> {
    DoacaoEntity findByNome (String nome);
}
