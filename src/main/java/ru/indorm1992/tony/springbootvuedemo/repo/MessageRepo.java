package ru.indorm1992.tony.springbootvuedemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.indorm1992.tony.springbootvuedemo.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
