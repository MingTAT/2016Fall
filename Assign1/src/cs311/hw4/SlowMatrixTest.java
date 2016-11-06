package cs311.hw4;

import java.util.List;

/**
 *  Investigation of runtime complexity using Matrix.
 */
public class SlowMatrixTest
{
    public static void main(String[] args)
    {
        IMeasureTimeComplexity measureTimeComplexity = new MeasureTimeComplexity();
        IMeasureFactory measureFactory = new SlowMatrixFactory();
        int startSize = 2;
        long cpuTime = 2L; // 2 millisecond
        int normalizedIterationMeasure = measureTimeComplexity.normalize(
            measureFactory.createRandom(startSize), cpuTime);
        System.out.println(normalizedIterationMeasure);

        int endSize = 100;
        int stepSize = 1;
        List<? extends IResult> results = measureTimeComplexity.measure(
            measureFactory,
            normalizedIterationMeasure,
            startSize,
            endSize,
            stepSize);
        for (IResult result : results)
        {
            System.out.println(String.format(
                "%d %d",
                result.getSize(),
                result.getTime()));
        }
    }
}
