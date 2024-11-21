package br.com.fiap.global.model.pedido;


import br.com.fiap.global.model.user.UserAccount;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="GS_JV_PEDIDOS")
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name="seq_pedidos", sequenceName="seq_JV_pedidos", allocationSize=1, initialValue = 1)
public class Pedidos {

    @Id
    @GeneratedValue
    @Column(name = "cd_pedido")
    private Long id;

    @Column(name = "nr_pedido", nullable = false)
    private Long pedido;

    @Column(name = "qtd_pedido", nullable = false)
    @Min(1)
    private int quantidade;

    @Column(name = "vlr_total", nullable = false)
    @Positive
    private Double valorTotal;

    @Column(name = "dt_pedido", nullable = false)
    private Date dataPedido;

    @ManyToOne
    @JoinColumn(name = "cd_user", nullable = false)
    private UserAccount user;
}