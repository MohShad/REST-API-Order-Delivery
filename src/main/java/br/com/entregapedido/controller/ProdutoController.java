package br.com.entregapedido.controller;

import br.com.entregapedido.dto.ApiResponseDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoRequestDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoResponseDTO;
import br.com.entregapedido.repository.ProdutoRepository;
import br.com.entregapedido.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> registerProduto(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {

        try {
            if (produtoRepository.existsByNcm(produtoRequestDTO.getNcm())) {
                return new ResponseEntity(new ApiResponseDTO(false, "Existe produto registrado com NCM: " + produtoRequestDTO.getNcm() + "."),
                        HttpStatus.BAD_REQUEST);
            }
            produtoService.salvarProduto(produtoRequestDTO);

            return new ResponseEntity(new ApiResponseDTO(true, "Produto registrado com successo."),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{ncm}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoByNcm(@Valid @PathVariable("ncm") String ncm) {

        try {
            if (!produtoRepository.existsByNcm(ncm)) {
                return new ResponseEntity(new ApiResponseDTO(false, "Produto não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }
            ProdutoResponseDTO produtoResponseDTO = produtoService.getProdutoByNcm(ncm);

            return new ResponseEntity<ProdutoResponseDTO>(produtoResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addStockQuantity")
    public ResponseEntity<?> aumentarQuantidadeEstoque(@Valid @RequestBody ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO) {

        try {
            if (!produtoRepository.existsByNcm(produtoRequestEstoqueDTO.getNcm())) {
                return new ResponseEntity(new ApiResponseDTO(false, "Produto não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }
            if (produtoRequestEstoqueDTO.getQuantidadeEstoque() <= 0) {
                return new ResponseEntity(new ApiResponseDTO(false, "Quantidade deve ser maior que 0."),
                        HttpStatus.BAD_REQUEST);
            }
            produtoService.increaseStockQuantity(produtoRequestEstoqueDTO);

            return new ResponseEntity(new ApiResponseDTO(true, "Quantidade estoque do produto alterado com successo."),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
