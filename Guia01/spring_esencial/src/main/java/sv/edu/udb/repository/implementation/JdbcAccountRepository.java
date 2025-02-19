package sv.edu.udb.repository.implementation;

import sv.edu.udb.repository.AccountRepository;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.UUID;

public class JdbcAccountRepository implements AccountRepository {
    private final DataSource ds;

    public JdbcAccountRepository(DataSource ds) {
        this.ds = Objects.requireNonNull(ds);
    }

    @Override
    public String findAccountNumber(final Integer userId){
        return UUID.randomUUID().toString();
    }
}
