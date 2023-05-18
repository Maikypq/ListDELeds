package co.edu.manizales.listdeleds.controller;

import co.edu.manizales.listdeleds.controller.dto.LedDTO;
import co.edu.manizales.listdeleds.controller.dto.ResponseDTO;
import co.edu.manizales.listdeleds.exception.ListDEException;
import co.edu.manizales.listdeleds.model.Led;
import co.edu.manizales.listdeleds.service.ListDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/leds")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;


    @GetMapping
    public ResponseEntity<ResponseDTO> getLeds() {
        return new ResponseEntity<>(new ResponseDTO(
                200, listDEService.getLeds().print(), null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addLed(@RequestBody LedDTO ledDTO) throws ListDEException {
        Led newLed = new Led(ledDTO.getIdentification() , false);
        listDEService.getLeds().addLed(newLed);
        return new ResponseEntity<>(new ResponseDTO(200, "la bombilla fue añadida", null), HttpStatus.OK);
    }
    @PostMapping(path = "/add/{id}")
    public ResponseEntity<ResponseDTO> addLedToEnd(@PathVariable int id){

        listDEService.getLeds().addLedToEnd(new Led(id,false));
        return new ResponseEntity<>(new ResponseDTO(
                200, "la bombilla fue añadida", null), HttpStatus.OK);

    }


    @GetMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Led led){

        listDEService.getLeds().addLedToStart(led);
        return new ResponseEntity<>(new ResponseDTO(
                200, "la bombilla fue añadida al inicio", null), HttpStatus.OK);

    }


    @GetMapping(path = "/lightUpMiddleLeds")
    public ResponseEntity<ResponseDTO> lightUpMiddleLeds() {

        listDEService.getLeds().lightUpMiddleLeds();

        return new ResponseEntity<>(new ResponseDTO(
                200, "se encendieron las luces empezando por la mitad", null), HttpStatus.OK);
    }
    @GetMapping(path = "/resetList")
    public ResponseEntity<ResponseDTO> resetList(){
        listDEService.getLeds().resetList();
        return new ResponseEntity<>(new ResponseDTO(200, "la bombillas reiniciaron su estado", null), HttpStatus.OK);
    }
}

