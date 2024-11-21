package br.com.fiap.global.model.painel;

import br.com.fiap.global.model.painel.enums.TipoOperacao;
import br.com.fiap.global.model.pedido.Pedidos;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="GS_JV_ESTOQUE")
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name="seq_estoque", sequenceName="seq_JV_estoque", allocationSize=1, initialValue = 1)
public class Estoque {

    @Id
    @GeneratedValue
    @Column(name = "cd_estoque")
    private Long id;

    @Column(name = "nr_quantidade", nullable = false)
    @Min(value = 0, message = "A quantidade não pode ser negativa.")
    private int quantidade;

    @Column(name = "nr_valor", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que zero.")
    private Double valor;

    @Column(name = "dt_entrada", nullable = false)
    private Date dataEntrada;

    @Column(name = "dt_saida")
    private Date dataSaida;

    @Column(name = "tp_operacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoOperacao tipoOperacao;

    @ManyToOne
    @JoinColumn(name = "cd_painel", nullable = false)
    private PainelSolar painel;

}
