package sv.edu.udb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import sv.edu.udb.configuration.TestInfrastructureConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransferServiceTest {
    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = SpringApplication.run(TestInfrastructureConfig.class);
    }

    @Test
    void getBeanByCastAndTransferMoney(){
        final TransferService transferService = (TransferService) context.getBean("transferService");
        assertNotNull(transferService);
        final Double amountTransfered = transferService.transfer(1, 2, 20D);
        assertEquals(400D, amountTransfered);

    }

    @Test
    void getBeanTypeMethodAndTransferMoney(){
        final TransferService transferService = context.getBean("transferService", TransferService.class);
        assertNotNull(transferService);
        final Double amountTransfered = transferService.transfer(1, 2, 20D);
        assertEquals(400D, amountTransfered);
    }

    @Test
    void getBeanByBeanIdWhenIdIsUniqueAndTransferMoney(){
        final TransferService transferService = context.getBean(TransferService.class);
        assertNotNull(transferService);
        final Double amountTransfered = transferService.transfer(1, 2, 20D);
        assertEquals(400D, amountTransfered);
    }




}
