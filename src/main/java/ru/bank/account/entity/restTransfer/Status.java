package ru.bank.account.entity.restTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Исформация о статусе{@link EntityRs}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    private String code;
    private String description;
}

