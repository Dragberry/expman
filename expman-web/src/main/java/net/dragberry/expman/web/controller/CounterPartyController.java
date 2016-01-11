package net.dragberry.expman.web.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.dragberry.expman.bean.CounterPartyTO;
import net.dragberry.expman.bean.ResultTO;
import net.dragberry.expman.business.CounterPartyService;
import net.dragberry.expman.query.CounterPartyCreateQuery;
import net.dragberry.expman.web.common.Constants;
import net.dragberry.expman.web.security.ExpmanSecurityContext;

@Controller
public class CounterPartyController implements Serializable {

	private static final String COUNTER_PARTY_MODEL = "counterParty";

	private static final long serialVersionUID = 4273757467655999554L;

	@Autowired
	private CounterPartyService counterPartyService;
	
	@RequestMapping(value = Constants.Path.COUNTER_PARTY_CREATE)
	public ModelAndView createCounterParty(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView(Constants.View.COUNTER_PARTY_CREATE);
		modelAndView.addObject(COUNTER_PARTY_MODEL, new CounterPartyCreateQuery());
		
		return modelAndView;
	}
	
	@RequestMapping(value = Constants.Path.COUNTER_PARTY_CREATE, method = RequestMethod.POST)
	public ModelAndView createCounterParty(CounterPartyCreateQuery counterParty, HttpServletRequest request) {
		counterParty.setCustomerKey(ExpmanSecurityContext.getCustomerKey());
		ResultTO<CounterPartyTO> result = counterPartyService.createCounterParty(counterParty);
		if (result.hasIssues()) {
			ModelAndView modelAndView = new ModelAndView(Constants.View.COUNTER_PARTY_CREATE);
			modelAndView.addObject(COUNTER_PARTY_MODEL, counterParty);
			return modelAndView;
		} else {
			return new ModelAndView(Constants.View.HOME);
		}
	}

}
