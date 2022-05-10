package com.springboot.app.business.checkFlight;

import com.springboot.app.business.checkFlight.model.CheckFlightDE;
import com.springboot.app.business.checkFlight.model.CheckFlightTO;

public class CheckFlightMapper {

	public static CheckFlightTO mapTo(CheckFlightDE de) {
		CheckFlightTO to = new CheckFlightTO();
		to.setIdCheckFlight(de.getIdCheckFlight());
		to.setA1(de.isA1());
		to.setA2(de.isA2());
		to.setA3(de.isA3());
		to.setA4(de.isA4());
		to.setA5(de.isA5());
		to.setA6(de.isA6());
		to.setA7(de.isA7());
		to.setA8(de.isA8());
		to.setA9(de.isA9());
		to.setA10(de.isA10());
		to.setA11(de.isA11());
		to.setA12(de.isA12());
		to.setA13(de.isA13());
		to.setA14(de.isA14());
		to.setA15(de.isA15());
		to.setA16(de.isA16());
		to.setA17(de.isA17());
		to.setA18(de.isA18());
		to.setA19(de.isA19());
		to.setA20(de.isA20());
		to.setA21(de.isA21());
		to.setA22(de.isA22());
		to.setA23(de.isA23());
		to.setA24(de.isA24());
		to.setB1(de.isB1());
		to.setB2(de.isB2());
		to.setB3(de.isB3());
		to.setB4(de.isB4());
		to.setB5(de.isB5());
		to.setB6(de.isB6());
		to.setB7(de.isB7());
		to.setB8(de.isB8());
		to.setB9(de.isB9());
		to.setB10(de.isB10());
		to.setB11(de.isB11());
		to.setB12(de.isB12());
		to.setB13(de.isB13());
		to.setB14(de.isB14());
		to.setC1(de.isC1());
		to.setC2(de.isC2());
		to.setC3(de.isC3());
		to.setC4(de.isC4());
		to.setC5(de.isC5());
		to.setC6(de.isC6());
		to.setC7(de.isC7());
		to.setC8(de.isC8());
		to.setC9(de.isC9());
		to.setC10(de.isC10());
		to.setC11(de.isC11());
		to.setC12(de.isC12());
		to.setD1(de.isD1());
		to.setD2(de.isD2());
		to.setD3(de.isD3());
		to.setE1(de.isE1());

		return to;
	}

	public static CheckFlightDE mapDe(CheckFlightTO to) {
		CheckFlightDE de = new CheckFlightDE();
		de.setIdCheckFlight(to.getIdCheckFlight());
		de.setA1(to.isA1());
		de.setA2(to.isA2());
		de.setA3(to.isA3());
		de.setA4(to.isA4());
		de.setA5(to.isA5());
		de.setA6(to.isA6());
		de.setA7(to.isA7());
		de.setA8(to.isA8());
		de.setA9(to.isA9());
		de.setA10(to.isA10());
		de.setA11(to.isA11());
		de.setA12(to.isA12());
		de.setA13(to.isA13());
		de.setA14(to.isA14());
		de.setA15(to.isA15());
		de.setA16(to.isA16());
		de.setA17(to.isA17());
		de.setA18(to.isA18());
		de.setA19(to.isA19());
		de.setA20(to.isA20());
		de.setA21(to.isA21());
		de.setA22(to.isA22());
		de.setA23(to.isA23());
		de.setA24(to.isA24());
		de.setB1(to.isB1());
		de.setB2(to.isB2());
		de.setB3(to.isB3());
		de.setB4(to.isB4());
		de.setB5(to.isB5());
		de.setB6(to.isB6());
		de.setB7(to.isB7());
		de.setB8(to.isB8());
		de.setB9(to.isB9());
		de.setB10(to.isB10());
		de.setB11(to.isB11());
		de.setB12(to.isB12());
		de.setB13(to.isB13());
		de.setB14(to.isB14());
		de.setC1(to.isC1());
		de.setC2(to.isC2());
		de.setC3(to.isC3());
		de.setC4(to.isC4());
		de.setC5(to.isC5());
		de.setC6(to.isC6());
		de.setC7(to.isC7());
		de.setC8(to.isC8());
		de.setC9(to.isC9());
		de.setC10(to.isC10());
		de.setC11(to.isC11());
		de.setC12(to.isC12());
		de.setD1(to.isD1());
		de.setD2(to.isD2());
		de.setD3(to.isD3());
		de.setE1(to.isE1());

		return de;
	}
}
