package escola.ti.controleparental.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceitaDTO {
    private String nome;
    private Integer tempoPreparo;
    private Number custoAproximado;
}
