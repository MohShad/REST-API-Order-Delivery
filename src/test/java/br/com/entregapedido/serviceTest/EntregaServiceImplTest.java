package br.com.entregapedido.serviceTest;

import br.com.entregapedido.dto.MessageDTO.MessageDTO;
import br.com.entregapedido.dto.MessageDTO.ProdutoResponseMessageDTO;
import br.com.entregapedido.model.*;
import br.com.entregapedido.repository.ClienteRepository;
import br.com.entregapedido.repository.EntregaRepository;
import br.com.entregapedido.repository.PedidoRepository;
import br.com.entregapedido.repository.ProdutoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.entregapedido.model.PedidoStatus.ABERTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class EntregaServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(EntregaServiceImplTest.class);

    @Mock
    private EntregaRepository entregaRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private Cliente clienteMock;

    @Mock
    private Produto produtoMock;

    @Mock
    private MessageDTO messageDTOMock;

    @Mock
    private Pedido pedidoMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(EntregaServiceImplTest.class);
    }

    @Before
    public void init() {

        produtoMock = new Produto(
                "Doce",
                40.50,
                50,
                "3242342",
                new Date(),
                new Date()
        );
        produtoMock.setId(20L);

        List<ProdutoResponseMessageDTO> listProdutoResponseMessageDTO = new ArrayList<>();
        ProdutoResponseMessageDTO produtoResponseMessageDTO01 = new ProdutoResponseMessageDTO(
                20L,
                10,
                40.50,
                405.0
        );
        listProdutoResponseMessageDTO.add(produtoResponseMessageDTO01);

        ProdutoResponseMessageDTO produtoResponseMessageDTO02 = new ProdutoResponseMessageDTO(
                21L,
                15,
                45.50,
                682.50
        );
        listProdutoResponseMessageDTO.add(produtoResponseMessageDTO02);

        pedidoMock = new Pedido(
                new Date(),
                new Date(),
                new Date(),
                "Pedido do Sr. José",
                105.50,
                10,
                ABERTO,
                "38725b7c-61f2-4ddd-bdbe-ce5b4355a6d8",
                clienteMock,
                produtoMock
        );

        messageDTOMock = new MessageDTO(
                25L,
                "38725b7c-61f2-4ddd-bdbe-ce5b4355a6d8",
                450.0,
                "Av. Bandeirantes, 2555",
                10L,
                listProdutoResponseMessageDTO
        );

        clienteMock = new Cliente(
                "Mohammad Shadnik",
                "08858966957",
                new Date(),
                new Date(),
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@hotmail.com"
        );
        clienteMock.setId(10L);

        when(clienteRepository.findById(clienteMock.getId())).thenReturn(Optional.of(clienteMock));
        when(produtoRepository.findById(produtoResponseMessageDTO01.getId())).thenReturn(Optional.of(produtoMock));
        when(pedidoRepository.findById(messageDTOMock.getId())).thenReturn(Optional.of(pedidoMock));

        Cliente clienteMock = new Cliente(
                "Mohammad Shadnik",
                "03423423423",
                new Date(),
                new Date(),
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@gmail.com"
        );
        clienteMock.setId(3L);

        Produto produtoMock01 = new Produto(
                "Produto 01",
                25.50D,
                48,
                "345678",
                new Date(),
                new Date()
        );
        produtoMock01.setId(15L);

        Produto produtoMock02 = new Produto(
                "Produto 02",
                25.50D,
                58,
                "3456789",
                new Date(),
                new Date()
        );
        produtoMock02.setId(16L);

        Pedido pedidoMock01 = new Pedido(
                new Date(),
                new Date(),
                new Date(),
                "Descrição teste",
                123.50,
                12,
                PedidoStatus.ABERTO,
                "f9be16ca-76a0-4975-ada3-c7867e5c0dbd",
                clienteMock,
                produtoMock01
        );

        List<Entrega> listEntregaResponseDTO = new ArrayList<>();
        Entrega entrega01 = new Entrega(
                new Date(),
                "f9be16ca-76a0-4975-ada3-c7867e5c0dbd",
                123.50,
                12,
                "Av Jorge Casoni",
                "80e80419-607d-4845-966b-d495de21c926",
                clienteMock,
                produtoMock01,
                pedidoMock01
        );
        listEntregaResponseDTO.add(entrega01);

        Entrega entrega02 = new Entrega(
                new Date(),
                "f9be16ca-76a0-4975-ada3-c7867e5c0dbd",
                123.50,
                12,
                "Av Jorge Casoni",
                "80e80419-607d-4845-966b-d495de21c926",
                clienteMock,
                produtoMock02,
                pedidoMock01
        );
        listEntregaResponseDTO.add(entrega02);

        when(entregaRepository.findAll()).thenReturn(listEntregaResponseDTO);
    }

    @Test
    public void saveEntregaTest() {

        try {
            logger.info("Início do test unitário do saveEntregaTest");

            Entrega entrega = new Entrega();

            Date createdAt = new Date();
            entrega.setCreatedAt(createdAt);
            entrega.setEnderecoEntrega(messageDTOMock.getEnderecoEntrega());
            entrega.setNumeroPedido(messageDTOMock.getNumeroPedido());
            entrega.setValorTotal(messageDTOMock.getProduto().get(0).getValorTotal());
            Optional<Cliente> cliente = clienteRepository.findById(messageDTOMock.getCliente_id());
            entrega.setCliente(cliente.get());
            Optional<Produto> produto = produtoRepository.findById(messageDTOMock.getProduto().get(0).getId());
            entrega.setQuantidadeProduto(messageDTOMock.getProduto().get(0).getQauantidade());
            entrega.setProduto(produto.get());
            Optional<Pedido> pedido = pedidoRepository.findById(messageDTOMock.getId());
            entrega.setPedido(pedido.get());

            entregaRepository.save(entrega);

            assertNotNull(entrega);
            assertEquals("38725b7c-61f2-4ddd-bdbe-ce5b4355a6d8", entrega.getNumeroPedido());
            assertEquals("Doce", entrega.getPedido().getProduto().getNome());
            assertEquals("Pedido do Sr. José", entrega.getPedido().getDescricao());
            assertEquals("Mohammad Shadnik", entrega.getCliente().getNome());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void getAllEntregasTest() {
        List<Entrega> listEntregas = entregaRepository.findAll();

        assertThat(listEntregas.size()).isEqualTo(2);
        Assertions.assertEquals("80e80419-607d-4845-966b-d495de21c926", listEntregas.get(0).getNumeroEntrega());
    }
}
