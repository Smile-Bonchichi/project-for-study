package kg.it.academy.OnlineAuction.repository;

import kg.it.academy.OnlineAuction.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
