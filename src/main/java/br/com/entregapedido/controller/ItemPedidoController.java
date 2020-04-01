package br.com.entregapedido.controller;

import br.com.entregapedido.dto.ApiResponseDTO;
import br.com.entregapedido.dto.ItemPedidoRequestDTO;
import br.com.entregapedido.dto.ItemPedidoResponseDTO;
import br.com.entregapedido.model.ItemPedido;
import br.com.entregapedido.model.Produto;
import br.com.entregapedido.repository.ItemPedidoRepository;
import br.com.entregapedido.repository.ProdutoRepository;
import br.com.entregapedido.service.ItemPedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/itemPedido")
public class ItemPedidoController {

    private static final Logger logger = LoggerFactory.getLogger(ItemPedidoController.class);

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<?> registerItemPedido(@Valid @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO) {

        try {
            Optional<Produto> produto = produtoRepository.findById(itemPedidoRequestDTO.getProdutoId());
            if (!produto.isPresent()) {
                return new ResponseEntity(new ApiResponseDTO(false, "Produto não encontrado!"),
                        HttpStatus.BAD_REQUEST);
            }
            if (produto.isPresent()) {
                Produto pr = produto.get();
                if(pr.getQuantidadeEstoque() < itemPedidoRequestDTO.getQuantidade())
                return new ResponseEntity(new ApiResponseDTO(false, "Não temos estoque suficiente! Existem "+ pr.getQuantidadeEstoque() + " produto(s) em estoque."),
                        HttpStatus.BAD_REQUEST);
            }

            itemPedidoService.salvarItemPedido(itemPedidoRequestDTO);

            return new ResponseEntity(new ApiResponseDTO(true, "ItemPedido registrado com successo."),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDTO> getItemPedidoById(@Valid @PathVariable("id")Long id) {

        try {
            Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
            if(!itemPedido.isPresent()){
                return new ResponseEntity(new ApiResponseDTO(false, "Item pedido não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }

            ItemPedidoResponseDTO itemPedidoResponseDTO = itemPedidoService.getItemPedidoById(id);

            return new ResponseEntity<ItemPedidoResponseDTO>(itemPedidoResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
