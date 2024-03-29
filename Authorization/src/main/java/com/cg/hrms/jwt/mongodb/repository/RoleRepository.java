package com.cg.hrms.jwt.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cg.hrms.jwt.mongodb.models.ERole;
import com.cg.hrms.jwt.mongodb.models.Role;
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
