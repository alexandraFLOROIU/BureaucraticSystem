package BeaureaticSystems.office;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfficeService {
    private OfficeRepository officeRepository;
    public void createOffice(Office office){
        officeRepository.save(office);
    }
}
