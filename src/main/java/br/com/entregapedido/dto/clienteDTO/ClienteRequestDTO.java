package br.com.entregapedido.dto.clienteDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteRequestDTO {

    @Size(max= 100)
    @NotNull
    private String nome;

    @Size(max= 11)
    @NotNull
    private String cpf;

    @Size(max= 100)
    @NotNull
    private String endereco;

    @Size(max= 100)
    @NotNull
    private String endereco_entrega;

    @Size(max= 9)
    @NotNull
    private String cep;

    @Size(max= 50)
    @NotNull
    private String cidade;

    @Size(max= 50)
    @NotNull
    private String estado;

    @Size(max= 100)
    @NotNull
    @Email
    private String email;

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
