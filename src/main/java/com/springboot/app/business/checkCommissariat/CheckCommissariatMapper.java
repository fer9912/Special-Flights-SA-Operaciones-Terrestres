package com.springboot.app.business.checkCommissariat;

import com.springboot.app.business.checkCommissariat.model.CheckCommissariatDE;
import com.springboot.app.business.checkCommissariat.model.CheckCommissariatTO;

public class CheckCommissariatMapper {

	public static CheckCommissariatTO mapTo(CheckCommissariatDE de) {
		CheckCommissariatTO to = new CheckCommissariatTO();

		to.setIdCheckCommissariat(de.getIdCheckCommissariat());
		to.setIdFlight(de.getIdFlight());
		to.setA1(de.isA1());
		to.setA2(de.isA2());
		to.setA3(de.isA3());
		to.setA4(de.isA4());
		to.setA5(de.isA5());
		return to;
	}

	public static CheckCommissariatDE mapDE(CheckCommissariatTO to) {
		CheckCommissariatDE de = new CheckCommissariatDE();

		de.setIdCheckCommissariat(to.getIdCheckCommissariat());
		de.setIdFlight(to.getIdFlight());
		de.setA1(to.isA1());
		de.setA2(to.isA2());
		de.setA3(to.isA3());
		de.setA4(to.isA4());
		de.setA5(to.isA5());

		return de;
	}

}
