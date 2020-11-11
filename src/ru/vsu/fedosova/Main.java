package ru.vsu.fedosova;

import java.util.Scanner;
import java.util.Locale;

public class Main
{

    public static void main(String[] args)
    {
        Locale.setDefault(Locale.ROOT);

        double x = readDouble("x (-1 < x < 1)");
        int n = readInt("n");
        double e = readDouble("e");

        if (!checkValueX(x))
        {
            System.out.println("The value x does not belong to the interval (-1;1). Try again");
            return;
        }

        RowFoundResult SumGreaterThanEpsilon = findSumGreaterThanEpsilon(x, n, e);
        RowFoundResult SumGreaterThanEpsilonDividedByTen = findSumGreaterThanEpsilon(x, n, e / 10);

        printResult("sum", findSumSeries(x, n));
        printEpsilonRelatedResult(": ", SumGreaterThanEpsilon);
        printEpsilonRelatedResult("divided by ten: ", SumGreaterThanEpsilonDividedByTen);
        printResult("function value", findFunctionValue(x));
    }

    public static double readDouble(String name)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Input %s: ", name);
        return scanner.nextDouble();
    }

    public static int readInt(String name)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Input %s: ", name);
        return scanner.nextInt();
    }

    public static boolean checkValueX(double x)
    {
        return Math.abs(x) < 1;
    }

    public static double findSumSeries(double x, int n)
    {
        double nMember = 1;
        double sum = 1;

        for (int i = 1; i < n; i++)
        {
            nMember *= - x * (2 * i - 1) / (2 * i);

            sum += nMember;
        }
        return sum;
    }

    public static RowFoundResult findSumGreaterThanEpsilon(double x, int n, double e)
    {
        double nMember = 1;
        double sumGreaterThenEpsilon = 1;
        int iteration = 0;

        for (int i = 1; i < n; i++)
        {
            if (Math.abs(nMember) > e) {
                nMember *= -x * (2 * i - 1) / (2 * i);
                sumGreaterThenEpsilon += nMember;
                iteration++;
            }
        }
        return new RowFoundResult(iteration, sumGreaterThenEpsilon);
    }

    public static double findFunctionValue(double x)
    {
        return Math.pow((1 + x), - 1 / 2);
    }

    public static void printResult(String name, double result)
    {
        System.out.printf("The %s: %1f\n", name, result);
    }

    public static void printEpsilonRelatedResult(String phrase, RowFoundResult rowFoundResult)
    {
        System.out.println("The sum greater than epsilon" + phrase + rowFoundResult.getSum() +
                ". Count of iteration:" + rowFoundResult.getIteration());
    }
}
