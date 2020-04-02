package br.com.entregapedido.dto.pedido;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class PedidoRequestDTO {

    @NotNull
    @Size(max = 2000)
    private String descricao;

    @NotNull
    private Long clienteId;

    @NotNull
    private List<Long> itemPedidoId;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<Long> getItemPedidoId() {
        return itemPedidoId;
    }

    public void setItemPedidoId(List<Long> itemPedidoId) {
        this.itemPedidoId = itemPedidoId;
    }
}
