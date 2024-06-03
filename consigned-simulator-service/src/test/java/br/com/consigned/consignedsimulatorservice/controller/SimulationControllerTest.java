package br.com.consigned.consignedsimulatorservice.controller;

import br.com.consigned.consigned_model.enums.AgreementType;
import br.com.consigned.consigned_model.enums.SegmentType;
import br.com.consigned.consigned_model.model.Client;
import br.com.consigned.consignedsimulatorservice.controller.request.SimulationRequest;
import br.com.consigned.consignedsimulatorservice.exception.CustomerNotFoundException;
import br.com.consigned.consignedsimulatorservice.exception.InstallmentsLimitExceededException;
import br.com.consigned.consignedsimulatorservice.service.SimulationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SimulationControllerTest {

    @Mock
    private SimulationService simulationService;

    @InjectMocks
    private SimulationController simulationController;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRequestSimulation() {
        SimulationRequest request = getSimulationRequest();

        Client client = createClient();
        when(simulationService.validationClient(anyString())).thenReturn(client);
        when(simulationService.createSimulation(anyInt(), any(), any())).thenReturn(any());

        ResponseEntity<?> response = simulationController.requestSimulation(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(simulationService, times(1)).validationClient("123456789");
        verify(simulationService, times(1)).createSimulation(13, BigDecimal.valueOf(1000.0), client);
    }

    @Test
    public void testListSimulations() {
        when(simulationService.listSimulations(anyInt())).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = simulationController.listSimulations(null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(simulationService, times(1)).listSimulations(null);
    }


    @Test
    public void testRequestSimulationCustomerNotFoundException() {
        SimulationRequest request = getSimulationRequest();

        when(simulationService.validationClient(any())).thenThrow(new CustomerNotFoundException());

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
            simulationController.requestSimulation(request);
        });

        assertEquals("No customers found with the document provided", exception.getMessage());
    }

    @Test
    public void testRequestSimulationInstallmentsLimitExceededException() {
        SimulationRequest request = getSimulationRequest();

        Client client = createClient();
        when(simulationService.validationClient(anyString())).thenReturn(client);
        when(simulationService.createSimulation(anyInt(), any(), any())).thenThrow(new InstallmentsLimitExceededException(SegmentType.VAREJO.name()));


        Exception exception = assertThrows(InstallmentsLimitExceededException.class, () -> {
            simulationController.requestSimulation(request);
        });

        assertEquals("The number of installments exceeds that allowed for the segment: " + SegmentType.VAREJO.name(), exception.getMessage());
    }

    private static SimulationRequest getSimulationRequest() {
        SimulationRequest request = new SimulationRequest();
        request.setDocument("123456789");
        request.setQtdInstallments(13);
        request.setValueConsigned(BigDecimal.valueOf(1000.0));
        return request;
    }

    private Client createClient() {
        return Client.builder()
                .docClient("123456789")
                .name("Emily")
                .accountHolder(true)
                .segment(SegmentType.VAREJO)
                .agreement(AgreementType.EP)
                .build();
    }
}
