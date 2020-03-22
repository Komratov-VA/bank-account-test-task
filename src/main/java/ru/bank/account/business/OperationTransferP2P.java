package ru.bank.account.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bank.account.entity.JPA.Account;
import ru.bank.account.entity.JPA.AccountRepo;
import ru.bank.account.entity.JPA.HistoryTransactional;
import ru.bank.account.entity.JPA.HistoryTransactionalRepo;
import ru.bank.account.entity.restTransfer.EntityRq;
import ru.bank.account.entity.restTransfer.EntityRs;
import ru.bank.account.entity.restTransfer.Status;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;

@Service
public class OperationTransferP2P {

    private static final String ONE = "1";
    private static final String TWO = "2";

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    HistoryTransactionalRepo historyTransactionalRepo;

    @Transactional
    public EntityRs doTransferMoney(EntityRq entityRq) {
        String desc = preCheck(entityRq);
       if(Objects.isNull(desc)){
          return aVoid(entityRq);
       }
       return returnEntityRs(TWO, desc);
    }

    private String preCheck(EntityRq entityRq) {

        if (Objects.isNull(accountRepo.findByAccountNumber(entityRq.getAccountNumberFrom()))) {
            return "Счет зачисления не существует";
        }
        if (Objects.isNull(accountRepo.findByAccountNumber(entityRq.getAccountNumberTo()))) {
            return "Счет списания не существует";
        }
        if(accountRepo.findByAccountNumber(entityRq.getAccountNumberFrom()).getMoney().compareTo(entityRq.getMoney()) == -1 )
        {
            return "на счете не хватает денег";
        }
        return null;
    }

    @Transactional
    EntityRs aVoid(EntityRq entityRq){
        Account accountTo = accountRepo.findByAccountNumber(entityRq.getAccountNumberTo());
        Account accountFrom = accountRepo.findByAccountNumber(entityRq.getAccountNumberFrom());
        BigDecimal moneyTo = accountTo.getMoney();
        BigDecimal moneyFrom = accountFrom.getMoney();
        accountRepo.updateBook(moneyTo.add(entityRq.getMoney()), accountTo.getAccountNumber());
        accountRepo.updateBook(moneyFrom.subtract(entityRq.getMoney()), accountFrom.getAccountNumber());
        String message = getMessageRs( accountFrom, accountTo, entityRq);
        return returnEntityRs(ONE, message);
    }

    private String getMessageRs(Account accountFrom, Account accountTo, EntityRq entityRq){
        String messageRs = "account from" +accountFrom.getAccountNumber()+"to account "+
                accountTo.getAccountNumber()+" on money " + entityRq.getMoney()+"create transactional";
        historyTransactionalRepo.save(new HistoryTransactional(entityRq.getRquid(), messageRs));
        return messageRs;
    }

    private EntityRs returnEntityRs(String code, String desc) {
        return new EntityRs(new Status(code, desc));
    }
}
