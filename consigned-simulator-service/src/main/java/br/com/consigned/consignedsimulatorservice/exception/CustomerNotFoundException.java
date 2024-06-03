package br.com.consigned.consignedsimulatorservice.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("No customers found with the document provided");
    }
}
