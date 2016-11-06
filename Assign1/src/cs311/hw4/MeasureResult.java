package cs311.hw4;

/**
 *  An object encapsulating a measurement result
 * that is a size and time (in milliseconds) pair.
 */
public class MeasureResult implements IResult
{
    private final int size;
    private final long time;

    public MeasureResult(int size, long time)
    {
        this.size = size;
        this.time = time;
    }

    /**
     * @return the size associated with the measurement
     */
    public int getSize()
    {
        return size;
    }

    /**
     * @return the time associated with the measurement
     */
    public long getTime()
    {
        return time;
    }
}
