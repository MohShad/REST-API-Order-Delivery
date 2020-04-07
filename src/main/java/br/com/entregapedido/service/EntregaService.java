package br.com.entregapedido.service;

import br.com.entregapedido.dto.MessageDTO.MessageDTO;
import br.com.entregapedido.model.Entrega;

import java.util.List;

public interface EntregaService {

    MessageDTO saveEntrega(MessageDTO messageDTO);

    List<Entrega> getAllEntregas();
}
