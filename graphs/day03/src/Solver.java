/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

import javax.swing.plaf.nimbus.State;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private State fin;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class StateComparer implements Comparator<State> {
        public int compare(State s1, State s2) {
            return s1.moves + s1.cost - s2.moves - s2.cost;
        }
    }

    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = 0;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        if (state.prev == null) {
            return state;
        }
        return root(state.prev);
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        solutionState = new State(initial, 0, null);

        if (isSolvable()) {
            PriorityQueue<State> solQ = new PriorityQueue<State>(new StateComparer());
            solutionState.cost = solutionState.board.manhattan();
            solQ.add(solutionState);
            HashMap<Board, Integer> visited = new HashMap<Board, Integer>();
            visited.put(solutionState.board, 1);

            boolean solved = false;

            while (!solQ.isEmpty() && !solved) {
                State temp = solQ.poll();
                if (temp.cost == 0) {
                    solved = true;
                    fin = temp;
                }
                else {
                    Iterable<Board> it = temp.board.neighbors(false);
                    for (Board i : it) {
                        if (!visited.containsKey(i)) {
                            visited.put(i, 1);
                            State temp2 = new State(i, temp.moves+1, temp);
                            temp2.cost = temp2.board.manhattan();
                            solQ.add(temp2);
                        }
                    }
                }
            }
            minMoves = fin.moves;
        }
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        LinkedList<Board> seq = new LinkedList<Board>();
        State temp = fin;
        while (temp != null) {
            seq.addFirst(temp.board);
            temp = temp.prev;
        }
        Iterable<Board> finseq = seq;
        return finseq;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        // int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}}; // Easy
        // int[][] initState = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}}; // Average
        // int[][] initState = {{2, 3, 6}, {1, 5, 0}, {4, 7, 8}}; // Medium
        // int[][] initState = {{0, 3, 5}, {2, 1, 8}, {4, 7, 6}}; // Hard
        // int[][] initState = {{3, 5, 6}, {1, 2, 8}, {0, 4, 7}}; // Really hard
        // int[][] initState = {{3, 5, 2}, {6, 0, 1}, {7, 8, 4}}; // Ridiculously hard
        int[][] initState = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}}; // Insanely hard
        Board initial = new Board(initState);
        long startTime = System.nanoTime();

        Solver solver = new Solver(initial);
        long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime) / 1000000;
        System.out.println("Time taken: " + timeTaken);
        System.out.println("Steps: " + solver.minMoves);

        Iterable<Board> it = solver.solution();

        for (Board i : it) {
            System.out.println(Arrays.deepToString(i.tiles));
        }
    }


}
