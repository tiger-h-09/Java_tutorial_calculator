package jp.co.bhopari.tutorial_calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jp.co.bhopari.tutorial_calculator.services.CalcService;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionX;
import jp.co.bhopari.tutorial_calculator.services.IllegalArgumentExceptionY;

@Controller
public class CalcServiceController {

	@Autowired
	CalcService calcService;

	final static String CALC = "CalcService";

	final String NAME_SERVLET = "/calc";

	@GetMapping(path = NAME_SERVLET)
	public String calc(Model model) {
		model.addAttribute("operator", Operator.ADD);
		return CALC;
	}

	@GetMapping(path = NAME_SERVLET, params = "calculate")
	public String calculate(Model model,
			@ModelAttribute("inputX") String inputX,
			@ModelAttribute("inputY") String inputY,
			@ModelAttribute("operator") Operator operator) {

		if (inputX == null || inputX.equals("")) {
			model.addAttribute("errorMessage", "エラー：左側のボックスに値を入力してください");
			return CALC;
		}

		if (inputY == null || inputY.equals("")) {
			model.addAttribute("errorMessage", "エラー：右側のボックスに値を入力してください");
			return CALC;
		}

		int numX = 0;
		int numY = 0;

		try {

			numX = Integer.parseInt(inputX);
		} catch (NumberFormatException e) {
			model.addAttribute("errorMessage", "エラー：左側のボックスに整数を入力してください");
			return CALC;
		}

		try {

			numY = Integer.parseInt(inputY);
		} catch (NumberFormatException e) {
			model.addAttribute("errorMessage", "エラー：右側のボックスに整数を入力してください");
			return CALC;
		}

		double result = 0;

		try {
			switch (operator) {

			case ADD:
				result = calcService.add(numX, numY);
				break;

			case SUBTRACT:
				result = calcService.subtract(numX, numY);
				break;

			case MULTIPLY:
				result = calcService.multiply(numX, numY);
				break;

			case DIVIDE:
				result = calcService.divide(numX, numY);
				break;
			default:

			}

			double resultRound = ((double) Math.round(result * 100)) / 100;

			String resultString = String.valueOf(resultRound);

			model.addAttribute("result", resultString);

		} catch (IllegalArgumentExceptionX e) {
			model.addAttribute("errorMessage", "エラー：左側のボックスに-100から 100までの値を入力してください");

		} catch (IllegalArgumentExceptionY e) {
			model.addAttribute("errorMessage", "エラー：右側のボックスに-100から 100までの値を入力してください");

		} catch (ArithmeticException e) {
			model.addAttribute("errorMessage", "エラー：0では除算できません");
		}

		return CALC;
	}


}
