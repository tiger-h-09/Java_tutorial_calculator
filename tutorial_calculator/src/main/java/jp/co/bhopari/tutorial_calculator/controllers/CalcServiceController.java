package jp.co.bhopari.tutorial_calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.bhopari.tutorial_calculator.services.CalcService;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionX;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionY;

/*
 * @Controller：CalcServiceController.java がコントローラであることを示すアノテーション
 * 	            (補足)SpringのDIコンテナにサービスとして登録される
 *                  →「DIコンテナ」がなにか分からなくて大丈夫。気になったら調べてみよう。登録しておくと、便利な機能が使えるよ！
 */
@Controller
public class CalcServiceController {

	/*
	 * @Autowired：
	 *   @Component(@Controller・@Service・@Repository)が付いているクラスはSpringのDIコンテナに登録される
	 *   @Autowiredはその中から宣言した型（今回だと、CalcService）に合うクラスを探し、
	 *   そのクラスのオブジェクトをSpringが自動的に生成し、変数にセットする
	 *
	 *   ★つまり、オブジェクトを生成する処理を書かずに（newって書かずに）、クラスを呼び出すことができる★
	 *
	 *   今回の場合だと、@Serviceがついている CalcService.java のオブジェクトを変数 calcService にセットしている
	 */
	@Autowired
	CalcService calcService;

	/*
	 *  CalcService.html を定数化
	 *  繰り返し書くと修正が入ったときに修正漏れの可能性が高くなるから定数化する
	 *    →※(補足)DRY原則
	 *
	 *  <定数化してない場合>
	 *  定数化せずに「 return "CalcService"; 」と書いてある場合
	 *  →使用している HTML ファイルのファイル名が変更されたり、違う HTML ファイルを使用することになったら、
	 *  「"CalcService"」を全て(複数個所)変更しなければいけない
	 *  そのため、修正漏れの可能性が高い
	 *
	 *  <定数化してある場合>
	 *  定数化してある場合は、「 final String CALC = "CalcService"; 」の「 "CalcService" 」のみ(1箇所)変更すればよい
	 *  「 return CALC; 」の「 CALC 」は定数なので変更する必要がない
	 *  そのため、修正漏れの可能性が低い
	 *
	 *
	 *  ※ DRY原則：Don't Repeat Yourselfの略で、「繰り返しを避けること」という意味
	 *              「すべての知識はシステム内において、単一、かつ明確な、そして信頼できる表現になっていなければならない。」
	 *              （『達人プログラマ』/David Thomas,Andrew Hunt(著)より）
	 *              プログラミングにおいて、同じような情報をいろんな場所に散らばっていると管理が難しく、修正漏れの可能性がある。
	 *              ★つまり、「不必要な情報の重複をなくそう」ということ★
	 *
	 */
	final static String CALC = "CalcService";

	// パスを定数化
	final String NAME_SERVLET = "/calc";


	/*
	 * @GetMapping：@RequestMapping(method = RequestMethod.GET) と同義
	 *               (path = URL)で指定したURL（/calc）とメソッド（calc）が関係あることを示す
	 *               ⇒/calc がリクエストされたときに、calcメソッドが実行される
	 *
	 * Model model：Controller からビューに受け渡すオブジェクトを保持する
	 *
	 * model.addAttribute()：インターフェース Model の addAttribute(String attributeName, Object attributeValue)メソッド
	 *                     String型のキー（"operator"）に画面に受け渡すオブジェクト（Operator.ADD）を追加する
	 *                     ★つまり、 Model に格納される★
	 */

	/**
	 * 初期画面
	 * @param model 演算子(operator)を格納するモデル
	 * @return 初期画面
	 */

	// 全体の流れ解説(以降▼)1
	@GetMapping(path = NAME_SERVLET)
	public String calc(Model model) {
		model.addAttribute("operator", Operator.ADD);
		return CALC;
	}


	/*
	 * @GetMapping(params = "calculate")：params 属性と html ファイルの name属性が一致している場合にメソッドが実行される
	 *
	 * htmlファイルから name = "calculate" が送信されてきたとき、
	 * ★つまり、計算ボタンが押下されたときに params = "calculate" というリクエストパラメータがあるこのメソッドが実行される★
	 *
	 * （引数についている）@ModelAttribute：
	 * ①Model の中から対応するオブジェクトを探し、
	 * ②引数として受け取る
	 *
	 * 例）@ModelAttribute("inputX") String inputX, の場合
	 * ①Model の中から "inputX" に対応するオブジェクトを探し、
	 * ②引数として受け取る（Model に格納された "inputX" に対応するオブジェクトを String inputX に代入する）
	 *
	 */

	/**
	 * 計算ボタン実行後
	 * @param model inputX、inputY、operator、errorMessage、result を格納するモデル
	 * @param inputX 数値入力ボックスXに入れられた整数
	 * @param inputY 数値入力ボックスYに入れられた整数
	 * @param operator 演算方法選択ボックスで、プルダウンによって選択された演算子
	 * @return 計算結果表示画面
	 */

	// ▼2
	@GetMapping(path = NAME_SERVLET, params = "calculate")
	public String calculate(Model model,
			// ▼3
			@ModelAttribute("inputX") String inputX,
			// ▼4
			@ModelAttribute("inputY") String inputY,
			// ▼5
			@ModelAttribute("operator") Operator operator) {

		// チェック処理を行う

		// ▼6 入力値X入力チェック

		if (inputX == null || inputX.equals("")) {
			model.addAttribute("errorMessage", "エラー：左側のボックスに値を入力してください");
			return CALC;
		}

		// ▼7 入力値Y入力チェック

		if (inputY == null || inputY.equals("")) {
			model.addAttribute("errorMessage", "エラー：右側のボックスに値を入力してください");
			return CALC;
		}

		// ▼8 入力値X整数値チェック

		int numX = 0;
		int numY = 0;

		try {
			numX = Integer.parseInt(inputX);
		} catch (NumberFormatException e) {
			model.addAttribute("errorMessage", "エラー：左側のボックスに整数を入力してください");
			return CALC;
		}

		// ▼9 入力値Y整数値チェック

		try {
			numY = Integer.parseInt(inputY);
		} catch (NumberFormatException e) {
			model.addAttribute("errorMessage", "エラー：右側のボックスに整数を入力してください");
			return CALC;
		}

		double result = 0;

		try {
			// ▼10
			switch (operator) {

			// ▼11 演算子が「＋」の場合、計算サービスの「加算」を呼び出す
			case ADD:
				// ▼13   ▼12
				result = calcService.add(numX, numY);
				break;
				// 演算子が「－」の場合、計算サービスの「減算」を呼び出す
			case SUBTRACT:
				result = calcService.subtract(numX, numY);
				break;
				// 演算子が「×」の場合、計算サービスの「乗算」を呼び出す
			case MULTIPLY:
				result = calcService.multiply(numX, numY);
				break;
				// 演算子が「÷」の場合、計算サービスの「徐算」を呼び出す
			case DIVIDE:
				result = calcService.divide(numX, numY);
				break;
			default:

			}

			// ▼14 小数点第三位を四捨五入
			double resultR = ((double) Math.round(result * 100)) / 100;

			// ▼15 計算結果を文字列に変換
			String resultS = String.valueOf(resultR);

			// ▼16 計算結果を Model に格納する
			model.addAttribute("result", resultS);

			// ▼17 入力値Xが-100以上 100以下でない時、エラーとする。
		} catch (IllegalArgumentExceptionX e) {
			model.addAttribute("errorMessage", "エラー：左側のボックスに-100から 100までの値を入力してください");

			// 入力値Yが-100以上 100以下でない時、エラーとする。
		} catch (IllegalArgumentExceptionY e) {
			model.addAttribute("errorMessage", "エラー：右側のボックスに-100から 100までの値を入力してください");

			// 入力値Yが0である時、エラーとする。
		} catch (ArithmeticException e) {
			model.addAttribute("errorMessage", "エラー：0では除算できません");
		}

		// ▼18
		return CALC;
	}

}
