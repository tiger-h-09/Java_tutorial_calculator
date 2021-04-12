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
@SpringBootTest
public class CalcServiceControllerTest {

	private MockMvc mockMvc;

	final static String CALC = "CalcService";

	final String NAME_SERVLET = "/calc";

	@Autowired
	CalcServiceController calcServiceController;

	@MockBean
	CalcService calcService;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(calcServiceController).build();
	}

	@Test
	public void 初期画面_Test() throws Exception {

		mockMvc.perform(get(NAME_SERVLET))

		.andExpect(status().isOk())

		.andExpect(view().name(CALC))

		.andExpect(model().attribute("operator", Operator.ADD));
	}

	@Test
	public void inputX_null_Test() throws Exception {

		when(calcService.add(anyInt(), anyInt())).thenReturn(0.0);

		mockMvc.perform(get(NAME_SERVLET)
				.param("calculate", "計算")
				.param("inputX", (String) null)
				.param("inputY", "100")
				.param("operator", "ADD"))

		.andExpect(status().isOk())
		.andExpect(view().name(CALC))
		.andExpect(model().attribute("errorMessage", "エラー：左側のボックスに値を入力してください"));

		verify(calcService, times(0)).add(anyInt(), anyInt());
	}

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

	@Test
	public void inputX入力値範囲以上_Test() throws Exception {
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
