package br.com.consigned.userverificationservice.controller.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class ClientRequest {
    private String docClient;
    private String name;
    private boolean accountHolder;
    private Integer segment;
    private int agreement;
}
