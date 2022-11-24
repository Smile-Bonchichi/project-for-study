package kg.it.academy.OnlineAuction.repository;

import kg.it.academy.OnlineAuction.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
