package BeaureaticSystems.document;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentTypeService {
    private DocumentTypeRepository documentTypeRepository;

    public void createDocumentType(DocumentType documentType) {
        documentTypeRepository.save(documentType);
    }
}
