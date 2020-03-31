package br.com.entregapedido.service;

import br.com.entregapedido.controller.ClienteController;
import br.com.entregapedido.dto.ClienteRequestDTO;
import br.com.entregapedido.dto.ClienteResponseDTO;
import br.com.entregapedido.model.Cliente;
import br.com.entregapedido.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void salvarCliente(ClienteRequestDTO clienteRequestDTO) {

        Cliente cl = new Cliente();
        cl.setNome(clienteRequestDTO.getNome());
        cl.setCpf(clienteRequestDTO.getCpf());
        cl.setEndereco(clienteRequestDTO.getEndereco());
        cl.setEndereco_entrega(clienteRequestDTO.getEndereco_entrega());
        cl.setCep(clienteRequestDTO.getCep());
        cl.setCidade(clienteRequestDTO.getCidade());
        cl.setEstado(clienteRequestDTO.getEstado());
        cl.setEmail(clienteRequestDTO.getEmail());

        clienteRepository.save(cl);

    }

    @Override
    public ClienteResponseDTO getClienteByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getEndereco_entrega(), cliente.getCep(),cliente.getCidade(), cliente.getEstado(), cliente.getEmail());
        return clienteResponseDTO;
    }

}
