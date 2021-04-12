package jp.co.bhopari.tutorial_calculator.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalcServiceTest {
	@Autowired
	CalcService calcService;

	@Test
	public void 加算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 加算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 加算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 加算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 加算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 加算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -200.0;
		double actual = calcService.add(-100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 加算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.add(100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 加算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 加算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 加算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.add(-100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 加算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 200.0;
		double actual = calcService.add(100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 加算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 加算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 加算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 加算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 加算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 減算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 減算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.subtract(-100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 減算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 200.0;
		double actual = calcService.subtract(100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 減算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -200.0;
		double actual = calcService.subtract(-100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 減算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.subtract(100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 減算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 減算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 減算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 減算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 乗算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 乗算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 10000.0;
		double actual = calcService.multiply(-100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 乗算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -10000.0;
		double actual = calcService.multiply(100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 乗算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -10000.0;
		double actual = calcService.multiply(-100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 乗算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 10000.0;
		double actual = calcService.multiply(100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 乗算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 乗算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 乗算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 乗算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 徐算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 徐算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 1.0;
		double actual = calcService.divide(-100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 徐算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -1.0;
		double actual = calcService.divide(100, -100);
		assertEquals(expected, actual);
	}

	@Test
	public void 徐算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -1.0;
		double actual = calcService.divide(-100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 徐算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 1.0;
		double actual = calcService.divide(100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 徐算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 徐算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 徐算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	@Test
	public void 徐算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	@Test
	public void 除算_0除算_例外なし() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 1.0;
		double actual = calcService.divide(100, 100);
		assertEquals(expected, actual);
	}

	@Test
	public void 除算_0除算_例外あり() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(100, 0);
			assertTrue(false);
		} catch (ArithmeticException e) {
		}
	}


}
