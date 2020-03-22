package ru.bank.account.entity.restTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * сущность запроса осуществления перевода для сервиса{@link ru.bank.account.service.TransferRestController}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityRq
{
    @NonNull
    @Size(max = 32)
    private String rquid;

    @NotNull
    @Size(max = 25)
    private String accountNumberFrom;

    @NotNull
    @Size(max = 25)
    private String accountNumberTo;

    @NotNull
    private BigDecimal money;
}