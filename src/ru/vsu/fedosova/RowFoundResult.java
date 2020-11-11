package ru.vsu.fedosova;

public class RowFoundResult
{
    int iteration;
    double sum;

    public RowFoundResult(int iteration, double sum)
    {
        this.iteration = iteration;
        this.sum = sum;
    }

    public int getIteration()
    {
        return iteration;
    }

    public double getSum()
    {
        return sum;
    }
}
