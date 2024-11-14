package br.com.fiap.global.model.painel;

import br.com.fiap.global.model.pedido.Pedidos;
import jakarta.persistence.*;
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
    private int quantidade;

    @Column(name = "nr_valor", nullable = false)
    private Double valor;

    @Column(name = "dt_entrada", nullable = false)
    private Date dataEntrada;

    @Column(name = "dt_saida", nullable = false)
    private Date dataSaida;

    @ManyToOne
    @JoinColumn(name = "cd_painel")
    private PainelSolar painel;

    @ManyToOne
    @JoinColumn(name = "cd_pedido")
    private Pedidos pedido;

}
