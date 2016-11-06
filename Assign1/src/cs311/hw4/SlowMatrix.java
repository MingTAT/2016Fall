package cs311.hw4;

/**
 * 
 * Represents a matrix of n rows by m columns.  Note that row and column indexes 
 * start at 0.
 * 
 **/
public class SlowMatrix implements IMatrix, IMeasurable
{
    private final int nRow;
    private final int nCol;
    private final Number[][] elements;

    public SlowMatrix(int nRow, int nCol)
    {
        this.nRow = nRow;
        this.nCol = nCol;
        this.elements = new Number[nRow][nCol];
    }

    public SlowMatrix(int nRow, int nCol, Number[][] elements)
    {
        this.nRow = nRow;
        this.nCol = nCol;
        this.elements = elements;
    }

    /**
     * The execute method contains code that is to be measured.  Typically a
     * simple call to the method that is to be measured in the object.
     */
    public void execute()
    {
        //this.multiply(this);
        this.add(this);
    }

    /**
     * Creates a new Object that implements the IMatrix interface that is a
     * submatrix of this matrix.  The elements of the submatrix are deep
     * copied.
     * 
     * @param upperLeftRow is the upper left row of the submatrix, inclusive.
     * @param upperLeftCol is the upper left column of the submatrix, inclusive.
     * @param lowerRightRow is the lower right row of the submatrix, inclusive.
     * @param lowerRightCol is the lower right column of the submatrix, inclusive.
     * 
     * @return the new submatrix as an object that implements the IMatrix interface.
     * 
     * @throws IllegalArgumentException if any of the arguments are out of bounds.
     */
    public IMatrix subMatrix( int upperLeftRow, int upperLeftCol, int lowerRightRow, int lowerRightCol) throws IllegalArgumentException
    {
        checkRow(upperLeftRow, "upperLeftRow");
        checkRow(lowerRightRow, "lowerRightRow");
        checkCol(upperLeftCol, "upperLeftCol");
        checkCol(lowerRightCol, "lowerRightCol");

        SlowMatrix newMatrix = new SlowMatrix(lowerRightRow - upperLeftRow + 1, lowerRightCol - upperLeftCol + 1);
        for (int i = upperLeftRow; i <= lowerRightRow; i++)
        {
            for (int j = upperLeftCol; j <= lowerRightCol; j++)
            {
                newMatrix.setElement(i - upperLeftRow, j - upperLeftCol, this.elements[i][j]);
            }
        }
        return newMatrix;
    }
    
    /**
     * Sets an element of the matrix.
     * 
     * @param row is the row at which to set the element.
     * @param col is the column at which to set the element.
     * @param val is the value that must be a Number type or subclass of Number type to set.
     * 
     * @throws IllegalArgumentException if row or column is not a valid index into the matrix.
     */
    public void setElement( int row, int col, Number val) throws IllegalArgumentException
    {
        checkRowAndCol(row, col);
        this.elements[row][col] = val;
    }
    
    /**
     *  Returns the specified element in the matrix.
     * 
     * @param row is the row at which to retrieve the element.
     * @param col is the column at which to retrieve the element.
     * 
     * @return the value of the element at the specified row and column.
     * 
     * @throws IllegalArgumentException if row or column is out of bounds.
     */
    public Number getElement( int row, int col ) throws IllegalArgumentException
    {
        checkRowAndCol(row, col);
        return this.elements[row][col];
    }
    
    /**
     * Performs a matrix multiplication of this matrix and the matrix given in the
     * parameter.  Note that the order of the multiplication is this matrix A multiplied
     * by the parameter matrix B.  That is A * B.  The result is returned as a new
     * IMatrix type.  The multiplication need only run in n^3 asymptotic time.
     * 
     * @param mat is the matrix that this matrix is multiplied by.
     * 
     * @return the resultant matrix of the multiplication.
     * @throws IllegalArgumentException if the sizes of the matricies are 
     * not compatible with multiplication.
     */
    public IMatrix multiply( IMatrix mat ) throws IllegalArgumentException
    {
        if (!(mat instanceof SlowMatrix)) {
            throw new IllegalArgumentException(String.format(
                "%s: %s\n%s: %s",
                "this.class",
                this.getClass(),
                "mat.class",
                mat.getClass()));
        }
        SlowMatrix that = (SlowMatrix) mat;

        if (this.nCol != that.nRow)
        {
            throw new IllegalArgumentException(String.format(
                "%s\n%s: %d x %d\n%s: %d x %d",
                "matrices dimension not compatible with matrix multiplicatoin:",
                "this.dim",
                this.nRow,
                this.nCol,
                "that.dim",
                that.nRow,
                that.nCol));
        }
        SlowMatrix result = new SlowMatrix(this.nRow, that.nCol);
        // bruteforce O(n^3) matrix multiplication algorithm
        for (int i = 0; i < this.nRow; i++)
        {
            for (int j = 0; j < that.nCol; j++)
            {
                int tmpSum = 0;
                for (int k = 0; k < this.nCol; k++)
                {
                    tmpSum += this.elements[i][k].intValue() * that.elements[k][j].intValue();
                }
                result.setElement(i, j, tmpSum);
            }
        }
        return result;
    }
    
    /**
     * Performs a matrix addition of this matrix and the matrix given in the parameter.
     * Te result is returned as a new IMatrix type.  The matrix addition runs in
     * n^2 asymptotic time.
     * 
     * @param mat is the matrix to add
     * 
     * @return the resultant matrix of the addition
     * 
     * @throws IllegalArgumentException if the sizes of the matricies are not 
     * compatible with addition.
     */
    public IMatrix add( IMatrix mat ) throws IllegalArgumentException
    {
        if (!(mat instanceof SlowMatrix)) {
            throw new IllegalArgumentException(String.format(
                "%s: %s\n%s: %s",
                "this.class",
                this.getClass(),
                "mat.class",
                mat.getClass()));
        }
        SlowMatrix that = (SlowMatrix) mat;

        if ((this.nRow != that.nRow) || (this.nCol != that.nCol))
        {
            throw new IllegalArgumentException(String.format(
                "%s\n%s: %d x %d\n%s: %d x %d",
                "matrices dimension not compatible with matrix addition:",
                "this.dim",
                this.nRow,
                this.nCol,
                "that.dim",
                that.nRow,
                that.nCol));
        }
        SlowMatrix result = new SlowMatrix(this.nRow, this.nCol);
        for (int i = 0; i < this.nRow; i++)
        {
            for (int j = 0; j < this.nCol; j++)
            {
                result.setElement(i, j, this.elements[i][j].intValue() + that.elements[i][j].intValue());
            }
        }
        return result;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.nRow; i++)
        {
            for (int j = 0; j < this.nCol; j++)
            {
                builder.append(this.elements[i][j]);
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private void checkRowAndCol(int row, int col) throws IllegalArgumentException
    {
        checkRow(row, "row");
        checkCol(col, "col");
    }

    private void checkRow(int row, String varName) throws IllegalArgumentException
    {
        if ((row < 0) || (row >= nRow))
        {
            throw new IllegalArgumentException(varName + " = " + row + ", nRow = " + nRow);
        }
    }

    private void checkCol(int col, String varName) throws IllegalArgumentException
    {
        if ((col < 0) || (col >= nCol))
        {
            throw new IllegalArgumentException(varName + " = " + col + ", nCol = " + nCol);
        }
    }
}