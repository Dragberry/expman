package net.dragberry.expman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.dragberry.expman.domain.Instruction;

@Repository
public interface InstructionRepo extends JpaRepository<Instruction, Long> {

}
