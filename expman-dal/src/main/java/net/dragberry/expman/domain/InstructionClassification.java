package net.dragberry.expman.domain;

public enum InstructionClassification {
	
	PAYMENT("payment"), TRANSFER("transfer"), COLLECTION("collection");

	private String description;
	
	private InstructionClassification(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
