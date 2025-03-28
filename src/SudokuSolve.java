import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SudokuSolve {
    public static class SudokuSolver{
        public static class Sudoku{
            char[][] grid = new char[9][9];
            Set<Character>[][] guessGrid = new HashSet[9][9];
            Queue<Integer> oneQueue = new LinkedList<>();
            int empty = 0;
            boolean unsolvableFlag=false;

            Sudoku(char[][] grid){
                setup(grid);
            }

            private void setup(char[][] grid){
                //first pass, empty cells
                for(int r=0;r<9;r++){

                    for(int c=0;c<9;c++){
                        this.grid[r][c] = grid[r][c];

                        if (grid[r][c]=='.'){
                            empty++;
                            for(int i=1; i<10;i++){
                                guessGrid[r][c].add(String.valueOf(i).charAt(0));
                            }
                        }
                    }
                }

                //second pass one Queue
                for(int r=0;r<9;r++){
                    for(int c=0;c<9;c++){
                        if (grid[r][c]=='.'){
                            continue;
                        }
                        checkOff(grid[r][c],r,c);
                    }
                }
            }

            private void checkCell(int r, int c){
                if (grid[r][c]=='.'){
                    if (guessGrid[r][c].isEmpty()){
                        unsolvableFlag=true;
                    } else if (guessGrid[r][c].size()==1){
                        oneQueue.add(r+c*9);
                    }
                }
            }

            private void checkOff(char guess, int r, int c){
                for(int cc = 0; cc<9;cc++){
                    guessGrid[r][cc].remove(guess);
                    checkCell(r,cc);
                }
                for(int rr=0; rr<9;rr++){
                    guessGrid[rr][c].remove(guess);
                    checkCell(rr,c);
                }
                int r0 = (r-r%2)/3;
                int c0 = (c-c%2)/3;
                for(int rr = 0; rr<3;rr++){
                    for(int cc=0; cc<3;cc++){
                        guessGrid[r0+rr][c0+cc].remove(guess);
                        checkCell(r0+rr,c0+cc);
                    }
                }
            }

            public Sudoku(Sudoku s){
                if (s.solvedStatus()==-1){

                    //would raise an exception, thought of this first
                    for(int r=0;r<9;r++){
                        for(int c=0;c<9;c++){
                            grid[r][c]='.';
                            setup(grid);
                            return;
                        }
                    }
                }
                char[][] sGrid = s.getGrid();
                Set<Character>[][] sGuessGrid = s.getGuessGrid();
                for(int r=0;r<9;r++){
                    for(int c=0;c<9;c++){
                        grid[r][c]=sGrid[r][c];
                        guessGrid[r][c].addAll(sGuessGrid[r][c]);
                        if(grid[r][c]=='.'){
                            empty++;
                            if(guessGrid[r][c].size()==1){
                                try {
                                    oneQueue.add(r+c*9);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        }
                    }
                }
            }

            private Set<Character>[][] getGuessGrid(){
                return guessGrid;
            }
            private char[][] getGrid(){
                return grid;
            }



            public void autoFill(){
                while(!oneQueue.isEmpty() && !unsolvableFlag){
                    int i = oneQueue.poll();
                    int c = i%9;
                    int r = (i-c)/9;
                    for(char ch:guessGrid[r][c]){
                        grid[r][c]=ch;
                        checkOff(ch,r,c);
                    }
                    guessGrid[r][c] = new HashSet<>();
                }
            }

            public int solvedStatus(){
                return unsolvableFlag? -1 : (empty>0 ? 0 : 1);
            }


            public void fillRandom(int n){
                //try n times, get choice with fewest guess left,
                //pick a random guess

            }


        }
        static void solve(char[][] grid){
            /*
            edits original grid if solvable
            tells you if it believes this to be unsolvable.
            */
            LinkedList<Sudoku> sudStack = new LinkedList<>();
            sudStack.add(new Sudoku(grid));
            while(true){

                /*
                take sudoku off top of stack
                autofill
                if not solved and not unsolvable
                clone it
                put back on stack
                fill in random cell to clone
                put on stack

                if unsolvable, take off last to stack
                if stack empty, return that this is unsolvable

                probably
                 */


            }
        }
    }


}
