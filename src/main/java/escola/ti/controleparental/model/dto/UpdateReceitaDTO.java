package escola.ti.controleparental.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReceitaDTO {
    private String nome;
    private String novoNome;
    private Integer novoTempo;
    private Number novoValor;

}
