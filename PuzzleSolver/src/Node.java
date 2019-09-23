
import java.util.ArrayList;

public class Node implements Comparable<Node>
{
    private int rowSize;
    private int columnSize;
    public  int pieceCount;
    private int internalPerimeter;
    private int externalPerimeter;
    public  byte[][] state;
    public  static Piece[] pieces = null;

    public Node(byte [][] state)
    {
        pieceCount = 0;
        this.state = state;
        externalPerimeter = (rowSize + columnSize) * 2;
        internalPerimeter = 0;
        rowSize = state.length;
        columnSize = state[0].length;
    }

    public Node(byte[][] state, int pieceCount, int inPerimeter, int i, int j)
    {
        this.pieceCount = pieceCount;
        this.state = state;
        this.internalPerimeter = inPerimeter;
        calculatePerimeters(i, j);
        rowSize = state.length;
        columnSize = state[0].length;
    }

    public ArrayList<Node> next()
    {
        ArrayList<Node> ans = new ArrayList<Node>();
        if (pieceCount < pieces.length)
        {
            Piece q = pieces[pieceCount];
            int iLimit = rowSize - q.rowSize;
            int jLimit = columnSize - q.columnSize;
            for (int r = 0; r < 4; r++, q.rotate())
            {
                for (int i = 0; i <= iLimit; i++)
                    for (int j = 0; j <= jLimit; j++)
                    {
                        byte[][] newState = new byte[rowSize][columnSize];
                        for (int k = 0; k < rowSize; k++)
                            System.arraycopy(state[k], 0, newState[k], 0, columnSize);
                        boolean placed = set(q, i,j, newState);
                        if (placed) ans.add(new Node(newState, pieceCount+1, internalPerimeter, i, j));
                    }
            }
        }

        return ans;
    }

    private boolean set(Piece p, int i, int j, byte[][] grid)
    {
        if (i + p.rowSize > grid.length) return false;
        if (j + p.columnSize > grid[0].length) return false;

        for (int r = 0; r < p.rowSize; r++)
        {
            for (int c = 0; c < p.columnSize; c++)
            {
                if (p.grid[r][c] != 0 && grid[r+i][c+j] != 0)
                    return false;
            }
        }

        for (int r = 0; r < p.rowSize; r++)
        {
            for (int c = 0; c < p.columnSize; c++)
            {
                if (p.grid[r][c] != 0)
                    grid[r+i][c+j] = p.grid[r][c];
            }
        }

        return true;
    }

    private void calculatePerimeters(int iLocation, int jLocation)
    {
        Piece p = pieces[pieceCount-1];
        int c = p.color;
        int x,y;

        for (int i = 0; i < p.rowSize; i++)
        {
            for (int j = 0; j < p.columnSize; j++)
            {
                if (p.grid[i][j] != 0)
                {
                    x = iLocation + i;
                    y = jLocation + j;

                    if (x + 1 == state.length) --externalPerimeter;
                    else internalPerimeter += (state[x+1][y] == 0 ? 1 : -1);
                    if (x == 0) --externalPerimeter;
                    else internalPerimeter += (state[x-1][y] == 0 ? 1 : -1);
                    if (y + 1 == state[0].length) --externalPerimeter;
                    else internalPerimeter += (state[x][y+1] == 0 ? 1 : -1);
                    if (y == 0) --externalPerimeter;
                    else internalPerimeter += (state[x][y-1] == 0 ? 1 : -1);
                }
            }
        }
    }

    private double inverse(int x)
    {
        if (x == 0) return 2;
        else return 1.0 / x;
    }

    public double evaluate()
    {
        return inverse(internalPerimeter) + inverse(externalPerimeter);
    }

    @Override
    public int compareTo(Node n)
    {
        double ans = n.evaluate() - this.evaluate();
        if (ans > 0) return 1;
        if (ans < 0) return -1;
        return 0;
    }
}