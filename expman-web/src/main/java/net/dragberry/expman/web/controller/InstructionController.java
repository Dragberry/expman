package net.dragberry.expman.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.business.AccountService;
import net.dragberry.expman.business.CounterPartyService;
import net.dragberry.expman.business.InstructionService;
import net.dragberry.expman.business.ReferenceService;
import net.dragberry.expman.business.TransactionTypeService;
import net.dragberry.expman.web.common.Constants;

@Controller
public class InstructionController implements Serializable {

	private static final long serialVersionUID = 3872073856599891990L;
	
	@Autowired
	private InstructionService instructionService;
	@Autowired
	private TransactionTypeService transactionTypeService;
	@Autowired
	private CounterPartyService counterPartyService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ReferenceService referenceService;
	
	@RequestMapping(value = Constants.Path.INSTRUCTION_CREATE)
	public ModelAndView createTransaction() {
		ModelAndView modelAndView = new  ModelAndView(Constants.View.INSTRUCTION_CREATE);
		return modelAndView;
	}

}
