package kg.it.academy.OnlineAuction.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @Column(name = "login", nullable = false, unique = true)
    String login;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "wallet", nullable = false)
    BigDecimal wallet;

    @ManyToOne
    @JoinColumn(name = "image_id")
    Image image;

    @Column(name = "is_active")
    Long isActive;
}
