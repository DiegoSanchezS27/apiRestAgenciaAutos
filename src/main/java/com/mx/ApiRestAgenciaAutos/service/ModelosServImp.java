package com.mx.ApiRestAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestAgenciaAutos.dao.ModelosDao;
import com.mx.ApiRestAgenciaAutos.model.Modelos;

@Service
public class ModelosServImp {

	@Autowired
	ModelosDao modeloDao;

	@Transactional(readOnly = true)
	public List<Modelos> listar() {
		return modeloDao.findAll();
	}

	@Transactional
	public boolean guardar(Modelos modelo) {
		for (Modelos v : modeloDao.findAll()) {
			if (v.getNombre().equals(modelo.getNombre())) {
				return true;
			}
			if (v.getIdMod().equals(modelo.getIdMod())) {
				return true;
			}
		}
		modeloDao.save(modelo);
		return false;
	}

	@Transactional(readOnly = true)
	public Modelos buscar(long idMod) {
		return modeloDao.findById(idMod).orElse(null);
	}

	@Transactional
	public boolean editar(Modelos modelo) {
		if (modeloDao.existsById(modelo.getIdMod())) {
			modeloDao.save(modelo);
		}
		return false;
	}

	@Transactional
	public boolean eliminar(long idMod) {
		if (modeloDao.existsById(idMod)) {
			modeloDao.deleteById(idMod);
			return true;
		}
		return false;
	}

}
