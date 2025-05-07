package com.api.search4green.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Estabelecimento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idEstabelecimento;

  @NotBlank(message = "Nome é obrigatório")
  private String nome;

  @NotBlank(message = "Telefone é obrigatório")
  @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX")
  private String telefone;

  @NotBlank(message = "Descrição é obrigatória")
  @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
  private String descricao;

  @NotBlank(message = "Tipo é obrigatório")
  private String tipo;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "endereco_id")
  private Endereco endereco;

  @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private List<Imagem> imagens;

}
