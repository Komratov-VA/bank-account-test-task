package ru.bank.account.entity.JPA;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface AccountRepo extends CrudRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);

    @Modifying
    @Query("UPDATE Account ac SET ac.money = :money1 WHERE ac.accountNumber = :accountNumber1")
    void updateBook(@Param("money1") BigDecimal money1, @Param("accountNumber1") String accountNumber1);
}
