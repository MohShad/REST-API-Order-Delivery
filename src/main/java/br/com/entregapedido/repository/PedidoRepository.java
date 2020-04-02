package br.com.entregapedido.repository;

import br.com.entregapedido.model.Pedido;
import br.com.entregapedido.model.PedidoStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    Optional<Pedido> findById(Long id);

    List<Pedido> findByNumeroPedido(String numeroPedido);
}
