package BeaureaticSystems.document;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentTypeService {
    private DocumentTypeRepository documentTypeRepository;

    public void createDocumentType(DocumentType documentType) {
        documentTypeRepository.save(documentType);
    }

    public DocumentType getDocumentTypeById(int id) {
        Optional<DocumentType> documentType = documentTypeRepository.findById(id);
        return documentType.orElse(null);
    }


}
