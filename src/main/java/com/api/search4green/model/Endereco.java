package com.api.search4green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Endereco {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idEndereco;

  @NotBlank(message = "UF é obrigatório")
  private String uf;

  @NotBlank(message = "CEP é obrigatório")
  private String cep;

  @NotBlank(message = "Cidade é obrigatória")
  private String cidade;

  private String bairro;
  private String logradouro;
  private String numero;
}
