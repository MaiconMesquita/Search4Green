package com.api.search4green.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Endereco {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idEndereco;

  private String uf;
  private String cep;
  private String cidade;
  private String bairro;
  private String logradouro;
  private String numero;
}
