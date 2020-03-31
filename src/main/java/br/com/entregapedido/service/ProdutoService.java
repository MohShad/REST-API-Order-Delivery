package br.com.entregapedido.service;

import br.com.entregapedido.dto.ProdutoRequestDTO;
import br.com.entregapedido.dto.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.dto.ProdutoResponseDTO;

public interface ProdutoService {

    void salvarProduto(ProdutoRequestDTO produtoRequestDTO);
    ProdutoResponseDTO getProdutoByNcm(String ncm);
    void increaseStockQuantity(ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO);
}
