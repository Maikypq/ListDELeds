package co.edu.manizales.listdeleds.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Led {
    private int id;
    private boolean state;
    private String dateOn;
    private String dateOff;
}