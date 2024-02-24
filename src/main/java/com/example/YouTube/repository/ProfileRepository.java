package com.example.YouTube.repository;

import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.method.P;
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
@Transactional
@Modifying
@Query("update ProfileEntity p set p.password=?2 where p.email=?1")
    Boolean updatePassword(String email, String newPassword);
@Transactional
@Modifying
@Query("update  ProfileEntity  set name=?1,surname=?2 where id=?3")
    void updateNameAndSurname(String name, String surname, Integer id);
@Transactional
@Modifying
@Query("update ProfileEntity set email=?2 where email=?1")
    void updateEmail(String email, String newEmail);
}

