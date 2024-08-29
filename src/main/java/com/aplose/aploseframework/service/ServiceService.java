package com.aplose.aploseframework.service;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.aplose.aploseframework.model.Service;
import com.aplose.aploseframework.repository.ServiceRepository;

import jakarta.persistence.EntityNotFoundException;



@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository _serviceRepository;
    


    public Service getServiceById(Long serviceId){
        Service service = this._serviceRepository.findById(serviceId).orElse(null);
        if(service == null){
            throw new EntityNotFoundException("Service with id %s not found".formatted(serviceId));
        }
        return service;
    }


    public Page<Service> getServices(PageRequest pageRequest){
        return this._serviceRepository.findAll(pageRequest);
    }


    public Page<Service> searchServiceByName(String query, String countryCode, Duration minDuration, Duration maxDuration, PageRequest pageRequest){
        return this._serviceRepository.findByNameContainingIgnoreCase(query, countryCode, minDuration, maxDuration, pageRequest);
    }


    public Service create(Service service){
        return this._serviceRepository.save(service);
    }
}
