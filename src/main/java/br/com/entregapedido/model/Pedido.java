package br.com.entregapedido.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "entrega_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "data_pedido", nullable = false)
    private Date dataPedido;

    @Basic
    @Column(name = "data_entrega", nullable = false)
    private Date dataEntrega;

    @Basic
    @Column(name = "descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PedidoStatus status;

    @Basic
    @GenericGenerator(name = "numero_pedido", strategy = "uuid2")
    private String numeroPedido;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Cliente.class)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ItemPedido.class)
    @JoinColumn(name = "item_pedido_id")
    private ItemPedido itemPedido;

    public Pedido() {
    }

    public Pedido(Date dataPedido, Date dataEntrega, String descricao, PedidoStatus status, String numeroPedido, Cliente cliente, ItemPedido itemPedido) {
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
        this.status = status;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itemPedido = itemPedido;
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

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemPedido getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) &&
                Objects.equals(dataPedido, pedido.dataPedido) &&
                Objects.equals(dataEntrega, pedido.dataEntrega) &&
                Objects.equals(descricao, pedido.descricao) &&
                status == pedido.status &&
                Objects.equals(numeroPedido, pedido.numeroPedido) &&
                Objects.equals(cliente, pedido.cliente) &&
                Objects.equals(itemPedido, pedido.itemPedido);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dataPedido, dataEntrega, descricao, status, numeroPedido, cliente, itemPedido);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", dataPedido=" + dataPedido +
                ", dataEntrega=" + dataEntrega +
                ", descricao='" + descricao + '\'' +
                ", status=" + status +
                ", numeroPedido='" + numeroPedido + '\'' +
                ", cliente=" + cliente +
                ", itemPedido=" + itemPedido +
                '}';
    }
}