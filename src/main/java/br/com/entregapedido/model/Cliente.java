package br.com.entregapedido.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "entrega_pedido")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "nome", nullable = false)
    private String nome;

    @Basic
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Basic
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Basic
    @Column(name = "endereco_entrega", nullable = false)
    private String endereco_entrega;

    @Basic
    @Column(name = "cep", nullable = false)
    private String cep;

    @Basic
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Basic
    @Column(name = "estado", nullable = false)
    private String estado;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String endereco, String endereco_entrega, String cep, String cidade, String estado, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.endereco_entrega = endereco_entrega;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco_entrega() {
        return endereco_entrega;
    }

    public void setEndereco_entrega(String endereco_entrega) {
        this.endereco_entrega = endereco_entrega;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(cpf, cliente.cpf) &&
                Objects.equals(endereco, cliente.endereco) &&
                Objects.equals(endereco_entrega, cliente.endereco_entrega) &&
                Objects.equals(cep, cliente.cep) &&
                Objects.equals(cidade, cliente.cidade) &&
                Objects.equals(estado, cliente.estado) &&
                Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, cpf, endereco, endereco_entrega, cep, cidade, estado, email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", endereco_entrega='" + endereco_entrega + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}