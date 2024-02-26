package com.example.YouTube.repository;

import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmail(String email);
    @Modifying
    @Transactional
    @Query("update ProfileEntity set status=?2 where id=?1")
    void updateStatus(Integer id, ProfileStatus profileStatus);

    Optional<ProfileEntity> findByEmailAndPassword(String email, String encode);
}

