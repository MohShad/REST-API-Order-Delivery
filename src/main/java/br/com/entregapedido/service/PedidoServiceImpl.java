package br.com.entregapedido.service;

import br.com.entregapedido.controller.PedidoController;
import br.com.entregapedido.dto.clienteDTO.ClienteResponseDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponseProdutoDTO;
import br.com.entregapedido.dto.pedido.PedidoRequestDTO;
import br.com.entregapedido.dto.pedido.PedidoResponseDTO;
import br.com.entregapedido.model.Cliente;
import br.com.entregapedido.model.ItemPedido;
import br.com.entregapedido.model.Pedido;
import br.com.entregapedido.repository.ClienteRepository;
import br.com.entregapedido.repository.ItemPedidoRepository;
import br.com.entregapedido.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static br.com.entregapedido.model.PedidoStatus.ENVIADO;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public String salvarPedido(PedidoRequestDTO pedidoRequestDTO) {

        try {
            String numeroPedido = UUID.randomUUID().toString();
            for (int i = 0; pedidoRequestDTO.getNumeroItemPedido().size() > i; i++) {
                List<ItemPedido> listItemPedido = itemPedidoRepository.findByNumeroItemPedido(pedidoRequestDTO.getNumeroItemPedido().get(i));
                for (ItemPedido itemPedido : listItemPedido) {
                    Optional<Cliente> cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId());

                    Pedido pedido = new Pedido();
                    Cliente cl = cliente.get();

                    Date dataPedido = new Date();
                    pedido.setDataPedido(dataPedido);
                    pedido.setDataEntrega(getDateSevenWorkDays(dataPedido));
                    pedido.setDescricao(pedidoRequestDTO.getDescricao());
                    pedido.setStatus(ENVIADO);
                    pedido.setCliente(cl);
                    pedido.setItemPedido(itemPedido);
                    pedido.setValorTotal(itemPedido.getValorTotal());
                    pedido.setNumeroPedido(numeroPedido);

                    pedidoRepository.save(pedido);
                }
            }

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
            List<ItemPedidoResponseProdutoDTO> listItemPedidoResponseProdutoDTO = new ArrayList<>();

            PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
            Double valorTotal = 0.0;

            if (listPedidos != null) {
                for (Pedido pedido : listPedidos) {

                    ItemPedidoResponseProdutoDTO itemPedidoResponseProdutoDTO = new ItemPedidoResponseProdutoDTO();
                    itemPedidoResponseProdutoDTO.setId(pedido.getItemPedido().getProduto().getId());
                    itemPedidoResponseProdutoDTO.setNome(pedido.getItemPedido().getProduto().getNome());
                    itemPedidoResponseProdutoDTO.setNcm(pedido.getItemPedido().getProduto().getNcm());
                    itemPedidoResponseProdutoDTO.setPreco(pedido.getItemPedido().getProduto().getPreco());
                    itemPedidoResponseProdutoDTO.setQuantidade(pedido.getItemPedido().getQuantidade());
                    valorTotal = valorTotal + pedido.getItemPedido().getValorTotal();

                    listItemPedidoResponseProdutoDTO.add(itemPedidoResponseProdutoDTO);
                }
            }

            clienteResponseDTO.setId(listPedidos.get(0).getCliente().getId());
            clienteResponseDTO.setNome(listPedidos.get(0).getCliente().getNome());
            clienteResponseDTO.setCpf(listPedidos.get(0).getCliente().getCpf());
            clienteResponseDTO.setEndereco(listPedidos.get(0).getCliente().getEndereco());
            clienteResponseDTO.setEndereco_entrega(listPedidos.get(0).getCliente().getEndereco_entrega());
            clienteResponseDTO.setCep(listPedidos.get(0).getCliente().getCep());
            clienteResponseDTO.setCidade(listPedidos.get(0).getCliente().getCidade());
            clienteResponseDTO.setEstado(listPedidos.get(0).getCliente().getEstado());
            clienteResponseDTO.setEmail(listPedidos.get(0).getCliente().getEmail());

            pedidoResponseDTO.setId(listPedidos.get(0).getId());
            pedidoResponseDTO.setDataPedido(listPedidos.get(0).getDataPedido());
            pedidoResponseDTO.setDataEntrega(listPedidos.get(0).getDataEntrega());
            pedidoResponseDTO.setDescricao(listPedidos.get(0).getDescricao());
            pedidoResponseDTO.setNumeroPedido(listPedidos.get(0).getNumeroPedido());
            pedidoResponseDTO.setStatus(listPedidos.get(0).getStatus());
            pedidoResponseDTO.setValorTotal(valorTotal);

            pedidoResponseDTO.setCliente(clienteResponseDTO);
            pedidoResponseDTO.setProduto(listItemPedidoResponseProdutoDTO);

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
}
