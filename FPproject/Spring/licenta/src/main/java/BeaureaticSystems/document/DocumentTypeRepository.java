package BeaureaticSystems.document;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentTypeRepository extends CrudRepository<DocumentType, Integer> {
    List<DocumentType> findAll();
}
