package br.com.entregapedido.service;

import br.com.entregapedido.dto.MessageDTO.MessageDTO;
import br.com.entregapedido.dto.MessageDTO.ProdutoResponseMessageDTO;
import br.com.entregapedido.dto.clienteDTO.ClienteResponseDTO;
import br.com.entregapedido.dto.pedido.PedidoRequestDTO;
import br.com.entregapedido.dto.pedido.PedidoResponseDTO;
import br.com.entregapedido.dto.produtoDTO.ProdutoResponsePedidoDTO;
import br.com.entregapedido.model.Cliente;
import br.com.entregapedido.model.Pedido;
import br.com.entregapedido.model.Produto;
import br.com.entregapedido.repository.ClienteRepository;
import br.com.entregapedido.repository.PedidoRepository;
import br.com.entregapedido.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

import static br.com.entregapedido.model.PedidoStatus.ABERTO;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoServiceImpl.class);

    private final RabbitTemplate rabbitTemplate;

    public PedidoServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MessageSenderServiceImpl messageSenderServiceImpl;

    SimpleDateFormat dateFormatData = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    @Transactional
    public String salvarPedido(PedidoRequestDTO pedidoRequestDTO) {

        try {
            String numeroPedido = UUID.randomUUID().toString();
            for (int i = 0; pedidoRequestDTO.getProduto().size() > i; i++) {
                Optional<Produto> produto = produtoRepository.findById(pedidoRequestDTO.getProduto().get(i).getId());
                Optional<Cliente> cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId());

                Produto pr = produto.get();
                Cliente cl = cliente.get();
                Pedido pedido = new Pedido();

                Date createdAt = new Date();
                pedido.setCreatedAt(createdAt);
                pedido.setDataEntrega(getDateSevenWorkDays(createdAt));
                pedido.setDescricao(pedidoRequestDTO.getDescricao());
                pedido.setNumeroPedido(numeroPedido);
                pedido.setStatus(ABERTO);
                Double valorTotal = pr.getPreco() * pedidoRequestDTO.getProduto().get(i).getQuantidade();
                pedido.setValorTotal(valorTotal);
                pedido.setQuantidadeProduto(pedidoRequestDTO.getProduto().get(i).getQuantidade());
                pedido.setCliente(cl);
                pedido.setProduto(pr);

                pr.setQuantidadeEstoque(pr.getQuantidadeEstoque() - pedidoRequestDTO.getProduto().get(i).getQuantidade());
                produtoRepository.save(pr);

                pedidoRepository.save(pedido);
            }
            validateAndParseMessageDTO(numeroPedido);
            return numeroPedido;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PedidoResponseDTO getPedidoByNumeroPedido(String numeroPedido) {

        try {
            List<Pedido> listPedidos = pedidoRepository.findByNumeroPedido(numeroPedido);
            List<ProdutoResponsePedidoDTO> listProdutoResponsePedidoDTO = new ArrayList<>();

            PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
            Double valorTotal = 0D;

            if (listPedidos != null) {
                for (Pedido pedido : listPedidos) {

                    ProdutoResponsePedidoDTO produtoResponsePedidoDTO = new ProdutoResponsePedidoDTO();
                    produtoResponsePedidoDTO.setId(pedido.getProduto().getId());
                    produtoResponsePedidoDTO.setNome(pedido.getProduto().getNome());
                    produtoResponsePedidoDTO.setNcm(pedido.getProduto().getNcm());
                    produtoResponsePedidoDTO.setPreco(pedido.getProduto().getPreco());
                    produtoResponsePedidoDTO.setQuantidade(pedido.getQuantidadeProduto());
                    valorTotal = valorTotal + pedido.getValorTotal();

                    listProdutoResponsePedidoDTO.add(produtoResponsePedidoDTO);
                }
            }
            clienteResponseDTO.setId(listPedidos.get(0).getCliente().getId());
            clienteResponseDTO.setNome(listPedidos.get(0).getCliente().getNome());
            clienteResponseDTO.setCpf(listPedidos.get(0).getCliente().getCpf());
            clienteResponseDTO.setEndereco(listPedidos.get(0).getCliente().getEndereco());
            clienteResponseDTO.setEnderecoEntrega(listPedidos.get(0).getCliente().getEnderecoEntrega());
            clienteResponseDTO.setCep(listPedidos.get(0).getCliente().getCep());
            clienteResponseDTO.setCidade(listPedidos.get(0).getCliente().getCidade());
            clienteResponseDTO.setEstado(listPedidos.get(0).getCliente().getEstado());
            clienteResponseDTO.setEmail(listPedidos.get(0).getCliente().getEmail());

            pedidoResponseDTO.setId(listPedidos.get(0).getId());
            pedidoResponseDTO.setDataPedido(dateFormatData.format(listPedidos.get(0).getCreatedAt()));
            pedidoResponseDTO.setDataEntrega(dateFormatData.format(listPedidos.get(0).getDataEntrega()));
            pedidoResponseDTO.setDescricao(listPedidos.get(0).getDescricao());
            pedidoResponseDTO.setNumeroPedido(listPedidos.get(0).getNumeroPedido());
            pedidoResponseDTO.setStatus(listPedidos.get(0).getStatus());
            pedidoResponseDTO.setValorTotal(valorTotal);

            pedidoResponseDTO.setCliente(clienteResponseDTO);
            pedidoResponseDTO.setProduto(listProdutoResponsePedidoDTO);

            return pedidoResponseDTO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    private Date getDateSevenWorkDays(Date dataPedido) {
        Calendar c = Calendar.getInstance();

        c.setTime(dataPedido);
        c.add(Calendar.DATE, 7);// 7 Work days
        Date currentDatePlusSeven = c.getTime();// convert calendar to date

        return currentDatePlusSeven;
    }

    public void validateAndParseMessageDTO(String numeroPedido) {
        try {
            MessageDTO messageDTO = new MessageDTO();


            List<Pedido> listPedido = pedidoRepository.findByNumeroPedido(numeroPedido);
            Double valorTotal = 0D;

            if (listPedido != null) {
                List<ProdutoResponseMessageDTO> listProdutoResponseMessageDTO = new ArrayList<>();
                for (Pedido pedido : listPedido) {
                    ProdutoResponseMessageDTO produtoResponseMessageDTO = new ProdutoResponseMessageDTO();
                    produtoResponseMessageDTO.setId(pedido.getProduto().getId());
                    produtoResponseMessageDTO.setQauantidade(pedido.getQuantidadeProduto());
                    produtoResponseMessageDTO.setPrecoUnitario(pedido.getProduto().getPreco());
                    produtoResponseMessageDTO.setValorTotal(pedido.getQuantidadeProduto() * pedido.getProduto().getPreco());
                    listProdutoResponseMessageDTO.add(produtoResponseMessageDTO);
                    valorTotal = valorTotal + pedido.getValorTotal();
                }

                messageDTO.setId(listPedido.get(0).getId());
                messageDTO.setNumeroPedido(listPedido.get(0).getNumeroPedido());
                messageDTO.setValorTotal(valorTotal);
                Cliente cliente = clienteRepository.findByCpf(listPedido.get(0).getCliente().getCpf());
                messageDTO.setEnderecoEntrega(cliente.getEnderecoEntrega());
                messageDTO.setCliente_id(listPedido.get(0).getCliente().getId());
                messageDTO.setProduto(listProdutoResponseMessageDTO);
            }

            messageSenderServiceImpl.sendMessage(messageDTO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
