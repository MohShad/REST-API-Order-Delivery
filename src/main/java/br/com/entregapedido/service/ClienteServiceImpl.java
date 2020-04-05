package br.com.entregapedido.service;

import br.com.entregapedido.dto.clienteDTO.ClienteRequestDTO;
import br.com.entregapedido.dto.clienteDTO.ClienteResponseDTO;
import br.com.entregapedido.model.Cliente;
import br.com.entregapedido.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Long salvarCliente(ClienteRequestDTO clienteRequestDTO) {

        try {
            Cliente cl = new Cliente();
            cl.setNome(clienteRequestDTO.getNome());
            cl.setCpf(clienteRequestDTO.getCpf());
            cl.setEndereco(clienteRequestDTO.getEndereco());
            cl.setEnderecoEntrega(clienteRequestDTO.getEnderecoEntrega());
            cl.setCep(clienteRequestDTO.getCep());
            cl.setCidade(clienteRequestDTO.getCidade());
            cl.setEstado(clienteRequestDTO.getEstado());
            cl.setEmail(clienteRequestDTO.getEmail());

            clienteRepository.save(cl);
            return cl.getId();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ClienteResponseDTO getClienteByCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getEnderecoEntrega(), cliente.getCep(), cliente.getCidade(), cliente.getEstado(), cliente.getEmail());
        return clienteResponseDTO;
    }

}
