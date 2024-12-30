package com.mx.ApiRestAgenciaAutos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestAgenciaAutos.dao.MarcasDao;
import com.mx.ApiRestAgenciaAutos.model.Marcas;

@Service
public class MarcasServImp {

	@Autowired
	MarcasDao marcaDao;

	@Transactional(readOnly = true)
	public List<Marcas> listar() {
		return marcaDao.findAll();
	}

	@Transactional
	public boolean guardar(Marcas marca) {
		for (Marcas v : marcaDao.findAll()) {
			if (v.getNombre().equals(marca.getNombre())) {
				return true;
			}
			if (v.getIdMar().equals(marca.getIdMar())) {
				return true;
			}
		}
		marcaDao.save(marca);
		return false;
	}

	@Transactional(readOnly = true)
	public Marcas buscar(long idMar) {
		return marcaDao.findById(idMar).orElse(null);
	}

	@Transactional
	public boolean editar(Marcas marca) {
		if (marcaDao.existsById(marca.getIdMar())) {
			marcaDao.save(marca);
			return true;
		}
		return false;
	}
	@Transactional
	public boolean eliminar (long idMar) {
		if(marcaDao.existsById(idMar)) {
			marcaDao.deleteById(idMar);
			return true;
		}
		return false;
	}
}
