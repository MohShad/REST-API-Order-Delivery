package br.com.entregapedido.service;

import br.com.entregapedido.dto.clienteDTO.ClienteRequestDTO;
import br.com.entregapedido.dto.clienteDTO.ClienteResponseDTO;

public interface ClienteService {

    Long salvarCliente(ClienteRequestDTO clienteRequestDTO);

    ClienteResponseDTO getClienteByCpf(String cpf);
}
