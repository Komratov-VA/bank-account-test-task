package ru.bank.account.entity.JPA;

import org.springframework.data.repository.CrudRepository;

public interface HistoryTransactionalRepo extends CrudRepository<HistoryTransactional, Long> {
}
