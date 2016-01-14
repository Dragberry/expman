package net.dragberry.expman.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
//@Table(name = "COUNTER_PARTY")
public class Instruction implements Serializable {

	private static final long serialVersionUID = -2614645869547074197L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INSTRUCTION_KEY")
	private Long instructionKey;
	
	@OneToMany
	@JoinColumn()
	private List<Transaction> transactions;
	
	
}
