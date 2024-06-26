package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    @Autowired
    private ProfessorsRepository professorsRepository;

    public List<Professor> getProfessors() {
        List<Professor> professors = new ArrayList<>();
        professorsRepository.findAll().forEach(professors::add);
        return professors;
    }

    public Professor getProfessorByDni(String dni) {
        return professorsRepository.findByDni(dni);
    }

    public Professor getProfessor(Long id) {
        return professorsRepository.findById(id).get();
    }

    public void addProfessor(Professor professor) {
        // Si en Id es null le asignamos el último + 1 de la lista
        professor.setDni(professor.getDni().toUpperCase());
        professorsRepository.save(professor);

    }

    public void deleteProfessor(Long id) {
        professorsRepository.deleteById(id);
    }

}