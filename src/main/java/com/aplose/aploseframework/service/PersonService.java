package com.aplose.aploseframework.service;

import com.aplose.aploseframework.model.UserAccount;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplose.aploseframework.model.Person;
import com.aplose.aploseframework.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class PersonService {
    @Autowired
    private PersonRepository _personRepository;

    public Person findById(Long personId){
        return this._personRepository.findById(personId).orElse(null);
    }

    public List<Person> getAll(){
        return this._personRepository.findAll();
    }

    public Person save(Person person){
        return this._personRepository.save(person);
    }
    public Page<UserAccount> searchProfessional(String query, String countryCode, PageRequest pageRequest){
        return this._personRepository.findProfessionalsByFullNameContainingIgnoreCase(query, countryCode, pageRequest);
    }
}
