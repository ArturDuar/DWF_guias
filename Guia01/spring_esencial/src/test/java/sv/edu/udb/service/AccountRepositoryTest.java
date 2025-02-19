package sv.edu.udb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import sv.edu.udb.configuration.ApplicationConfig;
import sv.edu.udb.configuration.TestInfrastructureConfig;
import sv.edu.udb.repository.AccountRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestInfrastructureConfig.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findAccountNumber(){
        assertNotNull(accountRepository);
        final String accountNumber = accountRepository.findAccountNumber(1);
        assertNotNull(accountNumber);
        assertEquals(36, accountNumber.length());
    }


}
