package br.com.entregapedido.repository;

import br.com.entregapedido.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

    Optional<ItemPedido> findById(Long id);

    List<ItemPedido> findByNumeroItemPedido(String numeroItemPedido);

}
