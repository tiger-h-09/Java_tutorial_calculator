package jp.co.bhopari.tutorial_calculator.controllers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.co.bhopari.tutorial_calculator.services.CalcService;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionX;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionY;
/*
 * 【テストについて】
 *  設計書をもとに、齟齬がないかどうか網羅的にテストできることが大事
 *
 *  コントローラが正常に動くかどうかではなく、「設計書通り」にコントローラが機能するかどうかのテストを行う
 *
 *  テストはテスト項目などを作成して網羅的にテストを行う
 */


@SpringBootTest
public class CalcServiceControllerTest {

	/* MockMvc:
	 * mock（ハリボテ）を使って SpringMVCを再現できるクラス。
	 * テストのための環境を用意してくれるイメージ。
	 *
	 * mock って？
	 * コントローラの仕事の一つである、「メソッドを呼び出せているかどうか」のテストをするために使う。”ハリボテ”と呼ばれることもある。
	 *「○○メソッドを呼び出す」というコントローラの処理をテストしてエラーが出た時に、それが○○メソッドの中身の問題なのかコントローラの問題なのかを切り分ける必要がある。
	 * ここではコントローラのテストをしたいので、○○メソッドの中身がどうかは無視したい。
	 * そこで使うのが mock 。 mock は○○メソッドを持った△△クラスの形を模したハリボテで、その mock を△△クラスのオブジェクトとして呼び出し、 mock の○○メソッドを呼び出すことでコントローラ自体のテストを可能にしている。
	 */
	private MockMvc mockMvc;

	/*
	 *  CalcService.html を定数化
	 *  →定数化については「 CalcServiceController.java 」に追加説明があります
	 */
	final static String CALC = "CalcService";

	// パスを定数化
	final String NAME_SERVLET = "/calc";

	/*
	 *  CalcServiceController.java のオブジェクトを変数 calcServiceController にセット
	 *  →@Autowired については「 CalcServiceController.java 」に追加説明があります
	 */
	@Autowired
	CalcServiceController calcServiceController;

	/*
	 * @MockBean：@Autowiredの機能を通じて、宣言した型（CalcService）に合うクラスのmock（ハリボテ）オブジェクトを変数にセットする
	 *
	 * ※mockとは：クラスの動作を意図的に設定し、擬似的シミュレートするためのオブジェクト
	 *
	 * CalcService型のmock（ハリボテ）オブジェクトを変数 calcService にセット
	 *
	 * コントローラテストで使用されるサービスクラスはmock（ハリボテ）なので、サービスの機能はテストによって設定できる
	 *
	 * ★つまり、サービスクラスをmockにすることで、コントローラクラスは実際のサービスクラスを呼び出していないが、
	 *   サービスクラスからの意図したデータを取得できる
	 *
	 */
	@MockBean
	CalcService calcService;

	/*
	 * @BeforeEach：現在のクラス内にある各テスト(@Test)が実行される前(before)に実行されるメソッドを意味する
	 *
	 * MockMvcBuilders.standaloneSetup()：テスト対象のController(calcServiceController)を指定して、MockMvcを生成する
	 *                                    springMVCの動作を再現するための準備
	 *
	 * .build()："これで終わりだよ"の合図、お作法
	 *
	 * ★つまり、Spring MVC の機能を使用したコントローラの単体テストができる
	 *
	 */
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(calcServiceController).build();
	}

	/*
	 * テストは3段階ある
	 * 1.前準備：テストを実行するために必要なことの準備
	 * 2.実行：テストを行う
	 * 3.確認：テストが正常に行われたかどうかの確認
	 *
	 *
	 * ＜今回のコントローラテストの場合＞
	 * 1.前準備：コントローラが呼び出すクラス（サービスクラス）のmock作成（サービスクラスのメソッドを呼び出さない場合は省略される）
	 * 2.実行：処理を行うパスの設定・入力値の設定
	 * 3.確認：①レスポンスのHTTPステータスコードは正しいか
	 *         ②指定の画面(HTMLファイル)を返すか
	 *         ③modelに正しい変数を詰められているか
	 *         ④サービスクラスのメソッドが正しく呼び出されているか（もしくは呼び出されていないか）
	 */

	// ① 初期画面表示
	@Test
	public void 初期画面_Test() throws Exception {

		// 1.準備項目がないので、前準備は省略

		/*
		 * 2.実行
		 * perform()：テストで利用するリクエストデータを設定
		 * get(URL)：URL（/calc）にGETメソッドでリクエストを発行している
		 */
		mockMvc.perform(get(NAME_SERVLET))

		// 3.確認
		// レスポンスの HTTP ステータスコード（成功レスポンス 200）を確認する
		.andExpect(status().isOk())

		// /calc で CalcService.html を返すかの確認する
		.andExpect(view().name(CALC))

		// modelに正しい変数を詰められているか確認する
		.andExpect(model().attribute("operator", Operator.ADD));
	}

	// ② inputX == null
	@Test
	public void inputX_null_Test() throws Exception {
		/*
		 *  1.前準備(mockの設定)
		 *  サービス（calcService）の add() メソッドはどんな整数（anyInt()）が入力されても、0.0 をコントローラに返す
		 */
		when(calcService.add(anyInt(), anyInt())).thenReturn(0.0);

		/*
		 * 2.実行(入力)
		 * perform()：テストで利用するリクエストデータを設定
		 * get(URL)：URL（/calc）にGETメソッドでリクエストを発行している
		 * param(String name, String values)：テスト実行時のリクエストに、リクエストパラメータを追加する
		 *
		 * ★つまり、model には
		 * .param("calculate", "計算")→ "calculate" に  "計算" が代入されている（「計算ボタン」を実行した）
		 * .param("inputX", (String) null)→ "inputX" に (String) null が代入されている（なにもない）
		 * .param("inputY", "100")→ "inputY" に "100" が代入されている（画面の「数値入力ボックスY」に「100」を入力した）
		 * .param("operator", "ADD")→ "operator" に "ADD" が代入されている（画面の「演算方法選択ボックス」で「＋」を選択した）
		 * 以上の条件（入力値）のテストを実行する
		 */
		mockMvc.perform(get(NAME_SERVLET)

				// 入力値の設定
				.param("calculate", "計算")
				.param("inputX", (String) null)
				.param("inputY", "100")
				.param("operator", "ADD"))

		// 3.確認(出力)
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：左側のボックスに値を入力してください"));

		/*
		 *  verify(mockオブジェクト名, 使用回数).メソッド名(引数);
		 *
		 *  calcService の add() メソッドが0回呼び出されていること（1回も呼び出されていないこと）
		 *
		 *  ★つまり、呼び出すクラスの該当のメソッドが正常に呼び出されているか（呼び出されていないか）どうかの確認
		 */
		verify(calcService, times(0)).add(anyInt(), anyInt());
	}

	// ③ inputX == 空値
	@Test
	public void inputX空値_Test() throws Exception {
		when(calcService.add(anyInt(), anyInt())).thenReturn(0.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "")
				.param("inputY", "100")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：左側のボックスに値を入力してください"));

		verify(calcService, times(0)).add(anyInt(), anyInt());
	}

	// ④ inputY == null
	@Test
	public void inputY_null_Test() throws Exception {
		when(calcService.add(anyInt(), anyInt())).thenReturn(0.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", (String) null)
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：右側のボックスに値を入力してください"));

		verify(calcService, times(0)).add(anyInt(), anyInt());
	}

	// ⑤ inputY == 空値
	@Test
	public void inputY空値_Test() throws Exception {
		when(calcService.add(anyInt(), anyInt())).thenReturn(0.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：右側のボックスに値を入力してください"));

		verify(calcService, times(0)).add(anyInt(), anyInt());
	}

	// ⑥ inputX整数
	@Test
	public void inputX整数_Test() throws Exception {
		when(calcService.add(anyInt(), anyInt())).thenReturn(0.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "12.3")
				.param("inputY", "100")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：左側のボックスに整数を入力してください"));

		verify(calcService, times(0)).add(anyInt(), anyInt());
	}

	// ⑦ inputY整数
	@Test
	public void inputY整数_Test() throws Exception {
		when(calcService.add(anyInt(), anyInt())).thenReturn(0.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "12.3")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：右側のボックスに整数を入力してください"));

		verify(calcService, times(0)).add(anyInt(), anyInt());
	}

	// ⑧ 加算サービス
	@Test
	public void 加算呼び出し_Test() throws Exception {
		when(calcService.add(100, 100)).thenReturn(200.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "100")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("result", "200.0"));

		verify(calcService, times(1)).add(100, 100);
	}

	// ⑨ 減算サービス
	@Test
	public void 減算呼び出し_Test() throws Exception {
		when(calcService.subtract(100, 100)).thenReturn(0.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "100")
				.param("operator", "SUBTRACT"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("result", "0.0"));

		verify(calcService, times(1)).subtract(100, 100);
	}

	// ⑩ 乗算サービス
	@Test
	public void 乗算呼び出し_Test() throws Exception {
		when(calcService.multiply(100, 100)).thenReturn(10000.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "100")
				.param("operator", "MULTIPLY"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("result", "10000.0"));

		verify(calcService, times(1)).multiply(100, 100);
	}

	// ⑪ 除算サービス
	@Test
	public void 徐算呼び出し_Test() throws Exception {
		when(calcService.divide(100, 100)).thenReturn(1.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "100")
				.param("operator", "DIVIDE"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("result", "1.0"));

		verify(calcService, times(1)).divide(100, 100);

	}

	// ⑫ inputX > 100
	@Test
	public void inputX入力値範囲以上_Test() throws Exception {
		/*
		 * 1.前準備
		 * 例外（IllegalArgumentExceptionX）は戻り値（return）ではなく、スローされる
		 * when().thenThrow(ExceptionClass) を使用して例外を発生させ、スローする
		 *
		 * ★つまり今回の場合、サービスに x = 101, y = 100 を入力すると例外（IllegalArgumentExceptionX()）がスローされるようなmockを設定している
		 */
		when(calcService.add(101, 100)).thenThrow(new IllegalArgumentExceptionX());

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "101")
				.param("inputY", "100")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：左側のボックスに-100から 100までの値を入力してください"));

		verify(calcService, times(1)).add(101, 100);
	}

	// ⑬ inputX < -100
	@Test
	public void inputX入力値範囲以下_Test() throws Exception {
		when(calcService.add(-101, 100)).thenThrow(new IllegalArgumentExceptionX());

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "-101")
				.param("inputY", "100")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：左側のボックスに-100から 100までの値を入力してください"));

		verify(calcService, times(1)).add(-101, 100);
	}

	// ⑭ inputY > 100
	@Test
	public void inputY入力値範囲以上_Test() throws Exception {
		when(calcService.add(100, 101)).thenThrow(new IllegalArgumentExceptionY());

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "101")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：右側のボックスに-100から 100までの値を入力してください"));

		verify(calcService, times(1)).add(100, 101);
	}

	// ⑮ inputY < -100
	@Test
	public void inputY入力値範囲以下_Test() throws Exception {
		when(calcService.add(100, -101)).thenThrow(new IllegalArgumentExceptionY());

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "-101")
				.param("operator", "ADD"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：右側のボックスに-100から 100までの値を入力してください"));

		verify(calcService, times(1)).add(100, -101);
	}

	// ⑯ 0除算
	@Test
	public void inputY0除算_Test() throws Exception {
		when(calcService.divide(100, 0)).thenThrow(new ArithmeticException());

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", "100")
				.param("inputY", "0")
				.param("operator", "DIVIDE"))
		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：0では除算できません"));

		verify(calcService, times(1)).divide(100, 0);
	}

}
