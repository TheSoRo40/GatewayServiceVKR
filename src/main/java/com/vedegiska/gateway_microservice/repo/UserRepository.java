package com.vedegiska.gateway_microservice.repo;

import com.vedegiska.gateway_microservice.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Set<User> findByRoles_NameEquals(String name);
}
