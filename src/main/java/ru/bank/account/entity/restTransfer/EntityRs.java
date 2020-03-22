package ru.bank.account.entity.restTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * сущность ответа на запрос{@link EntityRq} для сервиса{@link ru.bank.account.service.TransferRestController}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityRs {

    private Status status;

}
