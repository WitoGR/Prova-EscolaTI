package escola.ti.controleparental.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ingrediente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IngredienteModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ingrediente")
    private Integer idIngrediente;

    @Column(name = "id_receita")
    private Integer idReceita;

    @Column(name="nome_ingrediente")
    @Basic (optional = false)
    private String nome;
}
