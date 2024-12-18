package BeaureaticSystems.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;

@RestController
@RequestMapping("/counter")
public class CounterController {
    @Autowired
    private CounterService counterService;

    @PostMapping
    public ResponseEntity<Counter> createCounter(@RequestBody Counter counter) {
        counterService.createCounter(counter);
        return new ResponseEntity<>(counter, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCounter(@PathVariable int id) {
        Counter counter = counterService.getCounterById(id);
        if (counter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Counter with ID " + id + " not found");
        }
        counterService.deleteCounterById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Counter with ID " + id + " has been deleted successfully");
    }

}
