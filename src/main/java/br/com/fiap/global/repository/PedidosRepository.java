package br.com.fiap.global.repository;

import br.com.fiap.global.model.pedido.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidosRepository extends JpaRepository<Pedidos, Long> {

}
