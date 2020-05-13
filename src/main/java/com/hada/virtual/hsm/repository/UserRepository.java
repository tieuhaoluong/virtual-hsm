package com.hada.virtual.hsm.repository;

import com.hada.virtual.hsm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);

	Optional<User> findByEmail(String email);

	Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String login);

	Optional<User> findOneWithAuthoritiesByLogin(String lowercaseLogin);
}
