package kg.it.academy.OnlineAuction.repository;

import kg.it.academy.OnlineAuction.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT t.* FROM users t WHERE t.login = :loginOrEmail or t.email = :loginOrEmail")
    User findByLoginOrEmail(String loginOrEmail);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users SET wallet = :newWallet WHERE id = :userId")
    void updateWallet(BigDecimal newWallet, Long userId);
}
