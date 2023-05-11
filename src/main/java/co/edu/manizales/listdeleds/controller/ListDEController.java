package co.edu.manizales.listdeleds.controller;

import co.edu.manizales.listdeleds.controller.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> addLED(@RequestBody Led led) {
        listDEService.getLeds().addLed(led);
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha añadido el led", null),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllLEDs() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLeds(), null), HttpStatus.OK);
    }

    @PostMapping("/start")
    public ResponseEntity<String> addLEDToStart(@RequestBody Led led) {
        listDEService.getLeds().addLedToStart(led);
        return ResponseEntity.ok("LED añadido al inicio");
    }

    @PostMapping("/end")
    public ResponseEntity<String> addLEDToEnd(@RequestBody Led led) {
        listDEService.getLeds().addLedToEnd(led);
        return ResponseEntity.ok("LED añadido al final");
    }

    @GetMapping ("/reset")
    public ResponseEntity<String> resetList() {
        listDEService.getLeds().resetList();
        return ResponseEntity.ok("Lista reseteada");
    }

    @GetMapping("/lightup")
    public ResponseEntity<String> lightUpMiddleLeds() {
        try {
            listDEService.getLeds().lightUpMiddleLeds();
            return ResponseEntity.ok("Los leds se prendieron");
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al encender los LED");
        }
    }
}
