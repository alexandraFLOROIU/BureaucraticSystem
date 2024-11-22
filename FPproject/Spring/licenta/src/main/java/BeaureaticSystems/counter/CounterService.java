package BeaureaticSystems.counter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CounterService {
    private CounterRepository counterRepository;

    public void createCounter(Counter counter) {counterRepository.save(counter);}
}
