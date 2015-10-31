package net.dragberry.expman.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.dragberry.expman.domain.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByRoleName(String roleName);
	
	Set<Role> findByRoleNameIn(Collection<String> roleNames);
	
}
