package com.form_builder.backend_forms_fer.forms.service.contracts;

import com.form_builder.backend_forms_fer.forms.model.shared.*;
import org.springframework.http.ResponseEntity;

public interface IFormCRUDService {

    /**
     * Saves the given form data to the database.
     *
     * @param requestDto The request data object containing the form to be saved.
     * @return A ResponseEntity containing the saved form.
     */
    ResponseEntity<ApiResponseDto> save(ApiRequestDto requestDto);
    /**
     * Updates the given form data in the database.
     *
     * @param requestDto The request data object containing the form to be updated.
     * @return A ResponseEntity containing the updated form.
     */
    ResponseEntity<ApiResponseDto> update(ApiRequestDto requestDto);
    /**
     * Finds a form by its ID.
     *
     * @param requestDto The request data object containing the ID of the form to be found.
     * @return A ResponseEntity containing the found form, or an error response if the form is not found.
     */
    ResponseEntity<ApiResponseDto> findById(ApiRequestDto requestDto);
}
