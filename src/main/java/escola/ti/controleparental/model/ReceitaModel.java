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
@Table(name="receita")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReceitaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_receita")
    private Integer idReceita;

    @Column(name="nome_receita")
    @Basic (optional = false)
    private String nome;

    @Column(name="tempo_preparo")
    @Basic (optional = true)
    private Integer tempoPreparo;

    @Column(name="custo_aproximado")
    @Basic (optional = true)
    private Number custoAproximando;
}
