package co.edu.manizales.listdeleds.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalTime;
@Data
@AllArgsConstructor

public class Led {
    private int id;
    private boolean state;
    private LocalTime dateOn;
    private LocalTime dateOff;

    public Led(int id, boolean states) {
        this.id = id;
        this.state = states;
    }
}

