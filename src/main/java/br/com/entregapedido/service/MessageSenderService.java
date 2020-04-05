package br.com.entregapedido.service;

import br.com.entregapedido.dto.MessageDTO.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageSenderService {

    void sendMessage(MessageDTO messageDTO) throws JsonProcessingException;
}
