package kg.it.academy.OnlineAuction.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "histories")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class History extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "auction_id", nullable = false)
    Auction auction;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name = "price", nullable = false)
    BigDecimal price;
}
