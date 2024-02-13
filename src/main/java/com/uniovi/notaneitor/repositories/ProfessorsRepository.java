package com.uniovi.notaneitor.repositories;

import com.uniovi.notaneitor.entities.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorsRepository extends CrudRepository<Professor,Long> {
    public Professor findByDni(String dni); //Busca un profesor por dni (PK)

}
