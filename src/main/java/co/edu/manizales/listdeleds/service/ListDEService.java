package co.edu.manizales.listdeleds.service;
import co.edu.manizales.listdeleds.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@Data
public class ListDEService {
    private ListDE leds;

    public ListDEService(){leds=new ListDE();}

}





