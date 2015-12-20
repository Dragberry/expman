package net.dragberry.expman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.dragberry.expman.domain.Interchange;

public interface InterchangeRepo extends JpaRepository<Interchange, Long> {

	@Query("select i.interchangeKey, it.interchangeTypeKey from Interchange i, InterchangeType it where it.interchangeTypeKey = i.interchangeType")
	List<Object> fetchInterchangeList();
	
}
