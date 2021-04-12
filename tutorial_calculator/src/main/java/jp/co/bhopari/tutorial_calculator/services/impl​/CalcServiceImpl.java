package jp.co.bhopari.tutorial_calculator.services.impl​;

import org.springframework.stereotype.Service;

import jp.co.bhopari.tutorial_calculator.services.CalcService;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionX;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionY;

@Service
public class CalcServiceImpl implements CalcService{
	final int ADD_MIN_VALUE_X = -100;
	final int ADD_MAX_VALUE_X = 100;
	final int ADD_MIN_VALUE_Y = -100;
	final int ADD_MAX_VALUE_Y = 100;

	final int SUBTRACT_MIN_VALUE_X = -100;
	final int SUBTRACT_MAX_VALUE_X = 100;
	final int SUBTRACT_MIN_VALUE_Y = -100;
	final int SUBTRACT_MAX_VALUE_Y = 100;

	final int MULTIPLY_MIN_VALUE_X = -100;
	final int MULTIPLY_MAX_VALUE_X = 100;
	final int MULTIPLY_MIN_VALUE_Y = -100;
	final int MULTIPLY_MAX_VALUE_Y = 100;

	final int DIVIDE_MIN_VALUE_X = -100;
	final int DIVIDE_MAX_VALUE_X = 100;
	final int DIVIDE_MIN_VALUE_Y = -100;
	final int DIVIDE_MAX_VALUE_Y = 100;

	@Override
	public double add(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		if (x < ADD_MIN_VALUE_X || x > ADD_MAX_VALUE_X) {
			throw new IllegalArgumentExceptionX();
		} else if (y < ADD_MIN_VALUE_Y || y > ADD_MAX_VALUE_Y) {
			throw new IllegalArgumentExceptionY();
		}
		double result = x + y;
		return result;
	}

	@Override
	public double subtract(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		if (x < SUBTRACT_MIN_VALUE_X || x > SUBTRACT_MAX_VALUE_X) {
			throw new IllegalArgumentExceptionX();
		} else if (y < SUBTRACT_MIN_VALUE_Y || y > SUBTRACT_MAX_VALUE_Y) {
			throw new IllegalArgumentExceptionY();
		}
		double result = x - y;
		return result;
	}

	@Override
	public double multiply(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		if (x < MULTIPLY_MIN_VALUE_X || x > MULTIPLY_MAX_VALUE_X) {
			throw new IllegalArgumentExceptionX();
		} else if (y < MULTIPLY_MIN_VALUE_Y || y > MULTIPLY_MAX_VALUE_Y) {
			throw new IllegalArgumentExceptionY();
		}
		double result = x * y;
		return result;
	}

	@Override
	public double divide(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		if (x < DIVIDE_MIN_VALUE_X || x > DIVIDE_MAX_VALUE_X) {
			throw new IllegalArgumentExceptionX();
		} else if (y < DIVIDE_MIN_VALUE_Y || y > DIVIDE_MAX_VALUE_Y) {
			throw new IllegalArgumentExceptionY();
		} else if (y == 0) {
			throw new ArithmeticException();
		}
		double result = (double) x / y;
		return result;
	}


}
