package com.form_builder.backend_forms_fer.forms.service.contracts;

import org.springframework.http.ResponseEntity;

public interface IDocumentBuildTemplate extends IFormSupportService {
    /**
     * Gets the document build request template for the given document ID.
     *
     * @param id the document ID
     * @return the document build request template
     */
    public ResponseEntity<byte[]> documentBuild(Long id);
}
