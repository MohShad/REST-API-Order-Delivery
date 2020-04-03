package br.com.entregapedido.controller;

import br.com.entregapedido.dto.ApiResponseDTO;
import br.com.entregapedido.dto.pedido.PedidoRequestDTO;
import br.com.entregapedido.dto.pedido.PedidoResponseDTO;
import br.com.entregapedido.model.Cliente;
import br.com.entregapedido.model.ItemPedido;
import br.com.entregapedido.model.Pedido;
import br.com.entregapedido.repository.ClienteRepository;
import br.com.entregapedido.repository.ItemPedidoRepository;
import br.com.entregapedido.repository.PedidoRepository;
import br.com.entregapedido.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @PostMapping
    public ResponseEntity<?> registerPedido(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {

        try {
            for(int i = 0; pedidoRequestDTO.getNumeroItemPedido().size() > i; i++){
                List<ItemPedido> itemPedido = itemPedidoRepository.findByNumeroItemPedido(pedidoRequestDTO.getNumeroItemPedido().get(i));
                if (itemPedido == null) {
                    return new ResponseEntity(new ApiResponseDTO(false, "Item pedido não encontrado!"),
                            HttpStatus.BAD_REQUEST);
                }
            }
            Optional<Cliente> cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId());
            if (!cliente.isPresent()) {
                return new ResponseEntity(new ApiResponseDTO(false, "Cliente não encontrado!"),
                        HttpStatus.BAD_REQUEST);
            }

            String numeroPedido = pedidoService.salvarPedido(pedidoRequestDTO);

            return new ResponseEntity(new ApiResponseDTO(true, "Pedido registrado com successo, Número do Pedido: "+numeroPedido),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{numeroPedido}")
    public ResponseEntity<PedidoResponseDTO> getPedidoById(@Valid @PathVariable("numeroPedido")String numeroPedido) {

        try {
            List<Pedido> pedido = pedidoRepository.findByNumeroPedido(numeroPedido);
            if(pedido.isEmpty()){
                return new ResponseEntity(new ApiResponseDTO(false, "Pedido não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }

            PedidoResponseDTO pedidoResponseDTO = pedidoService.getPedidoByNumeroPedido(numeroPedido);

            return new ResponseEntity<PedidoResponseDTO>(pedidoResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
