package jp.co.bhopari.tutorial_calculator.services.impl​;

import org.springframework.stereotype.Service;

import jp.co.bhopari.tutorial_calculator.services.CalcService;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionX;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionY;

@Service
public class CalcServiceImpl implements CalcService {

	/* 定数化
	 *  繰り返し書くと修正が入ったときに修正漏れの可能性が高くなるから定数化する
	 *  →定数化については「 CalcServiceController.java 」に追加説明があります
	 *
	 *  x と y それぞれに入力できる最大値は 100、最小値は -100
	 */

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

	/*
	 * @inheritDoc：インターフェース（スーパークラス）のドキュメントコメントを、オーバーライドした側にも挿入される
	 * @Override：インターフェース（スーパークラス）のメソッドをオーバーライドできていない状況を防げるもの
	 */

	/**
	 * {@inheritDoc}
	 */
	// 全体の流れ解説(以降▼)2
	@Override
	public double add(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		// ▼3 入力値X範囲チェック
		if (x < ADD_MIN_VALUE_X || x > ADD_MAX_VALUE_X) {
			throw new IllegalArgumentExceptionX();
			// ▼4 入力値Y範囲チェック
		} else if (y < ADD_MIN_VALUE_Y || y > ADD_MAX_VALUE_Y) {
			throw new IllegalArgumentExceptionY();
		}

		// ▼5
		double result = x + y;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double divide(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		if (x < DIVIDE_MIN_VALUE_X || x > DIVIDE_MAX_VALUE_X) {
			throw new IllegalArgumentExceptionX();
		} else if (y < DIVIDE_MIN_VALUE_Y || y > DIVIDE_MAX_VALUE_Y) {
			throw new IllegalArgumentExceptionY();
			// ゼロ除算チェック
		} else if (y == 0) {
			throw new ArithmeticException();
		}
		double result = (double) x / y;
		return result;
	}
}
