package br.com.entregapedido.service;

import br.com.entregapedido.dto.ProdutoRequestDTO;
import br.com.entregapedido.dto.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.dto.ProdutoResponseDTO;
import br.com.entregapedido.model.Produto;
import br.com.entregapedido.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void salvarProduto(ProdutoRequestDTO produtoRequestDTO) {
        Produto pr = new Produto();
        pr.setNome(produtoRequestDTO.getNome());
        pr.setPreco(produtoRequestDTO.getPreco());
        pr.setNcm(produtoRequestDTO.getNcm());
        pr.setQuantidadeEstoque(produtoRequestDTO.getQuantidadeEstoque());

        produtoRepository.save(pr);

    }

    @Override
    public ProdutoResponseDTO getProdutoByNcm(String ncm) {
        Produto produto = produtoRepository.findByNcm(ncm);
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(produto.getId(),produto.getNome(), produto.getPreco(), produto.getQuantidadeEstoque(), produto.getNcm());
        return produtoResponseDTO;
    }

    @Override
    public void increaseStockQuantity(ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO) {
        Produto produto = produtoRepository.findByNcm(produtoRequestEstoqueDTO.getNcm());
        int novaQuantidade = produto.getQuantidadeEstoque() + produtoRequestEstoqueDTO.getQuantidadeEstoque();
        produto.setQuantidadeEstoque(novaQuantidade);
        produtoRepository.save(produto);
    }
}
