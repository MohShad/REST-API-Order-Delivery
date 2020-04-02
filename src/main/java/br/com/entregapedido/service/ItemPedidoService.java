package br.com.entregapedido.service;

import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoRequestDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponseDTO;

public interface ItemPedidoService {

    String salvarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO);
    ItemPedidoResponseDTO getItemPedidoById(String numeroItemPedido);

}
