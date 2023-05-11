package co.edu.manizales.listdeleds.controller;

import co.edu.manizales.listdeleds.model.Led;
import co.edu.manizales.listdeleds.model.ListDE;
import co.edu.manizales.listdeleds.service.ListDEService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leds")
public class ListDEController {
    private final ListDEService listDEService;

    public ListDEController(ListDEService listDEService) {
        this.listDEService = listDEService;
    }
    @PostMapping
    public ResponseEntity<String> addLED(@RequestBody Led led) {
        listDEService.addLed(led);
        return ResponseEntity.ok("LED added successfully");
    }

    @PostMapping("/start")
    public ResponseEntity<String> addLEDToStart(@RequestBody Led led) {
        listDEService.addLedToStart(led);
        return ResponseEntity.ok("LED added to the start successfully");
    }

    @PostMapping("/end")
    public ResponseEntity<String> addLEDToEnd(@RequestBody Led led) {
        listDEService.addLedToEnd(led);
        return ResponseEntity.ok("LED added to the end successfully");
    }

    @GetMapping ("/reset")
    public ResponseEntity<String> resetList() {
        listDEService.resetList();
        return ResponseEntity.ok("List reset successfully");
    }

    @GetMapping("/lightup")
    public ResponseEntity<String> lightUpMiddleLeds() {
        try {
            listDEService.lightUpMiddleLeds();
            return ResponseEntity.ok("LEDs lighted up successfully");
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while lighting up LEDs");
        }
    }
}
