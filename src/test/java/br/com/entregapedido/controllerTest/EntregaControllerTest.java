package br.com.entregapedido.controllerTest;

import br.com.entregapedido.model.*;
import br.com.entregapedido.service.EntregaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntregaControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EntregaControllerTest.class);

    @Mock
    private EntregaService entregaService;

    @Test
    public void getAllTest() {

        logger.info("Início do test unitário do getAllTest");
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

        when(entregaService.getAllEntregas()).thenReturn(listEntregaResponseDTO);
        List<Entrega> entregaResponseDTO = entregaService.getAllEntregas();

        assertThat(entregaResponseDTO.size()).isEqualTo(2);
        assertEquals("80e80419-607d-4845-966b-d495de21c926", entregaResponseDTO.get(0).getNumeroEntrega());
    }

}
