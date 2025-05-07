package com.api.search4green.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Imagem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idImagem;

  private String url;

  @ManyToOne
  @JoinColumn(name = "estabelecimento_id")
  @JsonBackReference
  private Estabelecimento estabelecimento;
}
