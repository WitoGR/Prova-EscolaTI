package escola.ti.controleparental.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateIngredienteDTO {
    private String nomeReceita;
    private String nomeIngrediente;
    private String novoNomeIngrediente;
}
