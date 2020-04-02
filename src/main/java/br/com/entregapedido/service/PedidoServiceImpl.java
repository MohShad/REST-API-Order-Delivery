package br.com.entregapedido.service;

import br.com.entregapedido.controller.PedidoController;
import br.com.entregapedido.dto.clienteDTO.ClienteResponseDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponsePedidoDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponseProdutoDTO;
import br.com.entregapedido.dto.pedido.PedidoRequestDTO;
import br.com.entregapedido.dto.pedido.PedidoResponseDTO;
import br.com.entregapedido.model.Cliente;
import br.com.entregapedido.model.ItemPedido;
import br.com.entregapedido.model.Pedido;
import br.com.entregapedido.repository.ClienteRepository;
import br.com.entregapedido.repository.ItemPedidoRepository;
import br.com.entregapedido.repository.PedidoRepository;
import br.com.entregapedido.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static br.com.entregapedido.model.PedidoStatus.ABERTO;

@Service
public class PedidoServiceImpl implements PedidoService {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public String salvarPedido(PedidoRequestDTO pedidoRequestDTO) {

        try {
            String numeroPedido = UUID.randomUUID().toString();
            for (int i = 0; pedidoRequestDTO.getItemPedidoId().size() > i; i++) {
                Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(pedidoRequestDTO.getItemPedidoId().get(i));
                Optional<Cliente> cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId());

                Pedido pedido = new Pedido();
                Cliente cl = cliente.get();
                ItemPedido itP = itemPedido.get();

                Date dataPedido = new Date();
                pedido.setDataPedido(dataPedido);
                pedido.setDataEntrega(getDateSevenWorkDays(dataPedido));
                pedido.setDescricao(pedidoRequestDTO.getDescricao());
                pedido.setStatus(ABERTO);
                pedido.setCliente(cl);
                pedido.setItemPedido(itP);
                pedido.setNumeroPedido(numeroPedido);

                pedidoRepository.save(pedido);
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
            //List<ItemPedidoResponsePedidoDTO> listItemPedidoResponsePedidoDTO = new ArrayList<>();
            List<ItemPedidoResponseProdutoDTO> listItemPedidoResponseProdutoDTO = new ArrayList<>();

            ItemPedidoResponsePedidoDTO itemPedidoResponsePedidoDTO = new ItemPedidoResponsePedidoDTO();
            PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();

            if (listPedidos != null) {
                pedidoResponseDTO.setId(listPedidos.get(0).getId());
                pedidoResponseDTO.setDataPedido(listPedidos.get(0).getDataPedido());
                pedidoResponseDTO.setDataEntrega(listPedidos.get(0).getDataEntrega());
                pedidoResponseDTO.setDescricao(listPedidos.get(0).getDescricao());
                pedidoResponseDTO.setNumeroPedido(listPedidos.get(0).getNumeroPedido());
                pedidoResponseDTO.setStatus(listPedidos.get(0).getStatus());

                clienteResponseDTO.setId(listPedidos.get(0).getCliente().getId());
                clienteResponseDTO.setNome(listPedidos.get(0).getCliente().getNome());
                clienteResponseDTO.setCpf(listPedidos.get(0).getCliente().getCpf());
                clienteResponseDTO.setEndereco(listPedidos.get(0).getCliente().getEndereco());
                clienteResponseDTO.setEndereco_entrega(listPedidos.get(0).getCliente().getEndereco_entrega());
                clienteResponseDTO.setCep(listPedidos.get(0).getCliente().getCep());
                clienteResponseDTO.setCidade(listPedidos.get(0).getCliente().getCidade());
                clienteResponseDTO.setEstado(listPedidos.get(0).getCliente().getEstado());
                clienteResponseDTO.setEmail(listPedidos.get(0).getCliente().getEmail());


                itemPedidoResponsePedidoDTO.setId(listPedidos.get(0).getItemPedido().getId());
                itemPedidoResponsePedidoDTO.setDescricao(listPedidos.get(0).getItemPedido().getDescricao());
                itemPedidoResponsePedidoDTO.setQuantidade(listPedidos.get(0).getItemPedido().getQuantidade());
                itemPedidoResponsePedidoDTO.setValorTotal(listPedidos.get(0).getItemPedido().getValorTotal());
                itemPedidoResponsePedidoDTO.setNumeroItemPedido(listPedidos.get(0).getItemPedido().getNumeroItemPedido());

                for (Pedido pedido : listPedidos) {
                    ItemPedidoResponseProdutoDTO itemPedidoResponseProdutoDTO = new ItemPedidoResponseProdutoDTO();
                    itemPedidoResponseProdutoDTO.setId(pedido.getItemPedido().getProduto().getId());
                    itemPedidoResponseProdutoDTO.setNome(pedido.getItemPedido().getProduto().getNome());
                    itemPedidoResponseProdutoDTO.setNcm(pedido.getItemPedido().getProduto().getNcm());
                    itemPedidoResponseProdutoDTO.setPreco(pedido.getItemPedido().getProduto().getPreco());

                    listItemPedidoResponseProdutoDTO.add(itemPedidoResponseProdutoDTO);
                }
            }

            pedidoResponseDTO.setCliente(clienteResponseDTO);
            itemPedidoResponsePedidoDTO.setProduto(listItemPedidoResponseProdutoDTO);
            pedidoResponseDTO.setItemPedido(itemPedidoResponsePedidoDTO);

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
