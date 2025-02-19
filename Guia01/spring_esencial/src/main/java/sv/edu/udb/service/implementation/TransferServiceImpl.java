package sv.edu.udb.service.implementation;

import sv.edu.udb.repository.AccountRepository;
import sv.edu.udb.service.TransferService;

import java.util.Objects;

public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;

    public TransferServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = Objects.requireNonNull(accountRepository);
    }

    @Override
    public Double transfer(final Integer userId1, final Integer userId2, final Double amount){
        final String accountNumberUser1 = accountRepository.findAccountNumber(userId1);
        final String accountNumber2 = accountRepository.findAccountNumber(userId2);
        return amount * amount;
    }
}
