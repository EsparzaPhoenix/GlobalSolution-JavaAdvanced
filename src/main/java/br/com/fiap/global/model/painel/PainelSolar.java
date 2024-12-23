package br.com.fiap.global.model.painel;

import br.com.fiap.global.model.painel.enums.TipoPainel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="GS_JV_PAINEL_SOLAR")
@Getter @Setter @NoArgsConstructor
@SequenceGenerator(name="seq_painel", sequenceName="seq_JV_painel", allocationSize=1, initialValue = 1)
public class PainelSolar {

    @Id
    @GeneratedValue
    @Column(name = "cd_painel")
    private Long id;

    @Column(name = "ds_marca", nullable = false)
    private String marca;

    @Column(name = "ds_modelo", nullable = false)
    private String modelo;

    @Column(name = "ds_voltagem", nullable = false)
    private String voltagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_tipo", nullable = false)
    private TipoPainel tipo;

    @Positive
    @Column(name = "nr_valor", nullable = false)
    private Double valor;

    @Positive
    @Column(name = "nr_dimensoes", nullable = false)
    private Double dimensoes; // Dimensões em metros quadrados

    @OneToMany(mappedBy = "painel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estoque> estoques = new ArrayList<>();

}