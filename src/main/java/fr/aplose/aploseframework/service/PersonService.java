package fr.aplose.aploseframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aplose.aploseframework.model.Person;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.repository.PersonRepository;

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


    public Page<Person> searchProfessional(String query, String countryCode, PageRequest pageRequest){
        return this._personRepository.findProfessionalsByFullNameContainingIgnoreCase(query, countryCode, pageRequest);
    }


    public Person getByUserAccount(UserAccount userAccount){
        return this._personRepository.findByUserAccount(userAccount);
    }

    public void delete(Person person){
        this._personRepository.delete(person);
    }
}
