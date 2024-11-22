package BeaureaticSystems.cityhall;

import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.office.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cityhall")
public class CityHallController {

//    private final CityHallService cityHallService;
//    private final CityHall cityHall = new CityHall();
//
//    public CityHallController(CityHallService cityHallService) {
//        this.cityHallService = cityHallService;
//    }
//
//    // Endpoint pentru a obține toate tipurile de documente
//    @GetMapping("/documents")
//    public String getAllDocumentTypes(Model model) {
//        List<DocumentType> documentTypes = cityHall.generateDocumentTypes();
//        model.addAttribute("documentTypes", documentTypes);
//        return "documents"; // numele paginii HTML care trebuie afișată
//    }
//
//    // Endpoint pentru a obține toate birourile
//    @GetMapping("/offices")
//    public String getAllOffices(Model model) {
//        List<Office> offices = cityHallService.generateOffices();
//        //model.addAttribute("offices", offices);
//        return "offices"; // numele paginii HTML care trebuie afișată
//    }
//
//    // Endpoint pentru a obține conexiunea dintre tipurile de documente și birouri
//    @GetMapping("/connections")
//    public String getDocumentToOfficeMappings(Model model) {
//        Map<DocumentType, Office> connections = cityHallService.connectTypesAndOffices();
//        //model.addAttribute("connections", connections);
//        return "connections"; // numele paginii HTML care trebuie afișată
//    }
//
//    // Endpoint pentru documentele care necesită alte documente
//    @GetMapping("/documents/requirements")
//    public String getDocumentsRequiringOtherDocuments(Model model) {
//        List<DocumentType> documentsRequiringOtherDocs = cityHallService.getDocumentsRequiringOtherDocuments();
//        //model.addAttribute("requiredDocuments", documentsRequiringOtherDocs);
//        return "requirements"; // numele paginii HTML care trebuie afișată
//    }
}