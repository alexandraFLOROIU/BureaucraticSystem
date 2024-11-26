package BeaureaticSystems.counter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    private CounterService counterService;

    @PostMapping("/create-counter")
    public ResponseEntity<Counter> createCounter(@RequestBody Counter counter) {
        counterService.createCounter(counter);
        return new ResponseEntity<>(counter, HttpStatus.CREATED);
    }
}
