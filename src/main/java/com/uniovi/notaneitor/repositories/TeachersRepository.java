package com.uniovi.notaneitor.repositories;

import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeachersRepository  extends CrudRepository<Teacher,Long> {

}
