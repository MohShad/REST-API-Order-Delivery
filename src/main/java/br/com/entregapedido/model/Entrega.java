package br.com.entregapedido.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "entrega", schema = "entrega_pedido")
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "data_envio", nullable = false)
    private Date dataEnvio;

    @Basic
    @Column(name = "data_entregue")
    private Date dataEntregue;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EntregaStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Pedido.class)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Entrega() {
    }

    public Entrega(Date dataEnvio, Date dataEntregue, EntregaStatus status, Pedido pedido) {
        this.dataEnvio = dataEnvio;
        this.dataEntregue = dataEntregue;
        this.status = status;
        this.pedido = pedido;
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

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataEntregue() {
        return dataEntregue;
    }

    public void setDataEntregue(Date dataEntregue) {
        this.dataEntregue = dataEntregue;
    }

    public EntregaStatus getStatus() {
        return status;
    }

    public void setStatus(EntregaStatus status) {
        this.status = status;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return Objects.equals(id, entrega.id) &&
                Objects.equals(dataEnvio, entrega.dataEnvio) &&
                Objects.equals(dataEntregue, entrega.dataEntregue) &&
                status == entrega.status &&
                Objects.equals(pedido, entrega.pedido);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, dataEnvio, dataEntregue, status, pedido);
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id +
                ", dataEnvio=" + dataEnvio +
                ", dataEntregue=" + dataEntregue +
                ", status=" + status +
                ", pedido=" + pedido +
                '}';
    }
}
