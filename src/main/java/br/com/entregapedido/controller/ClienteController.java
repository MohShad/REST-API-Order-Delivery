package br.com.entregapedido.controller;

import br.com.entregapedido.dto.ApiResponseDTO;
import br.com.entregapedido.dto.clienteDTO.ClienteRequestDTO;
import br.com.entregapedido.dto.clienteDTO.ClienteResponseDTO;
import br.com.entregapedido.repository.ClienteRepository;
import br.com.entregapedido.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<?> registerCliente(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO) {

        try {
            if (clienteRepository.existsByCpf(clienteRequestDTO.getCpf())) {
                return new ResponseEntity(new ApiResponseDTO(false, "Existe cliente registrado com CPF: " + clienteRequestDTO.getCpf() + "."),
                        HttpStatus.BAD_REQUEST);
            }
            clienteService.salvarCliente(clienteRequestDTO);
            
            return new ResponseEntity(new ApiResponseDTO(true, "Cliente registrado com successo."),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDTO> getClienteByCpf(@PathVariable("cpf") String cpf) {

        try {
            if (!clienteRepository.existsByCpf(cpf)) {
                return new ResponseEntity(new ApiResponseDTO(false, "CPF não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }
            ClienteResponseDTO clienteResponseDTO = clienteService.getClienteByCpf(cpf);

            return new ResponseEntity<ClienteResponseDTO>(clienteResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
