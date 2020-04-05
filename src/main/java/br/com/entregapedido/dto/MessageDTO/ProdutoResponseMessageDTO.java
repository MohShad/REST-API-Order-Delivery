package br.com.entregapedido.dto.MessageDTO;

public class ProdutoResponseMessageDTO {

    private Long id;
    private Integer qauantidade;
    private Double precoUnitario;

    public ProdutoResponseMessageDTO() {
    }

    public ProdutoResponseMessageDTO(Long id, Integer qauantidade, Double precoUnitario) {
        this.id = id;
        this.qauantidade = qauantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQauantidade() {
        return qauantidade;
    }

    public void setQauantidade(Integer qauantidade) {
        this.qauantidade = qauantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
