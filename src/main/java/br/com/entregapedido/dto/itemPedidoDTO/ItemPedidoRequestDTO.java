package br.com.entregapedido.dto.itemPedidoDTO;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ItemPedidoRequestDTO {

    @Size(max= 1000)
    @NotNull
    private String descricao;

    @NotNull
    private Integer quantidade;

    @NotNull
    private List<Long> produtoId;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<Long> getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(List<Long> produtoId) {
        this.produtoId = produtoId;
    }
}
