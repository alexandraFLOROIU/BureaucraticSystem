package BeaureaticSystems.document;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/document")
public class DocumentTypeController {

    private DocumentTypeService documentTypeService;

    @PostMapping()
    public ResponseEntity<DocumentType> createDocumentType(@RequestBody DocumentType docType) {
        documentTypeService.createDocumentType(docType);
        return new ResponseEntity<>(docType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentType> getDocumentTypeById(@PathVariable int id) {
        DocumentType d=documentTypeService.getDocumentTypeById(id);
        if(d==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(d, HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<List<DocumentType>> getAllDocumentTypes() {
//        List<DocumentType> docs = documentTypeService.getAllDocumentTypes();
//        if(docs==null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }else{
//            return new ResponseEntity<>(docs, HttpStatus.OK);
//        }
//    }

    @GetMapping()
    public ResponseEntity<List<DocumentType>> getAllDocumentTypes() {
        List<DocumentType> docs = documentTypeService.getAllDocumentTypes();
        if (docs == null || docs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Dacă documentele există, returnăm documentele cu dependențele lor
            return new ResponseEntity<>(docs, HttpStatus.OK);
        }
    }

}
