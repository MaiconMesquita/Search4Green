package com.api.search4green.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.search4green.model.Endereco;
import com.api.search4green.model.Estabelecimento;
import com.api.search4green.model.Imagem;
import com.api.search4green.repository.EstabelecimentoRepository;
import com.api.search4green.repository.ImagemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class EstabelecimentoService {
  private final EstabelecimentoRepository estabelecimentoRepo;
  private final ImagemRepository imagemRepo;

  public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepo, ImagemRepository imagemRepo) {
    this.estabelecimentoRepo = estabelecimentoRepo;
    this.imagemRepo = imagemRepo;
  }

  public Estabelecimento createEstabelecimento(Estabelecimento estabelecimento) {
    return estabelecimentoRepo.save(estabelecimento);
  }

  public Estabelecimento updateEstabelecimentoPartial(Long id, Estabelecimento dados) {
    Estabelecimento estabelecimento = estabelecimentoRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    if (dados.getNome() != null)
      estabelecimento.setNome(dados.getNome());
    if (dados.getTelefone() != null)
      estabelecimento.setTelefone(dados.getTelefone());
    if (dados.getDescricao() != null)
      estabelecimento.setDescricao(dados.getDescricao());
    if (dados.getTipo() != null)
      estabelecimento.setTipo(dados.getTipo());
    return estabelecimentoRepo.save(estabelecimento);
  }

  public void deleteEstabelecimento(Long id) {
    if (!estabelecimentoRepo.existsById(id)) {
      throw new EntityNotFoundException("Estabelecimento não encontrado");
    }
    estabelecimentoRepo.deleteById(id);
  }

  public Endereco addEndereco(Long id, Endereco endereco) {
    Estabelecimento estabelecimento = estabelecimentoRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    estabelecimento.setEndereco(endereco);
    Estabelecimento saved = estabelecimentoRepo.save(estabelecimento);
    return saved.getEndereco();
  }

  public Endereco updateEnderecoPartial(Long id, Endereco dados) {
    Estabelecimento estabelecimento = estabelecimentoRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    Endereco endereco = estabelecimento.getEndereco();
    if (endereco == null) {
      estabelecimento.setEndereco(dados);
      return estabelecimentoRepo.save(estabelecimento).getEndereco();
    }
    if (dados.getUf() != null)
      endereco.setUf(dados.getUf());
    if (dados.getCep() != null)
      endereco.setCep(dados.getCep());
    if (dados.getCidade() != null)
      endereco.setCidade(dados.getCidade());
    if (dados.getBairro() != null)
      endereco.setBairro(dados.getBairro());
    if (dados.getLogradouro() != null)
      endereco.setLogradouro(dados.getLogradouro());
    if (dados.getNumero() != null)
      endereco.setNumero(dados.getNumero());

    Estabelecimento updated = estabelecimentoRepo.save(estabelecimento);
    return updated.getEndereco();
  }

  public void deleteEndereco(Long id) {
    Estabelecimento estabelecimento = estabelecimentoRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    estabelecimento.setEndereco(null);
    estabelecimentoRepo.save(estabelecimento);
  }

  public List<Imagem> listImagens(Long id) {
    Estabelecimento estabelecimento = estabelecimentoRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    return estabelecimento.getImagens();
  }

  public Imagem addImagem(Long id, Imagem img) {
    Estabelecimento estabelecimento = estabelecimentoRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    img.setEstabelecimento(estabelecimento);
    return imagemRepo.save(img);
  }

  public void deleteImagem(Long id, Long imgId) {
    Estabelecimento estabelecimento = estabelecimentoRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));
    boolean removed = estabelecimento.getImagens().removeIf(img -> Objects.equals(img.getIdImagem(), imgId));
    if (!removed) {
      throw new EntityNotFoundException("Imagem não encontrada");
    }
    estabelecimentoRepo.save(estabelecimento);
  }

  public List<Estabelecimento> search(String nome, String tipo, String cidade) {
    if (nome != null) {
      return estabelecimentoRepo.findByNomeContainingIgnoreCase(nome);
    }
    if (tipo != null && cidade != null) {
      return estabelecimentoRepo.findByTipoAndEndereco_Cidade(tipo, cidade);
    }
    if (tipo != null) {
      return estabelecimentoRepo.findByTipo(tipo);
    }
    if (cidade != null) {
      return estabelecimentoRepo.findByEndereco_Cidade(cidade);
    }
    return estabelecimentoRepo.findAll();
  }

}
