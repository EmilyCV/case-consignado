package br.com.consigned.consigned_model.model;


import java.util.List;

public class Client {
    private String docClient;
    private String name;
    private boolean accountHolder;
    private String segment;
    private String agreement;
    private List<Simulation> simulations;
    private List<Contract> contracts;
}
