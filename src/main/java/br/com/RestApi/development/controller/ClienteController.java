package br.com.RestApi.development.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.RestApi.development.model.Cliente;
import br.com.RestApi.development.model.RespostaModel;
import br.com.RestApi.development.repository.ClienteRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository cr;
	@Autowired
	private RespostaModel rm;

	@GetMapping
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> clientes = cr.findAll();
		if (clientes.isEmpty()) {
			rm.setMensagem("Nenhum cliente encontrado.");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RespostaModel> adicionar(@Valid @RequestBody Cliente cliente, BindingResult result) {
		if(result.hasErrors()) {
		rm.setMensagem(result.getFieldError().getDefaultMessage());
		return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
		}
		cr.save(cliente);
		rm.setMensagem("Cliente cadastrado com sucesso!");
		return new ResponseEntity<>(rm, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<RespostaModel> atualizar(@Valid @RequestBody Cliente cliente, BindingResult result) {
		if(result.hasErrors()) {
			rm.setMensagem(result.getFieldError().getDefaultMessage());
			return new ResponseEntity<>(rm, HttpStatus.BAD_REQUEST);
		}
		cr.save(cliente);
		rm.setMensagem("Cliente atualizado com sucesso!");
		return new ResponseEntity<>(rm, HttpStatus.OK);
	}
	
}
