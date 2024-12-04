package BeaureaticSystems.counter;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CounterService {
    private CounterRepository counterRepository;

    public void createCounter(Counter counter) {counterRepository.save(counter);}

    @PostConstruct
    public void initializeDefaultStatus() {
        Iterable<Counter> counters = counterRepository.findAll();
        for (Counter counter : counters) {
            if (counter.getStatus() == null) {
                counter.setStatus(Counter.Status.AVAILABLE);
                counterRepository.save(counter);
            }
        }
    }
    public Counter getCounterById(int id)
    {
        return counterRepository.findById(id)
                .orElse(null);
    }
}
