
public class MyLevels {

    /**
     * Character values for displaying the different statuses of the game board cells.
     */
    public static final char EMPTY_CHAR = ' '; // Empty character
    public static final char BOX_CHAR = '='; // Box character
    public static final char WALL_CHAR   = '#'; // Wall character
    public static final char WORKER_CHAR  = '@'; // Worker character
    public static final char GOAL_CHAR  = '.'; // Worker character
    public static final char BOX_GOAL_CHAR  = '*'; // Box on a goal character
    public static final char WORK_GOAL_CHAR  = '+'; // Worker on a goal character

    /**
     * Constants for the random processes.
     */
    public static final long SEED            = 1234; // The random seed

    /**
     * Initial configuration of the levels. 
     * Note that we are using the actual characters to make it easier to visualize the initial
     * configurations, but it would be better to use the character constants defined above.
     */
    public static final char[][][] LEVELS = {
        {
            //{' ', ' ', ' '},
            //{' ', ' ', ' '},
            //{' ', '=', ' '},
            //{' ', ' ', ' '},
            //{' ', ' ', '@'}
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR}

        },
        {
        	//{' ', ' ', ' ',' ', ' ',' ',' ',' '},
            //{' ', ' ', ' ',' ', ' ', ' '},
            //{' ', '=', '=',' ', ' ', ' '},
            //{' ', ' ', ' ',' ', ' ', ' '},
            //{' ', ' ', '@',' ', ' ', ' '},
        	//{' ', ' ', ' ',' ', ' ', ' '},
        	//{' ', ' ', ' ',' ', ' ', ' '},
        	//{' ', ' ', ' ',' ', ' ', '#'},
        	//{' ', ' ', ' ',' ', ' ', '#'}
        	{EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, BOX_CHAR, BOX_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, WORKER_CHAR,EMPTY_CHAR,EMPTY_CHAR,WALL_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,WALL_CHAR}
            
        },
        {
        	//{' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' ',' '},
            //{' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' '},
            //{'=', '=', '=',' ', ' ', ' ',' ', ' ', ' '},
            //{' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' '},
            //{' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' '},
        	//{' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' '},
        	//{' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' '},
        	//{' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' '},
        	//{'@', ' ', ' ',' ', ' ', ' ','#', '#','#'}
        	{EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {BOX_CHAR, BOX_CHAR, BOX_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR},
            {WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,EMPTY_CHAR,WALL_CHAR, WALL_CHAR, WALL_CHAR}
        }
        
    };

    public static final int[][] GOALS = { {0,0}, {1,1,1,2},{0,0,0,1,0,2} };

    public static final char UP_CHAR = '8';
    public static final char DOWN_CHAR = '2';
    public static final char LEFT_CHAR = '4';
    public static final char RIGHT_CHAR = '6';
    public static final char QUIT_CHAR = 'q';
}



