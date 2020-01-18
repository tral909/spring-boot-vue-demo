package ru.indorm1992.tony.springbootvuedemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.indorm1992.tony.springbootvuedemo.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
