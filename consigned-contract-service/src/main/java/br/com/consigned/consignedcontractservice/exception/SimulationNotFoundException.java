package br.com.consigned.consignedcontractservice.exception;

public class SimulationNotFoundException extends RuntimeException {
    public SimulationNotFoundException() {
        super("No simulations found");
    }
}
