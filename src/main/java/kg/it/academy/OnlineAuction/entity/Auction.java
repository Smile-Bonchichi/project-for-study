package kg.it.academy.OnlineAuction.entity;

import kg.it.academy.OnlineAuction.enums.Status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "auctions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Auction extends BaseEntity {
    @Column(name = "start_time", nullable = false)
    LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    LocalDateTime endTime;

    @Column(name = "start_price", nullable = false)
    BigDecimal startPrice;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;
}
