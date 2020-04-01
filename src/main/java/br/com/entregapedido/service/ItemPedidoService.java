package br.com.entregapedido.service;

import br.com.entregapedido.dto.ItemPedidoRequestDTO;
import br.com.entregapedido.dto.ItemPedidoResponseDTO;

public interface ItemPedidoService {

    void salvarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO);
    ItemPedidoResponseDTO getItemPedidoById(Long id);

}
