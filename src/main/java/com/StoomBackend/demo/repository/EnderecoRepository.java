package com.StoomBackend.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.StoomBackend.demo.model.Endereco;

@Component
@Repository
public interface EnderecoRepository extends  JpaRepository<Endereco, Long>{
	
	List<Endereco> buscarPorNome(String name);

}
