package BeaureaticSystems.office;

import BeaureaticSystems.document.DocumentType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OfficeController {
    private OfficeService officeService;

    @PostMapping("/create-office")
    public ResponseEntity createOffice(@RequestBody Office office) {
        officeService.createOffice(office);
        return new ResponseEntity<>(office, HttpStatus.CREATED);
    }
}
