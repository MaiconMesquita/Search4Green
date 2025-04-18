package com.api.search4green.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.search4green.model.Endereco;
import com.api.search4green.model.Estabelecimento;
import com.api.search4green.model.Imagem;
import com.api.search4green.service.EstabelecimentoService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/estabelecimentos")
public class EstabelecimentoController {
  private final EstabelecimentoService service;

  public EstabelecimentoController(EstabelecimentoService service) {
    this.service = service;
  }

  // CRUD BÁSICO --------------------------------------------------------------

  @PostMapping
  public ResponseEntity<Estabelecimento> create(@RequestBody Estabelecimento body) {
    Estabelecimento saved = service.createEstabelecimento(body);
    return ResponseEntity.status(201).body(saved);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Estabelecimento> update(@PathVariable Long id, @RequestBody Estabelecimento novosDados) {
    try {
      Estabelecimento updated = service.updateEstabelecimentoPartial(id, novosDados);
      return ResponseEntity.ok(updated);
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    try {
      service.deleteEstabelecimento(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  // SUB-RECURSOS ---------------------- ENDEREÇO ----------------------

  @PostMapping("/{id}/endereco")
  public ResponseEntity<Endereco> addEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
    try {
      Endereco saved = service.addEndereco(id, endereco);
      return ResponseEntity.status(201).body(saved);
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/{id}/endereco")
  public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco novoEndereco) {
    try {
      Endereco updated = service.updateEnderecoPartial(id, novoEndereco);
      return ResponseEntity.ok(updated);
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}/endereco")
  public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
    try {
      service.deleteEndereco(id);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  // SUB-RECURSOS ---------------------- IMAGENS ----------------------

  @GetMapping("/{id}/imagens")
  public ResponseEntity<List<Imagem>> listImagens(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(service.listImagens(id));
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{id}/imagens")
  public ResponseEntity<Imagem> addImagem(@PathVariable Long id, @RequestBody Imagem img) {
    try {
      Imagem saved = service.addImagem(id, img);
      return ResponseEntity.status(201).body(saved);
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}/imagens/{imgId}")
  public ResponseEntity<Void> deleteImagem(@PathVariable Long id, @PathVariable Long imgId) {
    try {
      service.deleteImagem(id, imgId);
      return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException ex) {
      return ResponseEntity.notFound().build();
    }
  }

  // ENDPOINT DE BUSCA --------------------------------------------------

  @GetMapping("/search")
  public ResponseEntity<List<Estabelecimento>> search(@RequestParam(required = false) String nome,
      @RequestParam(required = false) String tipo,
      @RequestParam(required = false) String cidade) {
    List<Estabelecimento> results = service.search(nome, tipo, cidade);
    if (nome != null && results.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(results);
  }
}
