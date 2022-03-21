package com.vedegiska.gateway_microservice.repo;

import com.vedegiska.gateway_microservice.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Set<Role> findByNameIn(Collection<String> names);
}
