package com.example.YouTube.repository;

import com.example.YouTube.entity.PlaylistEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlaylistRepository extends CrudRepository<PlaylistEntity,Integer> {
    @Query("from PlaylistEntity where order_num=?1")
    Optional<PlaylistEntity> getByOrder_num(String num);
    @Transactional
    @Modifying
    @Query("update PlaylistEntity set status=?1 where order_num=?2")
    void change_status(String status, String num);
}
