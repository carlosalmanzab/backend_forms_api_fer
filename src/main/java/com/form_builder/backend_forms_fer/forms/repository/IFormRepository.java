package com.form_builder.backend_forms_fer.forms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IFormRepository <T, ID> {
     /**
      * Retrieves all entities.
      *
      * @return a List of all entities
      */
     List<T> findAll();
     /**
      * Retrieves an entity by its formId.
      *
      * @param id the formId of the entity to be retrieved
      * @return an Optional containing the entity if found, or an empty Optional if not found
      */
     Optional<T> findById(ID id);
     /**
      * Deletes the entity with the given formId.
      *
      * @param formId the formId of the entity to be deleted
      */
     void deleteById(Long formId);
}
