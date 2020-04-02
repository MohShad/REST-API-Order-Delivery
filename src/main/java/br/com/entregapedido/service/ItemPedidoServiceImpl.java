package br.com.entregapedido.service;

import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoRequestDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponseDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponseProdutoDTO;
import br.com.entregapedido.model.ItemPedido;
import br.com.entregapedido.model.Produto;
import br.com.entregapedido.repository.ItemPedidoRepository;
import br.com.entregapedido.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

    private static final Logger logger = LoggerFactory.getLogger(ItemPedidoServiceImpl.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public String salvarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {

        try {
            String numeroItemPedido = UUID.randomUUID().toString();
            for (int i = 0; itemPedidoRequestDTO.getProdutoId().size() > i; i++) {
                Optional<Produto> produto = produtoRepository.findById(itemPedidoRequestDTO.getProdutoId().get(i));
                Produto pr = produto.get();
                ItemPedido itemPedido = new ItemPedido();

                pr.setQuantidadeEstoque(pr.getQuantidadeEstoque() - itemPedidoRequestDTO.getQuantidade());
                produtoRepository.save(pr);

                itemPedido.setValorTotal(pr.getPreco() * itemPedidoRequestDTO.getQuantidade());
                itemPedido.setDescricao(itemPedidoRequestDTO.getDescricao());
                itemPedido.setQuantidade(itemPedidoRequestDTO.getQuantidade());
                itemPedido.setProduto(produto.get());
                itemPedido.setNumeroItemPedido(numeroItemPedido);

                itemPedidoRepository.save(itemPedido);
            }

            return numeroItemPedido;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public ItemPedidoResponseDTO getItemPedidoById(String numeroItemPedido) {

        try {
            List<ItemPedido> listItemPedido = itemPedidoRepository.findByNumeroItemPedido(numeroItemPedido);
            ItemPedidoResponseDTO itemPedidoResponseDTO = new ItemPedidoResponseDTO();
            List<ItemPedidoResponseProdutoDTO> lisItemPedidoResponseProdutoDTO = new ArrayList<>();
            Double valorTotal = 0.0;
            if (listItemPedido != null) {
                for (ItemPedido itemPedido : listItemPedido) {
                    ItemPedidoResponseProdutoDTO itemPedidoResponseProdutoDTO = new ItemPedidoResponseProdutoDTO();
                    itemPedidoResponseProdutoDTO.setId(itemPedido.getProduto().getId());
                    itemPedidoResponseProdutoDTO.setNome(itemPedido.getProduto().getNome());
                    itemPedidoResponseProdutoDTO.setNcm(itemPedido.getProduto().getNcm());
                    itemPedidoResponseProdutoDTO.setPreco(itemPedido.getProduto().getPreco());

                    valorTotal = valorTotal + itemPedido.getValorTotal();

                    lisItemPedidoResponseProdutoDTO.add(itemPedidoResponseProdutoDTO);
                }
            }

            itemPedidoResponseDTO.setId(listItemPedido.get(0).getId());
            itemPedidoResponseDTO.setDescricao(listItemPedido.get(0).getDescricao());
            itemPedidoResponseDTO.setQuantidade(listItemPedido.get(0).getQuantidade());
            itemPedidoResponseDTO.setValorTotal(valorTotal);
            itemPedidoResponseDTO.setNumeroItemPedido(listItemPedido.get(0).getNumeroItemPedido());

            itemPedidoResponseDTO.setProduto(lisItemPedidoResponseProdutoDTO);
            return itemPedidoResponseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }
}
