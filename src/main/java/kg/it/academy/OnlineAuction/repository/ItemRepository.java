package kg.it.academy.OnlineAuction.repository;

import kg.it.academy.OnlineAuction.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(nativeQuery = true, value = "SELECT t.* FROM items t WHERE t.user_id = :id")
    List<Item> getMyItem(Long id);

    @Query(nativeQuery = true, value = "SELECT t.* FROM items t WHERE t.id = :id")
    Item getItemById(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE items SET user_id = :userId WHERE id = :itemId")
    void updateUserIdOnItem( Long userId, Long itemId);
}
