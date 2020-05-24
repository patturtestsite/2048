/**
 * 
 * Represents the 2048 board and provides methods for moving up, down, left and
 * right. Also provides information about the game including score, highest tile
 * and whether the game is over or not
 *
 * @author Pranay Tiru
 * @version May 23, 2020
 * @author Period: 5
 * @author Assignment: APCSFinal
 *
 * @author Sources: None
 */
public class Board
{
    public Tile[][] board;

    int grids = 4;

    int border = 0;

    public int score = 0;


    /**
     * Default constructor for the Board - sets up a 4x4 matrix
     */
    public Board()
    {
        board = new Tile[4][4];
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                board[i][j] = new Tile();
            }
        }
    }


    /**
     * Constructor for the board - sets up a matrix with a specified grid size -
     * Not used in this version, but hopefully implement in the future
     * 
     * @param grids
     */
    public Board( int grids )
    {
        this.grids = grids;
        board = new Tile[grids][grids];
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                board[i][j] = new Tile();
            }
        }
    }


    /**
     * Board constructor with 2 parameters - sets up a board where the player
     * has already lost (for testing purposes)
     * 
     * @param lose
     * @param grids
     */
    public Board( int lose, int grids )
    {
        this.grids = grids;
        board = new Tile[grids][grids];
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                board[i][j] = new Tile( ( lose + i + j ) * ( i + j ) );
            }
        }
    }


    /**
     * 
     * Getter method that returns the board
     * 
     * @return board
     */
    public Tile[][] getBoard()
    {
        return board;
    }


    /**
     * 
     * Getter method that returns the score
     * 
     * @return score
     */
    public int getScore()
    {
        return score;
    }


    /**
     * 
     * Finds the the highest tile on the board and returns it
     * 
     * @return high
     */
    public int getHighTile()
    {
        int high = board[0][0].getValue();
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                if ( board[i][j].getValue() > high )
                {
                    high = board[i][j].getValue();
                }
            }
        }
        return high;
    }


    /**
     * 
     * Prints out the board on the console - for testing purposes
     */
    public void print()
    {
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                String s = board[i][j].toString() + " ";
                System.out.print( s );
            }
            System.out.println( "" );
        }
        System.out.println( "Score: " + score );
    }


    /**
     * Returns the board as a String - used in the GUI
     */
    public String toString()
    {
        String s = "";
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                s += board[i][j].toString() + " ";
            }
            s += "\n";
        }
        return s;
    }


    /**
     * 
     * Spawns a 2 at an empty space every time a move is made
     */
    public void spawn()
    {
        boolean empty = true;
        while ( empty )
        {
            int row = (int)( Math.random() * 4 );
            int col = (int)( Math.random() * 4 );
            double x = Math.random();
            if ( board[row][col].getValue() == 0 )
            {
                if ( x < 0.2 )
                {
                    board[row][col] = new Tile( 4 );
                    empty = false;
                }
                else
                {
                    board[row][col] = new Tile( 2 );
                    empty = false;
                }
            }

        }

    }


    /**
     * 
     * Checks to see if the board is completely blacked out and if it is, it
     * will give a suggestion to the player - nudging them to restart
     * 
     * @return boolean
     */
    public boolean blackOut()
    {
        int count = 0;
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                if ( board[i][j].getValue() > 0 )
                {
                    count++;
                }
            }
        }
        if ( count == 16 )
        {
            return true;
        }
        return false;
    }


    /**
     * 
     * Checks to see if the game is over - that is, checks if any tile (that
     * isn't a 0) is able to combine with the tiles next to it - If not, the
     * game is over
     * 
     * @return boolean
     */
    public boolean gameOver()
    {
        int count = 0;
        for ( int i = 0; i < board.length; i++ )
        {
            for ( int j = 0; j < board[i].length; j++ )
            {
                if ( board[i][j].getValue() > 0 )
                {
                    if ( i == 0 && j == 0 )
                    {
                        if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 0 && j == 3 )
                    {
                        if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 3 )
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && j == 0 )
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 0 && ( j == 1 || j == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i + 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( i == 3 && ( j == 1 || j == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i][j - 1].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( j == 0 && ( i == 1 || i == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                        {
                            count++;
                        }
                    }
                    else if ( j == 3 && ( i == 1 || i == 2 ) )
                    {
                        if ( board[i][j].getValue() != board[i][j - 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                        {
                            count++;
                        }
                    }
                    else
                    {
                        if ( board[i][j].getValue() != board[i][j - 1].getValue()
                            && board[i][j].getValue() != board[i][j + 1].getValue()
                            && board[i][j].getValue() != board[i - 1][j].getValue()
                            && board[i][j].getValue() != board[i + 1][j].getValue() )
                        {
                            count++;
                        }
                    }
                }
            }
        }
        if ( count == 16 )
        {
            return true;
        }
        return false;
    }


    /**
     * 
     * This method is called when a 'w' or up arrow is pressed - goes through
     * the entire board and calls verticalMove with an "up" parameter for each
     * tile
     */
    public void up()
    {
        for ( int i = 0; i < grids; i++ )
        {
            border = 0;
            for ( int j = 0; j < grids; j++ )
            {
                if ( board[j][i].getValue() != 0 )
                {
                    if ( border <= j )
                    {
                        verticalMove( j, i, "up" );
                    }
                }
            }
        }
    }


    /**
     * 
     * This method is called when a 's' or down arrow is pressed - goes through
     * the entire board and calls verticalMove with a "down" parameter for each
     * tile
     */
    public void down()
    {
        for ( int i = 0; i < grids; i++ )
        {
            border = ( grids - 1 );
            for ( int j = grids - 1; j >= 0; j-- )
            {
                if ( board[j][i].getValue() != 0 )
                {
                    if ( border >= j )
                    {
                        verticalMove( j, i, "down" );
                    }
                }
            }
        }
    }


    /**
     * 
     * Compares two tile's values together and if they are the same or if one is
     * equal to 0 (plain tile) - their values are added (provided that the tiles
     * we are comparing are two different tiles and they are moving towards the
     * appropriate direction) - Uses recursion to go through the entire column
     * 
     * @param row
     *            row that the compare tile is currently on
     * @param col
     *            column that the compare tile is currently on
     * @param direction
     *            direction (up or down) that the tile is moving in
     */
    private void verticalMove( int row, int col, String direction )
    {
        Tile initial = board[border][col];
        Tile compare = board[row][col];
        if ( initial.getValue() == 0 || initial.getValue() == compare.getValue() )
        {
            if ( row > border || ( direction.equals( "down" ) && ( row < border ) ) )
            {
                int addScore = initial.getValue() + compare.getValue();
                if ( initial.getValue() != 0 )
                {
                    score += addScore;
                }
                initial.setValue( addScore );
                compare.setValue( 0 );
            }
        }
        else
        {
            if ( direction.equals( "down" ) )
            {
                border--;
            }
            else
            {
                border++;
            }
            verticalMove( row, col, direction );
        }
    }


    /**
     * 
     * This method is called when an 'a' or left arrow is pressed - goes through
     * the entire board and calls horizontalMove with a "left" parameter for
     * each tile
     */
    public void left()
    {
        for ( int i = 0; i < grids; i++ )
        {
            border = 0;
            for ( int j = 0; j < grids; j++ )
            {
                if ( board[i][j].getValue() != 0 )
                {
                    if ( border <= j )
                    {
                        horizontalMove( i, j, "left" );
                    }
                }
            }
        }
    }


    /**
     * 
     * This method is called when a 'd' or right arrow is pressed - goes through
     * the entire board and calls horizontalMove with a "right" parameter for
     * each tile
     */
    public void right()
    {
        for ( int i = 0; i < grids; i++ )
        {
            border = ( grids - 1 );
            for ( int j = ( grids - 1 ); j >= 0; j-- )
            {
                if ( board[i][j].getValue() != 0 )
                {
                    if ( border >= j )
                    {
                        horizontalMove( i, j, "right" );
                    }
                }
            }
        }
    }


    /**
     * 
     * Compares two tile's values together and if they are the same or if one is
     * equal to 0 (plain tile) - their values are added (provided that the tiles
     * we are comparing are two different tiles and they are moving towards the
     * appropriate direction) - Uses recursion to go through the entire row
     * 
     * @param row
     *            row that the compare tile is currently on
     * @param col
     *            column that the compare tile is currently on
     * @param direction
     *            direction (left or right) that the tile is moving in
     */
    private void horizontalMove( int row, int col, String direction )
    {
        Tile initial = board[row][border];
        Tile compare = board[row][col];
        if ( initial.getValue() == 0 || initial.getValue() == compare.getValue() )
        {
            if ( col > border || ( direction.equals( "right" ) && ( col < border ) ) )
            {
                int addScore = initial.getValue() + compare.getValue();
                if ( initial.getValue() != 0 )
                {
                    score += addScore;
                }
                initial.setValue( addScore );
                compare.setValue( 0 );
            }
        }
        else
        {
            if ( direction.equals( "right" ) )
            {
                border--;
            }
            else
            {
                border++;
            }
            horizontalMove( row, col, direction );
        }
    }

}
