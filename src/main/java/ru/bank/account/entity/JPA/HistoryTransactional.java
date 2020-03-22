package ru.bank.account.entity.JPA;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * сущность информации о переводе
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class HistoryTransactional {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column
    private String rquid;

    @NonNull
    @Column
    private String message;
}
