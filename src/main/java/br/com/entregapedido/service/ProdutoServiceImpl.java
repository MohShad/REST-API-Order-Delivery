package br.com.entregapedido.service;

import br.com.entregapedido.controller.PedidoController;
import br.com.entregapedido.dto.produtoDTO.ProdutoRequestDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoResponseDTO;
import br.com.entregapedido.model.Produto;
import br.com.entregapedido.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public Long salvarProduto(ProdutoRequestDTO produtoRequestDTO) {

        try {
            Produto pr = new Produto();
            pr.setNome(produtoRequestDTO.getNome());
            pr.setPreco(produtoRequestDTO.getPreco());
            pr.setCreatedAt(new Date());
            pr.setNcm(produtoRequestDTO.getNcm());
            pr.setQuantidadeEstoque(produtoRequestDTO.getQuantidadeEstoque());

            produtoRepository.save(pr);
            return pr.getId();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }

    }

    @Override
    public ProdutoResponseDTO getProdutoByNcm(String ncm) {
        Produto produto = produtoRepository.findByNcm(ncm);
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidadeEstoque(), produto.getNcm());
        return produtoResponseDTO;
    }

    @Override
    @Transactional
    public Long increaseStockQuantity(ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO) {
        try {
            Produto produto = produtoRepository.findByNcm(produtoRequestEstoqueDTO.getNcm());
            int novaQuantidade = produto.getQuantidadeEstoque() + produtoRequestEstoqueDTO.getQuantidadeEstoque();
            produto.setQuantidadeEstoque(novaQuantidade);
            produtoRepository.save(produto);
            return produto.getId();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }
}
