package BeaureaticSystems.office;

import BeaureaticSystems.clients.Client;
import BeaureaticSystems.counter.Counter;
import BeaureaticSystems.counter.CounterService;
import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.document.DocumentTypeService;
import BeaureaticSystems.errors.ErrorResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@AllArgsConstructor
@RequestMapping("/office")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @Autowired
    private DocumentTypeService documentTypeService;

    @Autowired
    private CounterService counterService;

    @PostMapping
    public ResponseEntity createOffice(@RequestBody Office office) {
        officeService.createOffice(office);
        return new ResponseEntity<>(office, HttpStatus.CREATED);
    }

    @PostMapping("/document")
    public ResponseEntity requestDocument(
          //  @PathVariable int officeId,
            @RequestParam int clientId,
            @RequestParam int documentId
    ) {
        try {
            Client client = officeService.requestDocument(clientId, documentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity getAllOffices() {
        Iterable<Office> o = officeService.getAllOffices();
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @PatchMapping("{officeId}/document")
    public ResponseEntity updateCompatibleDocumentTypes(
            @PathVariable int officeId,
            @RequestBody DocumentType documentType) {
        // Găsește Office-ul după ID
        Office office = officeService.getOfficeById(officeId);
        if (office==null) {
            ErrorResponse errorResponse = new ErrorResponse("Office not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }

        DocumentType doc = documentTypeService.getDocumentTypeById(documentType.getId());
        if (doc==null) {
            ErrorResponse errorResponse = new ErrorResponse("Document not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }

        if(office.getCompatibleDocumentTypes().contains(doc)){
            ErrorResponse errorResponse = new ErrorResponse("Document already compatible");
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);
        }
        office.getCompatibleDocumentTypes().add(doc);
        // Salvează Office-ul actualizat
        Office updatedOffice = officeService.saveOffice(office);

        // Returnează Office-ul actualizat
        return ResponseEntity.ok(updatedOffice);
    }

    @PatchMapping("{officeId}/counter")
    public ResponseEntity updateCounters(
            @PathVariable int officeId,
            @RequestBody Counter counter) {
        // Găsește Office-ul după ID
        Office office = officeService.getOfficeById(officeId);
        if (office==null) {
            ErrorResponse errorResponse = new ErrorResponse("Office not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }

        Counter cnt = counterService.getCounterById(counter.getId());
        if (cnt==null) {
            ErrorResponse errorResponse = new ErrorResponse("Counter not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
        Iterable<Office> o = officeService.getAllOffices();
        for(Office o1 : o) {
            if (office.getCounters().contains(cnt)) {
                ErrorResponse errorResponse = new ErrorResponse("Counter already exists in another office");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(errorResponse);
            }
        }
        office.getCounters().add(cnt);
        // Salvează Counter-ul actualizat
        Office updatedOffice = officeService.saveOffice(office);

        // Returnează Counter-ul actualizat
        return ResponseEntity.ok(updatedOffice);
    }
}
