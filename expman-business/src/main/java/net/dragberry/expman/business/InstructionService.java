package net.dragberry.expman.business;

import java.io.Serializable;

import net.dragberry.expman.bean.InstructionTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.query.InstructionCreateQuery;

public interface InstructionService extends Serializable {
	
	public ResultTO<InstructionTO> createInstruction(InstructionCreateQuery query);

}
