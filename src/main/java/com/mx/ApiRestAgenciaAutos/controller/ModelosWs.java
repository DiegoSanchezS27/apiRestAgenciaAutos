package com.mx.ApiRestAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestAgenciaAutos.model.Modelos;
import com.mx.ApiRestAgenciaAutos.service.ModelosServImp;

@RestController
@RequestMapping(path = "ModelosWs")
public class ModelosWs {

	@Autowired
	ModelosServImp modeloImp;

	// htpp://localhost:9000/ModelosWs/listar
	@GetMapping(path = "listar")
	public List<Modelos> listar() {
		return modeloImp.listar();
	}

	// htpp://localhost:9000/ModelosWs/guardar
	@PostMapping(path = "guardar")
	ResponseEntity<?> guardar(@RequestBody Modelos modelo) {
		boolean response = modeloImp.guardar(modelo);
		if (response == true)
			return new ResponseEntity<>("Ese nombre ya esta, ingresa otro", HttpStatus.OK);
		else
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
	}

	// htpp://localhost:9000/ModelosWs/buscar{id}
	@PostMapping(path = "buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") long idmod) {
		Modelos modelo = modeloImp.buscar(idmod);
		if (modelo != null) {
			return new ResponseEntity<>(modelo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id no existe", HttpStatus.NOT_FOUND);
		}
	}

	// htpp://localhost:9000/ModelosWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelo) {
		boolean response = modeloImp.editar(modelo);
		if (response == true)
			return new ResponseEntity<>(modelo, HttpStatus.OK);
		else
			return new ResponseEntity<>("El id no existe", HttpStatus.NOT_FOUND);
	}

	// htpp://localhost:9000/ModelosWs/eliminar/{id}
	@DeleteMapping(path = "eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") long idMod) {
		boolean response = modeloImp.eliminar(idMod);
		if (response) {
			return new ResponseEntity<>("La marca se elimino con exito", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("EL id no existe", HttpStatus.NOT_FOUND);
		}
	}
}
