package it.albertus.spring.cassandra.dao;

import it.albertus.spring.cassandra.model.Persona;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDao extends CrudRepository<Persona, UUID> {

}
