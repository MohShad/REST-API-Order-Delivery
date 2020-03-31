package br.com.entregapedido.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item_pedido", schema = "entrega_pedido")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Basic
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Basic
    @Column(name = "descricao")
    private String descricao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Produto.class)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(Double valorTotal, Integer quantidade, String descricao, Produto produto) {
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.produto = produto;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(valorTotal, that.valorTotal) &&
                Objects.equals(quantidade, that.quantidade) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, valorTotal, quantidade, descricao, produto);
    }
}
