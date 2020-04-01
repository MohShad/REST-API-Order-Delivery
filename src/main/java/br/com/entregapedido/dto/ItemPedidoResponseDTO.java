package br.com.entregapedido.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "itemPedido")
public class ItemPedidoResponseDTO {

    private Long id;
    private Double valorTotal;
    private Integer quantidade;
    private String descricao;
    private ItemPedidoResponseProdutoDTO produto;

    public ItemPedidoResponseDTO() {
    }

    public ItemPedidoResponseDTO(Long id, Double valorTotal, Integer quantidade, String descricao, ItemPedidoResponseProdutoDTO produto) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ItemPedidoResponseProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ItemPedidoResponseProdutoDTO produto) {
        this.produto = produto;
    }
}
