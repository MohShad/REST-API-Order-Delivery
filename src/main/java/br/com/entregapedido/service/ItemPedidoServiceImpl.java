package br.com.entregapedido.service;

import br.com.entregapedido.dto.ItemPedidoRequestDTO;
import br.com.entregapedido.dto.ItemPedidoResponseDTO;
import br.com.entregapedido.dto.ItemPedidoResponseProdutoDTO;
import br.com.entregapedido.dto.ProdutoResponseDTO;
import br.com.entregapedido.model.ItemPedido;
import br.com.entregapedido.model.Produto;
import br.com.entregapedido.repository.ItemPedidoRepository;
import br.com.entregapedido.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService{

    private static final Logger logger = LoggerFactory.getLogger(ItemPedidoServiceImpl.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public void salvarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {

        Optional<Produto> produto = produtoRepository.findById(itemPedidoRequestDTO.getProdutoId());
        ItemPedido itemPedido = new ItemPedido();
        Produto pr = produto.get();

        pr.setQuantidadeEstoque(pr.getQuantidadeEstoque() - itemPedidoRequestDTO.getQuantidade());
        produtoRepository.save(pr);

        itemPedido.setValorTotal(itemPedidoRequestDTO.getValorTotal());
        itemPedido.setDescricao(itemPedidoRequestDTO.getDescricao());
        itemPedido.setQuantidade(itemPedidoRequestDTO.getQuantidade());
        itemPedido.setProduto(produto.get());

        itemPedidoRepository.save(itemPedido);
    }

    @Override
    public ItemPedidoResponseDTO getItemPedidoById(Long id){
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
        ItemPedido itP = itemPedido.get();
        ItemPedidoResponseProdutoDTO itemPedidoResponseProdutoDTO = new ItemPedidoResponseProdutoDTO();

        itemPedidoResponseProdutoDTO.setId(itP.getProduto().getId());
        itemPedidoResponseProdutoDTO.setNome(itP.getProduto().getNome());
        itemPedidoResponseProdutoDTO.setPreco(itP.getProduto().getPreco());
        itemPedidoResponseProdutoDTO.setNcm(itP.getProduto().getNcm());

        ItemPedidoResponseDTO itemPedidoResponseDTO = new ItemPedidoResponseDTO(itP.getId(), itP.getValorTotal(), itP.getQuantidade(), itP.getDescricao(), itemPedidoResponseProdutoDTO);
        return itemPedidoResponseDTO;
    }

}
