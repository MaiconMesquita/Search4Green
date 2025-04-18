package com.api.search4green.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.search4green.model.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
  List<Estabelecimento> findByNomeContainingIgnoreCase(String nome);

  List<Estabelecimento> findByTipo(String tipo);

  List<Estabelecimento> findByEndereco_Cidade(String cidade);

  List<Estabelecimento> findByTipoAndEndereco_Cidade(String tipo, String cidade);
}
