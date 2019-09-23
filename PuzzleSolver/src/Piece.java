public class Piece implements Comparable<Piece>
{
    public int rowSize;
    public int columnSize;
    public int perimeter;
    public byte color;

    public byte[][] grid;

    public Piece(int color, byte[][] grid)
    {
        perimeter = 0;
        rowSize = grid.length;
        columnSize = grid[0].length;
        this.color = (byte) color;
        this.grid = grid;
        calculatePerimeter();
    }

    private void calculatePerimeter()
    {
        for (int i = 0; i < rowSize; i++)
        {
            for (int j = 0; j < columnSize; j++)
            {
                if (grid[i][j] > 0)
                {
                    if (i+1 == grid.length) ++perimeter;
                    else if (grid[i+1][j] == 0) ++perimeter;
                    if (i == 0) ++perimeter;
                    else if (grid[i-1][j] == 0) ++perimeter;
                    if (j + 1 == grid[0].length) ++perimeter;
                    else if (grid[i][j+1] == 0) ++perimeter;
                    if (j == 0) ++perimeter;
                    else if (grid[i][j-1] == 0) ++perimeter;
                }
            }
        }
    }

    public void rotate()
    {
        byte[][] newGrid = new byte[columnSize][rowSize];
        for (int i = 0; i < rowSize ; i++)
            for (int j = 0; j < columnSize; j++)
                newGrid[columnSize-j-1][i] = grid[i][j];

        grid = newGrid;
        rowSize = grid.length;
        columnSize = grid[0].length;
    }

    @Override
    public int compareTo(Piece p)
    {
        return p.perimeter - perimeter;
    }
}
