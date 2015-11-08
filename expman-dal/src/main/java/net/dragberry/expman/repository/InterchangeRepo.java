package net.dragberry.expman.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.dragberry.expman.domain.Interchange;

public interface InterchangeRepo extends JpaRepository<Interchange, Long> {

}
