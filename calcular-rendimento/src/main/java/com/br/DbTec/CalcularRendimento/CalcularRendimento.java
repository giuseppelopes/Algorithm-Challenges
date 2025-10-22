package com.br.DbTec.CalcularRendimento;

import java.util.ArrayList;
import java.util.List;

public class CalcularRendimento {

  public static void main(String[] args) {
    System.out.println("Calculo do Rendimento " + CalculateIncomeAmmout(1000, 10, (float) 9.5, (float) 0.3));

  }

  /**
   * Calculates the final income amount based on compound interest.
   * Assumes a monthly tax rate based on SELIC > 8.5% or <= 8.5% plus a
   * Referential Tax (TR).
   *
   * @param ammout         The initial investment principal.
   * @param numberOfMonths The number of months.
   * @param selic          The annual SELIC rate (as a percentage, e.g., 9.5).
   * @param referencialTax The monthly Referential Tax (as a percentage, e.g.,
   *                       0.1).
   * @return The final total amount (principal + interest).
   */
  public static Double CalculateIncomeAmmout(double ammout, int numberOfMonths, float selic, float referencialTax) {

    final float tr = referencialTax / 100;
    List<Double> results = new ArrayList<>();

    double montlyTax;

    if (selic <= 8.5) {
      montlyTax = 0.007 + tr;
    } else {
      montlyTax = 0.005 + tr;
    }

    Double finalAmount = ammout * Math.pow(1.0 + montlyTax, numberOfMonths);
    Double finalResult = finalAmount + ammout;

    int aux = 1;
    while (aux <= numberOfMonths) {
      results.add((ammout * Math.pow(1.0 + montlyTax, aux)) + ammout);
      aux++;
    }
    repport(ammout, finalResult, results);

    return finalResult;
  }

  public static void repport(Double initialValue, Double FinalValue, List<Double> results) {

    System.out.println("Valor inicial [" + initialValue + "]" + "\nValor Final [" + FinalValue + "]"
        + "\nTabela mes a mes");

    results.forEach(result -> {
      System.out.println("[" + result + "]");
    });

  }

}
