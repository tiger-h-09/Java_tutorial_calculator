package jp.co.bhopari.tutorial_calculator.services;

import org.springframework.stereotype.Service;

@Service
public interface CalcService {
	double add(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

	double subtract(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

	double multiply(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

	double divide(int x, int y) throws IllegalArgumentExceptionX, IllegalArgumentExceptionY;

}
