package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@org.springframework.transaction.annotation.Transactional
	public EventDTO update(EventDTO dto, Long id) {
		
		try {
		Event entity = eventRepository.getOne(id);
		entity.setName(dto.getName());
		entity.setCity(cityRepository.findById(dto.getCityId()).get());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		
		entity = eventRepository.save(entity);	
		
		dto = new EventDTO(entity);
		
		return dto;
		}
		catch(EntityNotFoundException e) {
			
			throw new ResourceNotFoundException("Inexistent Id => " + id);
			
		}
		
		
		
	}
}
