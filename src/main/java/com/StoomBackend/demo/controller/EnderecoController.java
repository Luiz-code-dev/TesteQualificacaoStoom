package com.StoomBackend.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.StoomBackend.demo.model.Endereco;
import com.StoomBackend.demo.repository.EnderecoRepository;

@RestController
public class EnderecoController {
	
	
	@Autowired(required = false) /* Injeção de Dependencia */
	private EnderecoRepository enderecoRepository;
	
	
	@RequestMapping(value = "/cadastrar/{name}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String greetingText(@PathVariable String name) {

		Endereco endereco = new Endereco();
		endereco.setCity(name);
		

		enderecoRepository.save(endereco);/* gravar no banco de dados */

		return "Endereço cadastrado " + name + "!";
	}
	
	
	@GetMapping(value = "listatodos")
	@ResponseBody /* Retorna os dados para o corpo da resposta */
	public ResponseEntity<List<Endereco>> listaEndereco() {

		List<Endereco> enderecos = enderecoRepository.findAll();/* Executa a consulta no banco de dados */

		return new ResponseEntity<List<Endereco>>(enderecos, HttpStatus.OK); /* Retorna a lista em JSON */
	}
	
	@PostMapping(value = "salvar") /* Mapear a URL */
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {/* Recebe os dados para Salvar */

		Endereco end = enderecoRepository.save(endereco);

		return new ResponseEntity<Endereco>(end, HttpStatus.CREATED);

	}

	
	@PutMapping(value = "atualizar") /* Mapear a URL */
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<?> atualizar(
			@RequestBody Endereco endereco) {/* Recebe os dados para Salvar e atualizar no banco */

		if (endereco.getId() == null) {
			return new ResponseEntity<String>("id não foi informado para atualizar", HttpStatus.OK);
		}

		Endereco end = enderecoRepository.saveAndFlush(endereco);

		return new ResponseEntity<Endereco>(end, HttpStatus.OK);

	}
	
	@DeleteMapping(value = "delete") /* Mapear a URL */
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<String> delete(@RequestParam Long idend) {/* Recebe os dados para Delete */

		enderecoRepository.deleteById(idend);

		return new ResponseEntity<String>("Endereço deletado com sucesao", HttpStatus.OK);

	}
	
	@GetMapping(value = "buscaruserid") /* Mapear a URL */
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<Endereco> buscarendid(
			@RequestParam(name = "idend") Long idend) {/* Recebe os dados para Consultar */

		Endereco endereco = enderecoRepository.findById(idend).get();

		return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);

	}
	
	@GetMapping(value = "buscarPorNome") /* Mapear a URL */
	@ResponseBody /* Descrição da reposta */
	public ResponseEntity<List<Endereco>> buscarPorNome(@RequestBody
			@RequestParam(name = "name") String name) {/* Recebe os dados para Consultar Por nome no banco */

		List<Endereco> endereco = enderecoRepository.buscarPorNome(name.trim().toUpperCase());

		return new ResponseEntity<List<Endereco>>(endereco, HttpStatus.OK);

	}
	
}
