package kg.it.academy.OnlineAuction.repository;

import kg.it.academy.OnlineAuction.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
