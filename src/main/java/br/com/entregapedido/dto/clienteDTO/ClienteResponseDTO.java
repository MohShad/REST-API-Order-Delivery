package br.com.entregapedido.dto.clienteDTO;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "cliente")
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String endereco_entrega;
    private String cep;
    private String cidade;
    private String estado;
    private String email;

    public ClienteResponseDTO(){}

    public ClienteResponseDTO(Long id, String nome, String cpf, String endereco, String endereco_entrega, String cep, String cidade, String estado, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.endereco_entrega = endereco_entrega;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
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
}
