package br.com.entregapedido.service;

import br.com.entregapedido.dto.MessageDTO.MessageDTO;

public interface EntregaService {

    MessageDTO saveEntrega(MessageDTO messageDTO);
}
