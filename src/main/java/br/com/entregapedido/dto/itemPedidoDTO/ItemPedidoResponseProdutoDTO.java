package br.com.entregapedido.dto.itemPedidoDTO;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "produto")
public class ItemPedidoResponseProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;
    private String ncm;

    public ItemPedidoResponseProdutoDTO(){}

    public ItemPedidoResponseProdutoDTO(Long id, String nome, Double preco, String ncm) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.ncm = ncm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }
}
