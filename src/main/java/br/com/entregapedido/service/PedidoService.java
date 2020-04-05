package br.com.entregapedido.service;

import br.com.entregapedido.dto.pedido.PedidoRequestDTO;
import br.com.entregapedido.dto.pedido.PedidoResponseDTO;

public interface PedidoService {

    String salvarPedido(PedidoRequestDTO pedidoRequestDTO);

    PedidoResponseDTO getPedidoByNumeroPedido(String numeroPedido);
}
