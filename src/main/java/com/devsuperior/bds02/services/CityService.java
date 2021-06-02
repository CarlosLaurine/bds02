package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DBException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	
	
	@Transactional
	public List<CityDTO> findAll(){
		
		List<City> entityList = cityRepository.findAll(Sort.by("name"));
		
		return entityList.stream().map(entity -> new CityDTO(entity)).collect(Collectors.toList());
		
	}
	
	
	public void delete(Long id) {

		try {

			cityRepository.deleteById(id);

		}
		catch (EmptyResultDataAccessException e1) {
			throw new ResourceNotFoundException("Inexistent Id for Deletion => " + id);
		}
		
		catch (DataIntegrityViolationException e2) {
			throw new DBException("Data Integrity Violation");
		}

	}
}
