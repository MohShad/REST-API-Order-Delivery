package br.com.entregapedido.service;

import br.com.entregapedido.dto.MessageDTO.MessageDTO;
import br.com.entregapedido.model.Cliente;
import br.com.entregapedido.model.Entrega;
import br.com.entregapedido.model.Pedido;
import br.com.entregapedido.model.Produto;
import br.com.entregapedido.repository.ClienteRepository;
import br.com.entregapedido.repository.EntregaRepository;
import br.com.entregapedido.repository.PedidoRepository;
import br.com.entregapedido.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class EntregaServiceImpl implements EntregaService {

    private static final Logger logger = LoggerFactory.getLogger(EntregaServiceImpl.class);

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public MessageDTO saveEntrega(MessageDTO messageDTO) {
        try {
            for (int i = 0; messageDTO.getProduto().size() > i; i++) {
                Entrega entrega = new Entrega();

                Date createdAt = new Date();
                entrega.setCreatedAt(createdAt);
                entrega.setEnderecoEntrega(messageDTO.getEnderecoEntrega());
                entrega.setNumeroPedido(messageDTO.getNumeroPedido());
                entrega.setValorTotal(messageDTO.getValorTotal());
                Optional<Cliente> cliente = clienteRepository.findById(messageDTO.getCliente_id());
                entrega.setCliente(cliente.get());
                Optional<Produto> produto = produtoRepository.findById(messageDTO.getProduto().get(0).getId());
                entrega.setQuantidadeProduto(messageDTO.getProduto().get(i).getQauantidade());
                entrega.setProduto(produto.get());
                Optional<Pedido> pedido = pedidoRepository.findById(messageDTO.getId());
                entrega.setPedido(pedido.get());

                entregaRepository.save(entrega);
            }

            return messageDTO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }
}
