package br.com.consigned.consignedsimulatorservice.exception;

public class InstallmentsLimitExceededException extends RuntimeException{
    public InstallmentsLimitExceededException(String name) {
        super("The number of installments exceeds that allowed for the segment: " + name);
    }
}
