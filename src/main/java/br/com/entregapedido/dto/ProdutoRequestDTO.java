package br.com.entregapedido.dto;

public class ProdutoRequestDTO {

    private String nome;
    private Double preco;
    private Integer quantidadeEstoque;
    private String ncm;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(String nome, Double preco, Integer quantidadeEstoque, String ncm) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.ncm = ncm;
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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }
}
