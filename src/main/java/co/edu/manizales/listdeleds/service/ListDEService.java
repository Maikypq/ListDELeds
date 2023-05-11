package co.edu.manizales.listdeleds.service;

import co.edu.manizales.listdeleds.model.Led;
import co.edu.manizales.listdeleds.model.ListDE;
import co.edu.manizales.listdeleds.model.Node;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
@Service
@Data
public class ListDEService {
    private Node head;
    private Node tail;
    private int size;
    private ListDE leds;


    public ListDEService() {
        this.leds = new ListDE();
    }

    }




