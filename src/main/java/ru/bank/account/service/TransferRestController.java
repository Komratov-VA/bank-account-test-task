package ru.bank.account.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bank.account.business.OperationTransferP2P;
import ru.bank.account.entity.restTransfer.EntityRq;
import ru.bank.account.entity.restTransfer.EntityRs;
import ru.bank.account.entity.restTransfer.Status;

import javax.validation.Valid;
import java.util.logging.Logger;

/**
 * Cервис для осуществеления переводов с urn "/transferMoney"
 */
@RestController
public class TransferRestController {

    private static final Logger LOGGER = java.util.logging.Logger.getGlobal();

    @Autowired
    OperationTransferP2P operationTransferP2P;

    @PostMapping("/transferMoney")
    public EntityRs TransferMoneyP2P(@RequestBody @Valid EntityRq request) {
        LOGGER.info("Получен запрос "+ request);
        EntityRs entityRs;
        try {
           entityRs = operationTransferP2P.doTransferMoney(request);
        } catch (Exception e) {
            entityRs = new EntityRs(new Status("3",e.getMessage()));
            loggingResponse(entityRs);
            return entityRs;

        }
        loggingResponse(entityRs);
        return entityRs;
    }

    private void loggingResponse(EntityRs entityRs){
        LOGGER.info("Отправлен ответ"+ entityRs);
    }
}
