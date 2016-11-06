package cs311.hw4;

import java.util.Random;

/**
 * 
 * Represents a matrix of n rows by m columns.  Note that row and column indexes 
 * start at 0.
 * 
 **/
public class SlowMatrixFactory implements IMeasureFactory
{
    /**
     * This method creates an object that implements the IMeasurable interface
     * of the specified size.  The instance of the object is initialized with
     * random data.
     * 
     * @param size is the size of the object to create.
     * 
     * @return a new object that implements the IMeasureable interface.
     */
    public IMeasurable createRandom( int size )
    {
        SlowMatrix matrix = new SlowMatrix(size, size);
        Random randomNumberGenerator = new Random();
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                matrix.setElement(i, j, randomNumberGenerator.nextInt());
            }
        }
        return matrix;
    }
}
