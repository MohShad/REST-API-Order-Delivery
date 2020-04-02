package br.com.entregapedido.dto.itemPedidoDTO;

import java.util.List;

public class ItemPedidoResponsePedidoDTO {

    private Long id;
    private Double valorTotal;
    private Integer quantidade;
    private String descricao;
    private String numeroItemPedido;
    private List<ItemPedidoResponseProdutoDTO> produto;

    public ItemPedidoResponsePedidoDTO() {
    }

    public ItemPedidoResponsePedidoDTO(Long id, Double valorTotal, Integer quantidade, String descricao, String numeroItemPedido, List<ItemPedidoResponseProdutoDTO> produto) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.numeroItemPedido = numeroItemPedido;
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

    public String getNumeroItemPedido() {
        return numeroItemPedido;
    }

    public void setNumeroItemPedido(String numeroItemPedido) {
        this.numeroItemPedido = numeroItemPedido;
    }

    public List<ItemPedidoResponseProdutoDTO> getProduto() {
        return produto;
    }

    public void setProduto(List<ItemPedidoResponseProdutoDTO> produto) {
        this.produto = produto;
    }
}
