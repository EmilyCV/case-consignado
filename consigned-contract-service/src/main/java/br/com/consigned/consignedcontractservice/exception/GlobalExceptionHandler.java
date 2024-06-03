package br.com.consigned.consignedcontractservice.exception;

import org.apache.kafka.common.errors.*;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SimulationNotFoundException.class)
    public ResponseEntity<String> simulationNotFoundException(SimulationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception e) {
        return new ResponseEntity<>("An error occurred;error=" + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<Object> handleHttpStatusCodeException(Exception e) {
        return new ResponseEntity<>("HTTP status code exception occurred during client validation;error=" + e.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Object> handleRestClientException(Exception e) {
        return new ResponseEntity<>("Rest client exception occurred during client validation;error=" + e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIlLegalArgumentException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(DataAccessException e) {
        return new ResponseEntity<>("Error accessing required data;error=" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<Object> handleTimeoutException(TimeoutException e) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(e.getMessage());
    }

    @ExceptionHandler(SerializationException.class)
    public ResponseEntity<Object> handleSerializationException(SerializationException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
    }

    @ExceptionHandler(RecordTooLargeException.class)
    public ResponseEntity<Object> handleRecordTooLargeException(RecordTooLargeException e) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("The registry is too large;error=" + e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(OffsetOutOfRangeException.class)
    public ResponseEntity<Object> handleOffsetOutOfRangeException(OffsetOutOfRangeException e) {
        return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(e.getMessage());
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new ResponseEntity<>("No data found for the request;error=" + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(InvalidTopicException.class)
    public ResponseEntity<Object> handleInvalidTopicException(InvalidTopicException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The topic provided is invalid;error=" + e.getMessage());
    }

    @ExceptionHandler(UnsupportedForMessageFormatException.class)
    public ResponseEntity<Object> handleUnsupportedForMessageFormatException(UnsupportedForMessageFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message format is not supported;error=" + e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate key detected. Discarding message;error=" + e.getMessage());
    }

}
