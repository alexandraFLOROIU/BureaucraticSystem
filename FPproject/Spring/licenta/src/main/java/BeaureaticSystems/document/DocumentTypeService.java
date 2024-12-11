package BeaureaticSystems.document;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<DocumentType> getAllDocumentTypes() {
        Optional<List<DocumentType>> docs = Optional.of(documentTypeRepository.findAll());
        if (docs.isEmpty()) {
            return null;
        }
        List<DocumentType> finalDocumentTypes = new ArrayList<>();
        for (DocumentType documentType : docs.get()) {
            if (!documentType.getRequiredDocs().isEmpty()) {
                finalDocumentTypes.add(documentType);
            }
        }
        return finalDocumentTypes;
    }

}
