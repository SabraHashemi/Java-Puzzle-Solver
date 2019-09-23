
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Solver extends JPanel
{
    private final int blockSize = 50;
    private int rowSize;
    private int columnSize;
    private Color[][] coloredGrid;
    private Color[] colors;

    public Solver(int rowSize, int colSize, Piece[] pieces, Color[] colors)
    {
        this.rowSize = rowSize;
        this.columnSize = colSize;
        coloredGrid = new Color[rowSize][colSize];
        Node.pieces = pieces;
        this.colors = colors;
    }

    public boolean Solve()
    {
        Arrays.sort(Node.pieces);
        Node start = new Node(new byte[rowSize][columnSize]);
        Queue<Node> q = new PriorityQueue<Node>(2048);
        q.add(start);

        while (!q.isEmpty())
        {
            Node top = q.poll();
            if (top.pieceCount == Node.pieces.length)
            {
                fillColoredGrid(top.state);
                return true;
            }

            ArrayList<Node> childs = top.next();
            q.addAll(childs);
        }

        return false;
    }

    public void fillColoredGrid(byte[][] state)
    {
        coloredGrid = new Color[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++)
            for (int j = 0; j < columnSize; j++)
                coloredGrid[i][j] = colors[state[i][j]];
    }
    public void showResult()
    {
        JFrame frame = new JFrame();
        frame.setSize(columnSize*blockSize + 7, rowSize*blockSize + 29);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (int i = 0; i < rowSize; i++)
        {
            for (int j = 0; j < columnSize; j++)
            {
                g.setColor(coloredGrid[i][j]);
                g.fillRect(j*blockSize, i*blockSize, blockSize, blockSize);
            }
        }
    }


}