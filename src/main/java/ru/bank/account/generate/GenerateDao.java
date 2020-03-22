package ru.bank.account.generate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bank.account.entity.JPA.Account;
import ru.bank.account.entity.JPA.AccountRepo;

import java.math.BigDecimal;

/**
 * Сервис для генерации тестовых данных
 */
@Service
public class GenerateDao {

    @Autowired
    private AccountRepo accountRepo;

    public void generateDao()
    {
        for (int i=0;i<10;i++) {
            accountRepo.save(new Account(i+"2345678901234567890",new BigDecimal(12+i)));
        }
    }

}
