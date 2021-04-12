package jp.co.bhopari.tutorial_calculator.services;

import org.springframework.stereotype.Service;

/**
 *
 * @author bvs20005
 * 四則演算のサービスインタフェース
 */
/*
 *  インターフェースってなに？→「15_四則演算チュートリアル_登場人物・概念」4.インターフェースと実装クラス 参照
 *  @Service：CalcService.java がサービスであることを示すアノテーション
 *            (補足)SpringのDIコンテナにサービスとして登録される
 *                 →「DIコンテナ」がなにか分からなくて大丈夫。気になったら調べてみよう。登録しておくと、便利な機能が使えるよ！
 */
@Service
public interface CalcService {

	/*
	 * "/**"から始まる青字はドキュメントコメント
	 * メソッドの仕様を明確にするために必要なもの
	 *
	 */


	/**
	 * 加算メソッド
	 * @param x 入力値X
	 * @param y 入力値Y
	 * @return 計算結果
	 * @throws IllegalArgumentExceptionX 入力値Xが不適切な場合の例外処理
	 * @throws IllegalArgumentExceptionY 入力値Yが不適切な場合の例外処理
	 */

	// 全体の流れ解説▼1
	double add(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

	/**
	 * 減算メソッド
	 * @param x 入力値X
	 * @param y 入力値Y
	 * @return  計算結果
	 * @throws IllegalArgumentExceptionX 入力値Xが不適切な場合の例外処理
	 * @throws IllegalArgumentExceptionY 入力値Yが不適切な場合の例外処理
	 */
	double subtract(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

	/**
	 * 乗算メソッド
	 * @param x 入力値X
	 * @param y 入力値Y
	 * @return  計算結果
	 * @throws IllegalArgumentExceptionX 入力値Xが不適切な場合の例外処理
	 * @throws IllegalArgumentExceptionY 入力値Yが不適切な場合の例外処理
	 */
	double multiply(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

	/**
	 * 除算メソッド
	 * @param x 入力値X
	 * @param y 入力値Y
	 * @return  計算結果
	 * @throws IllegalArgumentExceptionX 入力値Xが不適切な場合の例外処理
	 * @throws IllegalArgumentExceptionY 入力値Yが不適切な場合の例外処理
	 */
	double divide(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

}
