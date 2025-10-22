package com.br.DbTec.CalcularRendimento;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * CalcularRendimentoTest
 */
@DisplayName("Teste do calculo de rendimento da poupança")
public class CalcularRendimentoTest {

  @DisplayName("Teste para o valor da selic acima de 8,5%. Deve usar o valor 0.5 para o calculo")
  @Test
  public void CalcularRendimentoComSelicMaiorQue85() {

    final double entrada = 1000.00;
    int qtdMesses = 10;
    final float selic = 8.6f; // maior que 8.5 valor utilizaro sera 0.5
    final float taxa = 0.12f;

    final double valorEsperado = 2063.70;
    double valorCalculado = CalcularRendimento.CalculateIncomeAmmout(entrada, qtdMesses, selic, taxa);

    assertEquals(valorCalculado, valorEsperado, 0.1);

  }

  @DisplayName("Teste para o valor da selic abaixo de 8,5%. Deve usar o valor 0.7 para o calculo")
  @Test
  public void CalcularRendimentoComSelicMenorQue85() {

    final double entrada = 1000.00;
    int qtdMesses = 10;
    final float selic = 8.4f; // maior que 8.5 valor utilizaro sera 0.7
    final float taxa = 0.12f;

    final double valorEsperado = 2085.00;
    double valorCalculado = CalcularRendimento.CalculateIncomeAmmout(entrada, qtdMesses, selic, taxa);

    assertEquals(valorCalculado, valorEsperado, 0.1);

  }

  @DisplayName("Teste para o valor da selic igual de 8,5%. Deve usar o valor 0.7 para o calculo")
  @Test
  public void CalcularRendimentoComSelicIgualA85() {

    final double entrada = 1000.00;
    int qtdMesses = 10;
    final float selic = 8.5f; // maior que 8.5 valor utilizaro sera 0.7
    final float taxa = 0.12f;

    final double valorEsperado = 2085.01;
    double valorCalculado = CalcularRendimento.CalculateIncomeAmmout(entrada, qtdMesses, selic, taxa);

    assertEquals(valorCalculado, valorEsperado, 0.1);

  }
}
