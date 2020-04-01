package br.com.entregapedido.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemPedidoRequestDTO {

    @NotNull
    private Double valorTotal;

    @NotNull
    private Integer quantidade;

    @Size(max= 1000)
    @NotNull
    private String descricao;

    @NotNull
    private Long produtoId;

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }
}
