package escola.ti.controleparental.model.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceitasViewDTO {
    private String nome;
    private Integer tempoPreparo;
    private Number custoAproximado;
    private ArrayList<String> ingredientes = new ArrayList<>();

    public void addIngrediente(String nome){
        ingredientes.add(nome);
    }
}
