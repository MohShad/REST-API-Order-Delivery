package br.com.entregapedido.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produto", schema = "entrega_pedido")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name="nome", nullable = false)
    private String nome;

    @Basic
    @Column(name="preco", nullable = false)
    private Double preco;

    @Basic
    @Column(name="quantidade_estoque", nullable = false)
    private Integer quantidadeEstoque;

    @Basic
    @Column(name="ncm", nullable = false, unique = true)
    private String ncm;

    public Produto() {
    }

    public Produto(String nome, Double preco, Integer quantidadeEstoque, String ncm) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.ncm = ncm;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(nome, produto.nome) &&
                Objects.equals(preco, produto.preco) &&
                Objects.equals(quantidadeEstoque, produto.quantidadeEstoque) &&
                Objects.equals(ncm, produto.ncm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, preco, quantidadeEstoque, ncm);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeEstoque +
                ", ncm='" + ncm + '\'' +
                '}';
    }
}
