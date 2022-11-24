package kg.it.academy.OnlineAuction.repository;

import kg.it.academy.OnlineAuction.entity.Auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    @Query(nativeQuery = true, value =
            "        SELECT " +
                    "   case " +
                    "       WHEN t.status = 'ACTIVE' THEN TRUE " +
                    "       ELSE FALSE " +
                    "   end " +
                    "FROM " +
                    "   auctions t " +
                    "WHERE " +
                    "   t.id = :id")
    Boolean getIsActiveAuction(Long id);

    @Query(nativeQuery = true, value = "SELECT t.* FROM auctions t WHERE t.id = :id")
    Auction getAuctionById(Long id);

    @Query(nativeQuery = true, value = "SELECT t.* FROM auctions t WHERE t.status != 'NOT_ACTIVE'")
    List<Auction> getAuctionByActiveAndAdvertising();

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE auctions SET status = :newStatus WHERE id = :auctionId")
    void updateStatus(String newStatus, Long auctionId);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM auctions t WHERE t.item_id = :id")
    Integer checkUniqueItemOnAuction(Long id);

    @Query(nativeQuery = true, value = "SELECT t.* FROM auctions t " +
            "                                   WHERE UPPER(t.name) like UPPER('%' || :nameAuction || '%') " +
            "                                   AND t.status IN ('ACTIVE', 'IN_ADVERTISING')")
    List<Auction> getAllByName(String nameAuction);

    @Query(nativeQuery = true, value = "SELECT t.* FROM auctions t WHERE t.status = 'IN_ADVERTISING'")
    List<Auction> getAllByInAdvertising();

    @Query(nativeQuery = true, value = "SELECT t.* FROM auctions t WHERE t.status = 'ACTIVE'")
    List<Auction> getAllByActive();
}
