package cs311.hw4;

import java.util.ArrayList;
import java.util.List;

public class MeasureTimeComplexity implements IMeasureTimeComplexity
{

    /**
     *  This method calibrates the measurement by computing the number of iterations
     *  of the execute method are required to match or exceed a specified time
     *  in milliseconds.
     * 
     * @param m is the IMeasureable object to run the execute method in.
     * 
     * @param timeInMilliseconds is the number of milliseconds to calibrate to. 
     * 
     * @return the number of iterations required for the execute method to run
     * in order to match or exceed the specified time given. (timeInMilliseconds)
     */
    public int normalize(IMeasurable m, long timeInMilliseconds)
    {
        long end = System.currentTimeMillis() + timeInMilliseconds;
        int iterationCount = 0;
        while (System.currentTimeMillis() < end)
        {
            iterationCount++;
            m.execute();
        }
        return iterationCount;
    }
    
    /**
     * The measure method is used measure the execution time of a sequence of
     * sizes.
     * 
     * @param factory is a IMeasureFactory implementation for creating instances of
     * random data of specified sizes.
     * 
     * @param nmeasures is the number of iterations to perform of the measurement function
     * (Usually the result returned from the normalize method.)
     * 
     * @param startsize is the initial size of the data to measure
     * @param endsize is the final size of the data to measure
     * @param stepsize is how much to change the size for each new data size.
     * 
     * @return an array list of IResult types that contains the size and time in
     * milliseconds the measurement took.
     */
    public List<? extends IResult> measure(IMeasureFactory factory, int nmeasures, int startsize, int endsize, int stepsize)
    {
        List<IResult> results = new ArrayList<>();
        for (int size = startsize; size <= endsize; size += stepsize)
        {
            IMeasurable measurable = factory.createRandom(size);
            long start = System.currentTimeMillis();
            for (int i = 0; i < nmeasures; i++)
            {
                measurable.execute();
            }
            long time = System.currentTimeMillis() - start;
            results.add(new MeasureResult(size, time));
        }
        return results;
    }
}