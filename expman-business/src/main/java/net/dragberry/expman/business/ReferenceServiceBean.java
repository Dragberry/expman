package net.dragberry.expman.business;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReferenceServiceBean implements ReferenceService {

	private static final long serialVersionUID = -8175046098653004790L;

	@Override
	public List<String> fecthCurrecnyList() {
		return Arrays.asList("BYR", "USD", "EUR", "RUR");
	}

}
