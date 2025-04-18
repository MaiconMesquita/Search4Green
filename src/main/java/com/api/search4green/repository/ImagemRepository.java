package com.api.search4green.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.search4green.model.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

}
