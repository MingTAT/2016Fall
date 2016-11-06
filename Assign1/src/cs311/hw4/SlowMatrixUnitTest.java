package cs311.hw4;

/**
 *  Unit test of SlowMatrix
 */
public class SlowMatrixUnitTest
{
    private static final IMeasureFactory MEASURE_FACTORY = new SlowMatrixFactory();

    public static void testSubMatrix_squareMatrix()
    {
        System.out.println("\n======================================\n");
        System.out.println("testSubMatrix_squareMatrix\n");
        SlowMatrix matrix = (SlowMatrix) MEASURE_FACTORY.createRandom(10);
        SlowMatrix subMatrix = (SlowMatrix) matrix.subMatrix(1, 2, 7, 8);
        System.out.println("matrix =\n" + matrix);
        System.out.println("subMatrix(1, 2, 7, 8) =\n" + subMatrix);
    }

    public static void testSubMatrix_nonSquareMatrix()
    {
        System.out.println("\n======================================\n");
        System.out.println("testSubMatrix_nonSquareMatrix\n");
        SlowMatrix matrix = new SlowMatrix(5, 8);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                matrix.setElement(i, j, 0);
            }
        }
        matrix.setElement(0, 0, -1);
        matrix.setElement(0, 3, 2);
        matrix.setElement(0, 6, -3);
        matrix.setElement(1, 1, 4);
        matrix.setElement(1, 3, -5);
        matrix.setElement(1, 7, 6);
        matrix.setElement(2, 2, -7);
        matrix.setElement(2, 4, 8);
        matrix.setElement(2, 5, -9);
        matrix.setElement(3, 0, -7);
        matrix.setElement(3, 4, 8);
        matrix.setElement(3, 7, -9);
        matrix.setElement(4, 3, -7);
        matrix.setElement(4, 5, 8);
        matrix.setElement(4, 6, -9);
        SlowMatrix subMatrix = (SlowMatrix) matrix.subMatrix(1, 2, 3, 6);
        System.out.println("matrix =\n" + matrix);
        System.out.println("subMatrix(1, 2, 3, 6) =\n" + subMatrix);
    }

    public static void testSubMatrix_indexOutOfBound_upperLeftRow()
    {
        System.out.println("\n======================================\n");
        System.out.println("testSubMatrix_indexOutOfBound_upperLeftRow\n");
        SlowMatrix matrix = new SlowMatrix(5, 8);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                matrix.setElement(i, j, 0);
            }
        }
        try
        {
            matrix.subMatrix(5, 2, 3, 6);    
        } catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    public static void testSubMatrix_indexOutOfBound_upperLeftCol()
    {
        System.out.println("\n======================================\n");
        System.out.println("testSubMatrix_indexOutOfBound_upperLeftCol\n");
        SlowMatrix matrix = new SlowMatrix(5, 8);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                matrix.setElement(i, j, 0);
            }
        }
        try
        {
            matrix.subMatrix(1, 9, 3, 6);    
        } catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    public static void testSubMatrix_indexOutOfBound_lowerRightRow()
    {
        System.out.println("\n======================================\n");
        System.out.println("testSubMatrix_indexOutOfBound_lowerRightRow\n");
        SlowMatrix matrix = new SlowMatrix(5, 8);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                matrix.setElement(i, j, 0);
            }
        }
        try
        {
            matrix.subMatrix(1, 2, 8, 6);    
        } catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    public static void testSubMatrix_indexOutOfBound_lowerRightCol()
    {
        System.out.println("\n======================================\n");
        System.out.println("testSubMatrix_indexOutOfBound_lowerRightCol\n");
        SlowMatrix matrix = new SlowMatrix(5, 8);
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                matrix.setElement(i, j, 0);
            }
        }
        try
        {
            matrix.subMatrix(1, 2, 3, 10);    
        } catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    public static void testMatrixAddition_squareMatrix()
    {
        System.out.println("\n======================================\n");
        System.out.println("testMatrixAddition_squareMatrix\n");
        SlowMatrix matrix1 = new SlowMatrix(3, 3);
        matrix1.setElement(0, 0, -1);
        matrix1.setElement(0, 1, 2);
        matrix1.setElement(0, 2, -3);
        matrix1.setElement(1, 0, 4);
        matrix1.setElement(1, 1, -5);
        matrix1.setElement(1, 2, 6);
        matrix1.setElement(2, 0, -7);
        matrix1.setElement(2, 1, 8);
        matrix1.setElement(2, 2, -9);
        SlowMatrix matrix2 = new SlowMatrix(3, 3);
        matrix2.setElement(0, 0, -9);
        matrix2.setElement(0, 1, 8);
        matrix2.setElement(0, 2, -7);
        matrix2.setElement(1, 0, 6);
        matrix2.setElement(1, 1, -5);
        matrix2.setElement(1, 2, 4);
        matrix2.setElement(2, 0, -3);
        matrix2.setElement(2, 1, 2);
        matrix2.setElement(2, 2, -1);
        System.out.println("matrix1 =\n" + matrix1);
        System.out.println("matrix2 =\n" + matrix2);
        System.out.println("matrix1 + matrix2 =\n" + matrix1.add(matrix2));
        System.out.println("matrix2 + matrix1 =\n" + matrix2.add(matrix1));
    }

    public static void testMatrixAddition_dimensionMismatch()
    {
        System.out.println("\n======================================\n");
        System.out.println("testMatrixAddition_dimensionMismatch\n");
        SlowMatrix matrix1 = new SlowMatrix(3, 3);
        SlowMatrix matrix2 = new SlowMatrix(3, 6);
        try
        {
            matrix1.add(matrix2);
        } catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    public static void testMatrixAddition_nonSquareMatrix()
    {
        System.out.println("\n======================================\n");
        System.out.println("testMatrixAddition_nonSquareMatrix\n");
        SlowMatrix matrix1 = new SlowMatrix(2, 3);
        matrix1.setElement(0, 0, -1);
        matrix1.setElement(0, 1, 2);
        matrix1.setElement(0, 2, -3);
        matrix1.setElement(1, 0, 4);
        matrix1.setElement(1, 1, -5);
        matrix1.setElement(1, 2, 6);
        SlowMatrix matrix2 = new SlowMatrix(2, 3);
        matrix2.setElement(0, 0, -9);
        matrix2.setElement(0, 1, 8);
        matrix2.setElement(0, 2, -7);
        matrix2.setElement(1, 0, 6);
        matrix2.setElement(1, 1, -5);
        matrix2.setElement(1, 2, 4);
        System.out.println("matrix1 =\n" + matrix1);
        System.out.println("matrix2 =\n" + matrix2);
        System.out.println("matrix1 + matrix2 =\n" + matrix1.add(matrix2));
        System.out.println("matrix2 + matrix1 =\n" + matrix2.add(matrix1));
    }

    public static void testMatrixMultiplication_squareMatrix()
    {
        System.out.println("\n======================================\n");
        System.out.println("testMatrixMultiplication_squareMatrix\n");
        SlowMatrix matrix1 = new SlowMatrix(3, 3);
        matrix1.setElement(0, 0, -1);
        matrix1.setElement(0, 1, 2);
        matrix1.setElement(0, 2, -3);
        matrix1.setElement(1, 0, 4);
        matrix1.setElement(1, 1, -5);
        matrix1.setElement(1, 2, 6);
        matrix1.setElement(2, 0, -7);
        matrix1.setElement(2, 1, 8);
        matrix1.setElement(2, 2, -9);
        SlowMatrix matrix2 = new SlowMatrix(3, 3);
        matrix2.setElement(0, 0, -9);
        matrix2.setElement(0, 1, 8);
        matrix2.setElement(0, 2, -7);
        matrix2.setElement(1, 0, 6);
        matrix2.setElement(1, 1, -5);
        matrix2.setElement(1, 2, 4);
        matrix2.setElement(2, 0, -3);
        matrix2.setElement(2, 1, 2);
        matrix2.setElement(2, 2, -1);
        System.out.println("matrix1 =\n" + matrix1);
        System.out.println("matrix2 =\n" + matrix2);
        System.out.println("matrix1 * matrix2 =\n" + matrix1.multiply(matrix2));
        System.out.println("matrix2 * matrix1 =\n" + matrix2.multiply(matrix1));
    }

    public static void testMatrixMultiplication_nonSquareMatrix()
    {
        System.out.println("\n======================================\n");
        System.out.println("testMatrixMultiplication_nonSquareMatrix\n");
        SlowMatrix matrix1 = new SlowMatrix(2, 4);
        matrix1.setElement(0, 0, -1);
        matrix1.setElement(0, 1, 2);
        matrix1.setElement(0, 2, -3);
        matrix1.setElement(0, 3, 4);
        matrix1.setElement(1, 0, -5);
        matrix1.setElement(1, 1, 6);
        matrix1.setElement(1, 2, -7);
        matrix1.setElement(1, 3, 8);
        SlowMatrix matrix2 = new SlowMatrix(4, 2);
        matrix2.setElement(0, 0, 9);
        matrix2.setElement(0, 1, -8);
        matrix2.setElement(1, 0, 7);
        matrix2.setElement(1, 1, -6);
        matrix2.setElement(2, 0, 5);
        matrix2.setElement(2, 1, -4);
        matrix2.setElement(3, 0, 3);
        matrix2.setElement(3, 1, -2);
        System.out.println("matrix1 =\n" + matrix1);
        System.out.println("matrix2 =\n" + matrix2);
        System.out.println("matrix1 * matrix2 =\n" + matrix1.multiply(matrix2));
        System.out.println("matrix2 * matrix1 =\n" + matrix2.multiply(matrix1));
    }

    public static void testMatrixMultiplication_dimensionMismatch()
    {
        System.out.println("\n======================================\n");
        System.out.println("testMatrixMultiplication_dimensionMismatch\n");
        SlowMatrix matrix1 = new SlowMatrix(3, 4);
        SlowMatrix matrix2 = new SlowMatrix(3, 6);
        try
        {
            matrix1.multiply(matrix2);
        } catch(IllegalArgumentException e)
        {
            System.out.println(e);
        }
    }

    public static void main(String[] args)
    {
        testSubMatrix_squareMatrix();
        testSubMatrix_nonSquareMatrix();
        testSubMatrix_indexOutOfBound_upperLeftRow();
        testSubMatrix_indexOutOfBound_upperLeftCol();
        testSubMatrix_indexOutOfBound_lowerRightRow();
        testSubMatrix_indexOutOfBound_lowerRightCol();
        testMatrixAddition_squareMatrix();
        testMatrixAddition_nonSquareMatrix();
        testMatrixAddition_dimensionMismatch();
        testMatrixMultiplication_squareMatrix();
        testMatrixMultiplication_nonSquareMatrix();
        testMatrixMultiplication_dimensionMismatch();
    }
}
