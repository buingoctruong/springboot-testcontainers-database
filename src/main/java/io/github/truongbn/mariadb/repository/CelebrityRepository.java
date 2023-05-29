package io.github.truongbn.mariadb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.truongbn.mariadb.entity.Celebrity;

public interface CelebrityRepository extends JpaRepository<Celebrity, Integer> {
    Optional<Celebrity> findById(Integer celebrityId);
}
