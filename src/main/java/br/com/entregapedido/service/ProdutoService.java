package br.com.entregapedido.service;

import br.com.entregapedido.dto.produtoDTO.ProdutoRequestDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoResponseDTO;

public interface ProdutoService {

    void salvarProduto(ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO getProdutoByNcm(String ncm);

    void increaseStockQuantity(ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO);
}
