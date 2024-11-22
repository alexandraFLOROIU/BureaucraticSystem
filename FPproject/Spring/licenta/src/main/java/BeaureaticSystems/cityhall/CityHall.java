package BeaureaticSystems.cityhall;

import BeaureaticSystems.document.DocumentType;
import BeaureaticSystems.office.Office;
import jakarta.persistence.Entity;

import java.util.*;

public class CityHall {
//    private static final int NUM_OFFICES = 3;
//    private static final int MIN_NUM_COUNTERS_PER_OFFICE = 3;
//    private static final int MAX_NUM_COUNTERS_PER_OFFICE = 5;
//    private static final Random rnd = new Random();
//
//    // Generarea tipurilor de documente
//    public List<DocumentType> generateDocumentTypes() {
//        DocumentType d1 = new DocumentType(1, List.of(), "Identity Card");
//        DocumentType d2 = new DocumentType(2, List.of(), "Account Statement");
//        DocumentType d3 = new DocumentType(3, List.of(), "Property Documents");
//        DocumentType d4 = new DocumentType(4, List.of(d1, d2), "Tax Certificate");
//        DocumentType d5 = new DocumentType(5, List.of(d1, d4), "Planning Certificate");
//        DocumentType d6 = new DocumentType(6, List.of(d4, d5), "Demolition Permit");
//        DocumentType d7 = new DocumentType(7, List.of(d1, d3), "Tax Assessment Decision");
//        DocumentType d8 = new DocumentType(8, List.of(d1, d3, d7), "Tax Payment Receipt");
//        DocumentType d9 = new DocumentType(9, List.of(d1, d4), "Building Permit");
//
//        return List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9);
//    }
//
//    // Generarea birourilor
//    public List<Office> generateOffices(List<DocumentType> documentTypes) {
//        List<Office> offices = new ArrayList<>();
//        int remainder = documentTypes.size() % NUM_OFFICES;
//        int countPerOffice = documentTypes.size() / NUM_OFFICES;
//        int start = 0;
//
//        for (int i = 0; i < NUM_OFFICES; ++i) {
//            int end = start + countPerOffice;
//            if (remainder > 0) {
//                end += 1;
//                remainder -= 1;
//            }
//            Office office = new Office(
//                    i,
//                    documentTypes.subList(start, end),
//                    rnd.nextInt(MIN_NUM_COUNTERS_PER_OFFICE, MAX_NUM_COUNTERS_PER_OFFICE + 1)
//            );
//            offices.add(office);
//            start = end;
//        }
//        return offices;
//    }
//
//    // Maparea tipurilor de documente cu birourile compatibile
//    public Map<DocumentType, Office> connectTypesAndOffices(List<DocumentType> documentTypes, List<Office> offices) {
//        Map<DocumentType, Office> typeOfficeMap = new HashMap<>();
//        for (DocumentType type : documentTypes) {
//            for (Office office : offices) {
//                if (office.getCompatibleDocumentTypes().contains(type)) {
//                    typeOfficeMap.put(type, office);
//                    break;
//                }
//            }
//        }
//        return typeOfficeMap;
//    }
//
//    // Obține documentele care necesită alte documente
//    public List<DocumentType> getDocumentsRequiringOtherDocuments(List<DocumentType> documentTypes) {
//        return documentTypes.stream().filter(documentType -> !documentType.getRequiredDocs().isEmpty()).toList();
//    }
}
