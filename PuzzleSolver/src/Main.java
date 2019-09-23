
import java.awt.Color;
import javax.swing.JOptionPane;

class Main
{
//    public static byte[][] copy(byte[][] c)
//    {
//        byte[][] a = new byte[c.length][c[0].length];
//        for (int i = 0; i < c.length; i++)
//            System.arraycopy(c[i], 0, a[i], 0, c[i].length);
//        return a;
//    }

    final static Color VIOLET = new Color(128,0,128);
    final static Color[] colors = {Color.WHITE, Color.RED, Color.CYAN, Color.GREEN,
            Color.YELLOW, VIOLET, Color.BLUE, Color.PINK, Color.ORANGE};
    public static void main(String[] args)
    {

        Piece p1 = new Piece(1, new byte[][]
        {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        });

        Piece p2 = new Piece(2, new byte[][]
        {
            {2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2}
        });

        Piece p3 = new Piece(3, new byte[][]
        {
            {3, 0, 0, 0, 0, 0},
            {3, 0, 0, 0, 0, 0},
            {3, 0, 0, 0, 0, 0},
            {3, 0, 0, 0, 0, 0},
            {3, 3 , 3 , 3 , 3 ,  3}
        });

        Piece p4 = new Piece(4, new byte[][]
        {
            {4, 4, 4, 4},
            {4, 4, 4, 4},
            {4, 4, 4, 4},
            {4, 4, 4, 4},
            {4, 4, 4, 4}
        });

        Piece[] set1 = {p1,p2,p3,p4};

        Piece q1 = new Piece(4, new byte[][]
        {
            {4, 4},
            {4, 4},
            {4, 4},
            {4, 4}
        });

        Piece q2 = new Piece(3, new byte[][]
        {
            {3, 0, 0, 3},
            {3, 0, 0, 3},
            {3, 3, 3, 3},
        });

        Piece q3 = new Piece(2, new byte[][]
        {
            {2, 2, 2},
            {2, 2, 2},
            {0, 2, 2}
        });

        Piece q4 = new Piece(5, new byte[][]
        {
            {5, 5,   0},
            {5, 5,   0},
            {  0, 5, 5},
            {  0, 5, 5}
        });

        Piece q5 = new Piece(6, new byte[][]
        {
            {0, 0, 6},
            {6, 6, 6},
            {6, 6, 6},
            {0, 0, 6}
        });

        Piece q6 = new Piece(1, new byte[][]
        {
            {0, 1, 0},
            {0, 1, 0},
            {1, 1, 1},
            {1, 1, 1}
        });

        Piece q7 = new Piece(7, new byte[][]
        {
            {7, 7, 7, 0, 0},
            {7, 7, 7, 7, 7}
        });

        Piece q8 = new Piece(8, new byte[][]
        {
            {8, 8, 8},
            {8, 8, 8},
            {0, 0, 8},
            {0, 0, 8}
        });

        Piece[] set2 = {q1,q2,q3,q4,q5,q6,q7,q8};

        Solver game = new Solver(8, 8, set2, colors);
        boolean b = game.Solve();
        if (b)
        {
            game.showResult();
            System.out.println("Yes");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Not Solvable!");
            System.out.println("No");
        }
    }

}