package jaquesprojeto.gof.repository;

import jaquesprojeto.gof.model.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// O Spring Data JPA fornece uma interface CrudRepository que possui métodos CRUD prontos para uso.
@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
