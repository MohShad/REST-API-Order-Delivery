package br.com.entregapedido.dto.pedido;

import br.com.entregapedido.dto.clienteDTO.ClienteResponseDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponseDTO;
import br.com.entregapedido.dto.itemPedidoDTO.ItemPedidoResponsePedidoDTO;
import br.com.entregapedido.model.PedidoStatus;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;
import java.util.List;

@JsonRootName(value = "pedido")
public class PedidoResponseDTO {

    private Long id;
    private Date dataPedido;
    private Date dataEntrega;
    private String descricao;
    private String numeroPedido;
    private PedidoStatus status;
    private ClienteResponseDTO cliente;
    private ItemPedidoResponsePedidoDTO itemPedido;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Long id, Date dataPedido, Date dataEntrega, String descricao, String numeroPedido, PedidoStatus status, ClienteResponseDTO cliente, ItemPedidoResponsePedidoDTO itemPedido) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
        this.numeroPedido = numeroPedido;
        this.status = status;
        this.cliente = cliente;
        this.itemPedido = itemPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public ItemPedidoResponsePedidoDTO getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedidoResponsePedidoDTO itemPedido) {
        this.itemPedido = itemPedido;
    }
}
