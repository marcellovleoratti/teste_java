package br.com.desafio.teste_java.controller;


import br.com.desafio.teste_java.model.WebLoginModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebLoginRepository extends JpaRepository<WebLoginModel,Long> {

    Optional<WebLoginModel> findByNomeAndUser(String nome, String usuario);
}
