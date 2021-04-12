package jp.co.bhopari.tutorial_calculator.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * 【テストについて】
 *  設計書をもとに、齟齬がないかどうか網羅的にテストできることが大事
 *
 *  サービスクラスが正常に動くかどうかではなく、設計書通りにサービスクラスが機能するかどうかのテストを行う
 *
 *  テストはマトリクス図などを作成して網羅的にテストを行う
 */


/*
 * @SpringBootTest：テストでSpringBootの機能（複数のフレームワークを使用する際に発生する、設定を可能な限り自動設定する）
 *                  を使えるようにしているもの
 */
@SpringBootTest
public class CalcServiceTest {

	/*
	 *  CalcService.java のオブジェクトを変数 calcService にセット
	 *  →@Autowired については「 CalcServiceController.java 」に追加説明があります
	 */
	@Autowired
	CalcService calcService;


	/*
	 * assertTrue(false)：このソースコードが実行されたら、必ず失敗。
	 *
	 * ★つまり、抜け道を作らないために必要。
	 *
	 * ＜「 assertTrue(false) 」がない場合＞
	 *
	 * try {
	 *   calcService.add(-101, -101);
	 * } catch (IllegalArgumentExceptionX e) {
	 * }
	 *
	 * もし、calcService.add(-101, -101); が実行されたときに例外（IllegalArgumentExceptionX）が発生しなくても正常に終了する
	 * ★つまり、本来ならば例外が発生すべき条件なのに、例外が発生しなくてもエラーにはならず、正常だというテスト結果になってしまう
	 *
	 *
	 * ＜「 assertTrue(false) 」がある場合＞
	 *
	 * try {
	 *   calcService.add(-101, -101);
	 *   assertTrue(false);
	 * } catch (IllegalArgumentExceptionX e) {
	 * }
	 *
	 * もし、calcService.add(-101, -101); が実行されたときに例外（IllegalArgumentExceptionX）が発生しなかったら、
	 * 次に書いてある assertTrue(false); が実行されてエラーが発生し異常終了する
	 * ★つまり、本来ならば例外が発生すべき条件で例外が発生しなかったら、assertTrue(false); が実行されることで、
	 *   テスト結果で異常を確認することができる
	 *
	 *
	 *
	 * 加算①のテストでは、、、
	 * calcService.add(-101, -101); は1つ目の引数が -101 で範囲外のため、例外（IllegalArgumentExceptionX）が発生し、
	 * assertTrue(false); を読まずに（実行せずに）飛び越えて catch されるとテスト成功となる
	 */

	// 加算
	// 加算① X範囲外、Y範囲外
	@Test
	public void 加算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 加算② X範囲内、Y範囲外
	@Test
	public void 加算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 加算③ X範囲内、Y範囲外
	@Test
	public void 加算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 加算④ X範囲内、Y範囲外
	@Test
	public void 加算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 加算⑤ X範囲外、Y範囲内
	@Test
	public void 加算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	/*
	 * assertEquals(expected, actual)
	 * 期待される結果（expected）と実際の結果（actual）が同じかどうか判定する
	 * 同じであればテスト成功
	 */

	// 加算⑥ X範囲内、Y範囲内
	@Test
	public void 加算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -200.0;
		double actual = calcService.add(-100, -100);
		assertEquals(expected, actual);
	}

	// 加算⑦ X範囲内、Y範囲内
	@Test
	public void 加算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.add(100, -100);
		assertEquals(expected, actual);
	}

	// 加算⑧ X範囲外、Y範囲内
	@Test
	public void 加算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 加算⑨ X範囲外、Y範囲内
	@Test
	public void 加算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 加算⑩ X範囲内、Y範囲内
	@Test
	public void 加算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.add(-100, 100);
		assertEquals(expected, actual);
	}

	// 加算⑪ X範囲内、Y範囲内
	@Test
	public void 加算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 200.0;
		double actual = calcService.add(100, 100);
		assertEquals(expected, actual);
	}

	// 加算⑫ X範囲外、Y範囲内
	@Test
	public void 加算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 加算⑬ X範囲外、Y範囲外
	@Test
	public void 加算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 加算⑭ X範囲内、Y範囲外
	@Test
	public void 加算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 加算⑮ X範囲内、Y範囲外
	@Test
	public void 加算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 加算⑯ X範囲外、Y範囲外
	@Test
	public void 加算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.add(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算
	// 減算① X範囲外、Y範囲外
	@Test
	public void 減算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算② X範囲内、Y範囲外
	@Test
	public void 減算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 減算③ X範囲内、Y範囲外
	@Test
	public void 減算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 減算④ X範囲内、Y範囲外
	@Test
	public void 減算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算⑤ X範囲外、Y範囲内
	@Test
	public void 減算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算⑥ X範囲内、Y範囲内
	@Test
	public void 減算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.subtract(-100, -100);
		assertEquals(expected, actual);
	}

	// 減算⑦ X範囲内、Y範囲内
	@Test
	public void 減算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 200.0;
		double actual = calcService.subtract(100, -100);
		assertEquals(expected, actual);
	}

	// 減算⑧ X範囲外、Y範囲内
	@Test
	public void 減算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算⑨ X範囲外、Y範囲内
	@Test
	public void 減算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算⑩ X範囲内、Y範囲内
	@Test
	public void 減算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -200.0;
		double actual = calcService.subtract(-100, 100);
		assertEquals(expected, actual);
	}

	// 減算⑪ X範囲内、Y範囲内
	@Test
	public void 減算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 0.0;
		double actual = calcService.subtract(100, 100);
		assertEquals(expected, actual);
	}

	// 減算⑫ X範囲外、Y範囲内
	@Test
	public void 減算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算⑬ X範囲外、Y範囲外
	@Test
	public void 減算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 減算⑭ X範囲内、Y範囲外
	@Test
	public void 減算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 減算⑮ X範囲内、Y範囲外
	@Test
	public void 減算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 減算⑯ X範囲外、Y範囲外
	@Test
	public void 減算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.subtract(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算
	// 乗算① X範囲外、Y範囲外
	@Test
	public void 乗算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算② X範囲内、Y範囲外
	@Test
	public void 乗算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 乗算③ X範囲内、Y範囲外
	@Test
	public void 乗算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 乗算④ X範囲内、Y範囲外
	@Test
	public void 乗算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算⑤ X範囲外、Y範囲内
	@Test
	public void 乗算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算⑥ X範囲内、Y範囲内
	@Test
	public void 乗算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 10000.0;
		double actual = calcService.multiply(-100, -100);
		assertEquals(expected, actual);
	}

	// 乗算⑦ X範囲内、Y範囲内
	@Test
	public void 乗算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -10000.0;
		double actual = calcService.multiply(100, -100);
		assertEquals(expected, actual);
	}

	// 乗算⑧ X範囲外、Y範囲内
	@Test
	public void 乗算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算⑨ X範囲外、Y範囲内
	@Test
	public void 乗算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算⑩ X範囲内、Y範囲内
	@Test
	public void 乗算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -10000.0;
		double actual = calcService.multiply(-100, 100);
		assertEquals(expected, actual);
	}

	// 乗算⑪ X範囲内、Y範囲内
	@Test
	public void 乗算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 10000.0;
		double actual = calcService.multiply(100, 100);
		assertEquals(expected, actual);
	}

	// 乗算⑫ X範囲外、Y範囲内
	@Test
	public void 乗算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算⑬ X範囲外、Y範囲外
	@Test
	public void 乗算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 乗算⑭ X範囲内、Y範囲外
	@Test
	public void 乗算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 乗算⑮ X範囲内、Y範囲外
	@Test
	public void 乗算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 乗算⑯ X範囲外、Y範囲外
	@Test
	public void 乗算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.multiply(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算
	// 除算① X範囲外、Y範囲外
	@Test
	public void 徐算1_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算② X範囲内、Y範囲外
	@Test
	public void 徐算2_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 除算③ X範囲内、Y範囲外
	@Test
	public void 徐算3_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(100, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 除算④ X範囲内、Y範囲外
	@Test
	public void 徐算4_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, -101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算⑤ X範囲外、Y範囲内
	@Test
	public void 徐算5_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算⑥ X範囲内、Y範囲内
	@Test
	public void 徐算6_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 1.0;
		double actual = calcService.divide(-100, -100);
		assertEquals(expected, actual);
	}

	// 除算⑦ X範囲内、Y範囲内
	@Test
	public void 徐算7_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -1.0;
		double actual = calcService.divide(100, -100);
		assertEquals(expected, actual);
	}

	// 除算⑧ X範囲外、Y範囲内
	@Test
	public void 徐算8_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, -100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算⑨ X範囲外、Y範囲内
	@Test
	public void 徐算9_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算⑩ X範囲内、Y範囲内
	@Test
	public void 徐算10_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = -1.0;
		double actual = calcService.divide(-100, 100);
		assertEquals(expected, actual);
	}

	// 除算⑪ X範囲内、Y範囲内
	@Test
	public void 徐算11_X範囲内_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 1.0;
		double actual = calcService.divide(100, 100);
		assertEquals(expected, actual);
	}

	// 除算⑫ X範囲外、Y範囲内
	@Test
	public void 徐算12_X範囲外_Y範囲内_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, 100);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算⑬ X範囲外、Y範囲外
	@Test
	public void 徐算13_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 除算⑭ X範囲内、Y範囲外
	@Test
	public void 徐算14_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(-100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 除算⑮ X範囲内、Y範囲外
	@Test
	public void 徐算15_X範囲内_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(100, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionY e) {
		}
	}

	// 除算⑯ X範囲外、Y範囲外
	@Test
	public void 徐算16_X範囲外_Y範囲外_Test() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(101, 101);
			assertTrue(false);
		} catch (IllegalArgumentExceptionX e) {
		}
	}

	// 0除算
	// 0除算① 例外なし
	@Test
	public void 除算_0除算_例外なし() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		double expected = 1.0;
		double actual = calcService.divide(100, 100);
		assertEquals(expected, actual);
	}

	// 0除算② 例外あり
	@Test
	public void 除算_0除算_例外あり() throws IllegalArgumentExceptionX, IllegalArgumentExceptionY {
		try {
			calcService.divide(100, 0);
			assertTrue(false);
		} catch (ArithmeticException e) {
		}
	}
}
