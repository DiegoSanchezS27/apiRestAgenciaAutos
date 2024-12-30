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

import com.mx.ApiRestAgenciaAutos.model.Marcas;
import com.mx.ApiRestAgenciaAutos.service.MarcasServImp;

@RestController
@RequestMapping(path = "MarcasWs")
public class MarcasWs {

	@Autowired
	MarcasServImp marcasImp;

	// http:localhost:9000/MarcasWs/listar
	@GetMapping(path = "listar")
	public List<Marcas> listar() {
		return marcasImp.listar();
	}

	// http://localhost:9000/MarcasWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca) {
		boolean response = marcasImp.guardar(marca);
		if (response == true)
			return new ResponseEntity<>("Ese nombre ya esta, ingresa otro", HttpStatus.OK);
		else
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
	}

	// http://localhost:9000/MarcasWs/buscar/{id}
	@PostMapping(path = "buscar/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") long idMar) {
		Marcas marca = marcasImp.buscar(idMar);
		if (marca != null) {
			return new ResponseEntity<>(marca, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("La marca y el id no existe ", HttpStatus.NOT_FOUND);
		}
	}

	// http://localhost:9000/MarcasWs/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marca) {
		boolean response = marcasImp.editar(marca);
		if (response == true)
			return new ResponseEntity<>(marca, HttpStatus.OK);
		else
			return new ResponseEntity<>("El id no exite", HttpStatus.NOT_FOUND);
	}

	// http://localhost:9000/MarcasWs/eliminar/{id}
	@DeleteMapping(path = "eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") long idMar) {
		boolean response = marcasImp.eliminar(idMar);
		if (response) {
			return new ResponseEntity<>("La marca se elimino con exito", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id no existe", HttpStatus.NOT_FOUND);

		}
	}

}
