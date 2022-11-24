package kg.it.academy.OnlineAuction.repository;

import kg.it.academy.OnlineAuction.entity.History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query(nativeQuery = true, value =
            "        SELECT case " +
                    "   WHEN tab.price IS NULL THEN 0 " +
                    "   ELSE tab.price " +
                    "END " +
                    "FROM ( " +
                    "   SELECT " +
                    "       MAX(t.price) price " +
                    "   FROM histories t " +
                    "   WHERE t.auction_id = :id) tab ")
    BigDecimal getMaxAuctionPrice(Long id);

    @Query(nativeQuery = true, value =
            "        SELECT " +
                    "   t.user_id " +
                    "FROM " +
                    "   histories t " +
                    "WHERE " +
                    "   t.auction_id = :id AND " +
                    "   t.price = ( " +
                    "   SELECT " +
                    "       MAX(t2.price) " +
                    "   FROM " +
                    "       histories t2 " +
                    "   WHERE " +
                    "       t2.auction_id = :id)")
    Long getWinnerOnAuction(Long id);
}
