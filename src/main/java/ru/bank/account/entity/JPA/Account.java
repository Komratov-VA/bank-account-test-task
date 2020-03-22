package ru.bank.account.entity.JPA;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * сущность счета в бд
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @Column(unique = true)
    private String accountNumber;

    @NonNull
    @Column
    private BigDecimal money;


}