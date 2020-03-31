package br.com.entregapedido.service;

import br.com.entregapedido.dto.ClienteRequestDTO;
import br.com.entregapedido.dto.ClienteResponseDTO;

public interface ClienteService {

    void salvarCliente(ClienteRequestDTO clienteRequestDTO);
    ClienteResponseDTO getClienteByCpf(String cpf);
}
