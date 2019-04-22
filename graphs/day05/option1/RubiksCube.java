import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.text.ChangedCharSetException;


// use this class if you are designing your own Rubik's cube implementation
public class RubiksCube {

    int[] cube;
    HashMap<Integer, Integer[]> orientation = new HashMap<Integer, Integer[]>();
    int[] solved = new int[]{1,2,3,4,5,6,7,8};
    int solvedHash = 12345678;
    RubiksCube previous = null;
    Character pRotation = null;
    // initialize a solved rubiks cube
    public RubiksCube() {
        cube = new int[]{1,2,3,4,5,6,7,8}; // Initialize as solved cube
        for (int i : cube) {
            orientation.put(i, new Integer[]{1,1,1});
        }
    }


    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = new int[8];
        System.arraycopy(r.cube, 0, cube, 0, 8);
        for (int i : cube) {
            Integer[] o = new Integer[3];
            System.arraycopy(r.orientation.get(i), 0, o, 0, 3);
            orientation.put(i, o);
        }
    }

    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        if (hashCode() == other.hashCode()) {
            for (int i : cube) {
                if (!(intListChecker(orientation.get(i), other.orientation.get(i))))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        String cString = "";
        for (int i : cube) {
            cString += i;
        }
        return Integer.parseInt(cString);
    }

    public boolean isSolved() {
        Integer[] solvedO = new Integer[]{1,1,1};
        if (hashCode() == solvedHash) {
            for (int i : cube) {
                if (!(intListChecker(orientation.get(i), solvedO)))
                    return false;
            }
            return true;
        }
        return false;
    }

    private boolean intListChecker(Integer[] a, Integer[] b) {
        for (int i = 0; i < 3; i++) {
            if (!(a[i].equals(b[i])))
                return false;
        }
        return true;
    }


    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        RubiksCube r;
        switch(c){
            case 'r':
                rotateRightCW();
                r = new RubiksCube(this);
                rotateRightCCW();
                break;
            case 'u':
                rotateUpCW();
                r = new RubiksCube(this);
                rotateUpCCW();
                break;
            case 'f':
                rotateFrontCW();
                r = new RubiksCube(this);
                rotateFrontCCW();
                break;
            case 'R':
                rotateRightCCW();
                r = new RubiksCube(this);
                rotateRightCW();
                break;
            case 'U':
                rotateUpCCW();
                r = new RubiksCube(this);
                rotateUpCW();
                break;
            case 'F':
                rotateFrontCCW();
                r = new RubiksCube(this);
                rotateFrontCW();
                break;
            default:
                r = new RubiksCube();
        }
        return r;
    }

    private void rotateRightCW() {
        int temp = cube[1];
        cube[1] = cube[2];
        cube[2] = cube[7];
        cube[7] = cube[4];
        cube[4] = temp;
        int[] seq = new int[]{1,2,7,4};
        // System.out.println("Begin orientation update.");
        for (int i : seq) {
            // System.out.println("Ori O: Cube " + i + " | " + cube[i] + " | " + Arrays.toString(o));
            orientation.put(cube[i], rotateXNeg(orientation.get(cube[i])));
            // System.out.println("New O: Cube " + i + " | " + cube[i] + " | " + Arrays.toString(orientation.get(cube[i])));
        }
    }

    private void rotateUpCW() {
        int temp = cube[0];
        cube[0] = cube[1];
        cube[1] = cube[4];
        cube[4] = cube[5];
        cube[5] = temp;
        int[] seq = new int[]{0,1,4,5};
        for (int i : seq) {
            orientation.put(cube[i], rotateYNeg(orientation.get(cube[i])));
        }
    }

    private void rotateFrontCW() {
        int temp = cube[0];
        cube[0] = cube[3];
        cube[3] = cube[2];
        cube[2] = cube[1];
        cube[1] = temp;
        int[] seq = new int[]{0,3,2,1};
        for (int i : seq) {
            orientation.put(cube[i], rotateZNeg(orientation.get(cube[i])));
        }
    }

    private void rotateRightCCW() {
        int temp = cube[1];
        cube[1] = cube[4];
        cube[4] = cube[7];
        cube[7] = cube[2];
        cube[2] = temp;
        int[] seq = new int[]{1,4,7,2};
        // System.out.println("Begin orientation update.");
        for (int i : seq) {
            // System.out.println("Ori O: Cube " + i + " | " + cube[i] + " | " + Arrays.toString(o));
            orientation.put(cube[i], rotateXPos(orientation.get(cube[i])));
            // System.out.println("New O: Cube " + i + " | " + cube[i] + " | " + Arrays.toString(orientation.get(cube[i])));
        }
    }

    private void rotateUpCCW() {
        int temp = cube[0];
        cube[0] = cube[5];
        cube[5] = cube[4];
        cube[4] = cube[1];
        cube[1] = temp;
        int[] seq = new int[]{0,5,4,1};
        for (int i : seq) {
            orientation.put(cube[i], rotateYPos(orientation.get(cube[i])));
        }
    }

    private void rotateFrontCCW() {
        int temp = cube[0];
        cube[0] = cube[1];
        cube[1] = cube[2];
        cube[2] = cube[3];
        cube[3] = temp;
        int[] seq = new int[]{0,1,2,3};
        for (int i : seq) {
            orientation.put(cube[i], rotateZPos(orientation.get(cube[i])));
        }
    }

    private Integer[] rotateXPos(Integer[] o) {
        return new Integer[]{o[0], -1 * o[2], o[1]};
    }
    
    private Integer[] rotateXNeg(Integer[] o) {
        return new Integer[]{o[0], o[2], -1 * o[1]};
    }

    private Integer[] rotateYPos(Integer[] o) {
        return new Integer[]{o[2], o[1], -1 * o[0]};
    }
    
    private Integer[] rotateYNeg(Integer[] o) {
        return new Integer[]{-1 * o[2], o[1], o[0]};
    }

    private Integer[] rotateZPos(Integer[] o) {
        return new Integer[]{-1 * o[1], o[0], o[2]};
    }
    
    private Integer[] rotateZNeg(Integer[] o) {
        return new Integer[]{o[1], -1 * o[0], o[2]};
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r = r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {
        LinkedList<Character> moves = new LinkedList<Character>();
        HashMap<RubiksCube, Integer> visited = new HashMap<RubiksCube, Integer>();
        Character[] rList = new Character[]{'r','u','f','R','U','F'};
        RubiksCube current = this;
        visited.put(current, 1);
        Queue<RubiksCube> nextCubes = new LinkedList<RubiksCube>();
        for (Character c : rList) {
            RubiksCube next = current.rotate(c);
            next.previous = current;
            next.pRotation = c;
            nextCubes.add(next);
        }
        while (!nextCubes.isEmpty()) {
            current = nextCubes.poll();
            if (current.isSolved())
                break;
            for (Character c : rList) {
                RubiksCube next = current.rotate(c);
                next.previous = current;
                next.pRotation = c;
                if (!(visited.containsKey(next))) {
                    nextCubes.add(next);
                }
            }
        }
        while (current.previous != null) {
            moves.addFirst(current.pRotation);
            current = current.previous;
        }
        // Transform into List type
        List<Character> moveList = moves;
        return moveList;
    }

    /* public static void main(String[] args) {
        RubiksCube r = new RubiksCube();
        r = r.scrambledCube(20);
        List<Character> moves = r.solve();
        for (Character c : moves) {
            System.out.print(c + " ");
        }
    } */

}



