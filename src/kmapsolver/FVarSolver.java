/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmapsolver;

/**
 *
 * @author emran
 */
public class FVarSolver {

    private String output = "";
    private final int A[][] = new int[4][4];
    private final int checked[][] = new int[4][4];

    public FVarSolver(int val[]) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                A[i][j] = val[count++];
                checked[i][j] = 0;
            }
        }
    }

    public String solve() {
        if (!check16()) {
            // reaches if all values are 1
            output = "1";
        } else {
            // reaches if smaller groups are to be found rather than 16
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (A[i][j] == 1 && checked[i][j] == 0) {
                        if (check8(i, j)) {
                            if (check4(i, j)) {
                                if (check2(i, j)) {
                                    nogrouping(i, j);
                                }
                            }
                        }
                    }
                }
            }
        }
        return output;
    }

    // check for 16
    private boolean check16() {
        boolean search_smaller_group = false;

        outer:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (A[i][j] == 1) {
                    search_smaller_group = false;
                } else {
                    // breaks outer loop and returns true to find smaller groups
                    search_smaller_group = true;
                    break outer;
                }
            }
        }
        return search_smaller_group;
    }

    // check for 8
    private boolean check8(int r, int c) {
        boolean search_smaller_group = true;
        String local = "";

        if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1 && A[(r + 1) % 4][0] == 1 && A[(r + 1) % 4][1] == 1
                && A[(r + 1) % 4][2] == 1 && A[(r + 1) % 4][3] == 1) { // rows ++
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][0] = 1;
            checked[r][1] = 1;
            checked[r][2] = 1;
            checked[r][3] = 1;
            checked[(r + 1) % 4][0] = 1;
            checked[(r + 1) % 4][1] = 1;
            checked[(r + 1) % 4][2] = 1;
            checked[(r + 1) % 4][3] = 1;
        } else if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1 && A[(4 + (r - 1)) % 4][0] == 1
                && A[(4 + (r - 1)) % 4][1] == 1 && A[(4 + (r - 1)) % 4][2] == 1 && A[(4 + (r - 1)) % 4][3] == 1) { // rows --
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][0] = 1;
            checked[r][1] = 1;
            checked[r][2] = 1;
            checked[r][3] = 1;
            checked[(4 + (r - 1)) % 4][0] = 1;
            checked[(4 + (r - 1)) % 4][1] = 1;
            checked[(4 + (r - 1)) % 4][2] = 1;
            checked[(4 + (r - 1)) % 4][3] = 1;
        } else if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1 && A[0][(c + 1) % 4] == 1 && A[1][(c + 1) % 4] == 1
                && A[2][(c + 1) % 4] == 1 && A[3][(c + 1) % 4] == 1) { // columns ++
            if (c == 0) {
                local = "C'";
            }
            if (c == 1) {
                local = "D";
            }
            if (c == 2) {
                local = "C";
            }
            if (c == 3) {
                local = "D'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[0][c] = 1;
            checked[1][c] = 1;
            checked[2][c] = 1;
            checked[3][c] = 1;
            checked[0][(c + 1) % 4] = 1;
            checked[1][(c + 1) % 4] = 1;
            checked[2][(c + 1) % 4] = 1;
            checked[3][(c + 1) % 4] = 1;
        } else if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1 && A[0][(4 + (c - 1)) % 4] == 1
                && A[1][(4 + (c - 1)) % 4] == 1 && A[2][(4 + (c - 1)) % 4] == 1 && A[3][(4 + (c - 1)) % 4] == 1) { // columns --
            if (c == 0) {
                local = "D'";
            }
            if (c == 1) {
                local = "C'";
            }
            if (c == 2) {
                local = "D";
            }
            if (c == 3) {
                local = "C";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked

            checked[0][c] = 1;
            checked[1][c] = 1;
            checked[2][c] = 1;
            checked[3][c] = 1;
            checked[0][(4 + (c - 1)) % 4] = 1;
            checked[1][(4 + (c - 1)) % 4] = 1;
            checked[2][(4 + (c - 1)) % 4] = 1;
            checked[3][(4 + (c - 1)) % 4] = 1;
        }
        return search_smaller_group;
    }

    // check for 4
    private boolean check4(int r, int c) {
        boolean search_smaller_group = true;
        String local = "";

        if (A[r][0] == 1 && A[r][1] == 1 && A[r][2] == 1 && A[r][3] == 1) { // row fours
            if (r == 0) {
                local = "A'B'";
            }
            if (r == 1) {
                local = "A'B";
            }
            if (r == 2) {
                local = "AB";
            }
            if (r == 3) {
                local = "AB'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][0] = 1;
            checked[r][1] = 1;
            checked[r][2] = 1;
            checked[r][3] = 1;
        } else if (A[0][c] == 1 && A[1][c] == 1 && A[2][c] == 1 && A[3][c] == 1) { // column fours
            if (c == 0) {
                local = "C'D'";
            }
            if (c == 1) {
                local = "C'D";
            }
            if (c == 2) {
                local = "CD";
            }
            if (c == 3) {
                local = "CD'";

            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[0][c] = 1;
            checked[1][c] = 1;
            checked[2][c] = 1;
            checked[3][c] = 1;
        } else if (A[r][c] == 1 && A[r][(c + 1) % 4] == 1 && A[(r + 1) % 4][c] == 1 && A[(r + 1) % 4][(c + 1) % 4] == 1) {
            // rows ++ & columns ++
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }
            if (c == 0) {
                local = local + "C'";
            }
            if (c == 1) {
                local = local + "D";
            }
            if (c == 2) {
                local = local + "C";
            }
            if (c == 3) {
                local = local + "D'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][c] = 1;
            checked[r][(c + 1) % 4] = 1;
            checked[(r + 1) % 4][c] = 1;
            checked[(r + 1) % 4][(c + 1) % 4] = 1;
        } else if (A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1 && A[(r + 1) % 4][(4 + (c - 1)) % 4] == 1 && A[(r + 1) % 4][c] == 1) {
            // rows ++ & columns --
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }
            if (c == 0) {
                local = local + "D'";
            }
            if (c == 1) {
                local = local + "C'";
            }
            if (c == 2) {
                local = local + "D";
            }
            if (c == 3) {
                local = local + "C'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][(4 + (c - 1)) % 4] = 1;
            checked[r][c] = 1;
            checked[(r + 1) % 4][(4 + (c - 1)) % 4] = 1;
            checked[(r + 1) % 4][c] = 1;

        } else if (A[(4 + (r - 1)) % 4][(4 + (c - 1)) % 4] == 1 && A[(4 + (r - 1)) % 4][c] == 1 && A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1) {
            // rows -- & columns --
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }
            if (c == 0) {
                local = local + "D'";
            }
            if (c == 1) {
                local = local + "C'";
            }
            if (c == 2) {
                local = local + "D";
            }
            if (c == 3) {
                local = local + "C'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[(4 + (r - 1)) % 4][(4 + (c - 1)) % 4] = 1;
            checked[(4 + (r - 1)) % 4][c] = 1;
            checked[r][(4 + (c - 1)) % 4] = 1;
            checked[r][c] = 1;
        } else if (A[(4 + (r - 1)) % 4][c] == 1 && A[(4 + (r - 1)) % 4][(c + 1) % 4] == 1 && A[r][c] == 1 && A[r][(c + 1) % 4] == 1) {
            // rows-- & columns++
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }
            if (c == 0) {
                local = local + "C'";
            }
            if (c == 1) {
                local = local + "D";
            }
            if (c == 2) {
                local = local + "C";
            }
            if (c == 3) {
                local = local + "D'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[(4 + (r - 1)) % 4][c] = 1;
            checked[(4 + (r - 1)) % 4][(c + 1) % 4] = 1;
            checked[r][c] = 1;
            checked[r][(c + 1) % 4] = 1;
        }
        return search_smaller_group;
    }

    // check for 2
    private boolean check2(int r, int c) {
        boolean search_smaller_group = true;
        String local = "";

        if (A[r][c] == 1 && A[r][(c + 1) % 4] == 1) { // columns ++
            if (r == 0) {
                local = "A'B'";
            }
            if (r == 1) {
                local = "A'B";
            }
            if (r == 2) {
                local = "AB";
            }
            if (r == 3) {
                local = "AB'";
            }
            if (c == 0) {
                local = local + "C'";
            }
            if (c == 1) {
                local = local + "D";
            }
            if (c == 2) {
                local = local + "C";
            }
            if (c == 3) {
                local = local + "D'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][c] = 1;
            checked[r][(c + 1) % 4] = 1;

        } else if (A[r][(4 + (c - 1)) % 4] == 1 && A[r][c] == 1) { // columns --
            if (r == 0) {
                local = "A'B'";
            }
            if (r == 1) {
                local = "A'B";
            }
            if (r == 2) {
                local = "AB";
            }
            if (r == 3) {
                local = "AB'";
            }
            if (c == 0) {
                local = local + "D'";
            }
            if (c == 1) {
                local = local + "C'";
            }
            if (c == 2) {
                local = local + "D";
            }
            if (c == 3) {
                local = local + "C";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][(4 + (c - 1)) % 4] = 1;
            checked[r][c] = 1;
        } else if (A[r][c] == 1 && A[(r + 1) % 4][c] == 1) { // rows ++
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "B";
            }
            if (r == 2) {
                local = "A";
            }
            if (r == 3) {
                local = "B'";
            }
            if (c == 0) {
                local = local + "C'D'";
            }
            if (c == 1) {
                local = local + "C'D";
            }
            if (c == 2) {
                local = local + "CD";
            }
            if (c == 3) {
                local = local + "CD'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][c] = 1;
            checked[(r + 1) % 4][c] = 1;
        } else if (A[r][c] == 1 && A[(4 + (r - 1)) % 4][c] == 1) { // rows --
            if (r == 0) {
                local = "B'";
            }
            if (r == 1) {
                local = "A'";
            }
            if (r == 2) {
                local = "B";
            }
            if (r == 3) {
                local = "A";
            }
            if (c == 0) {
                local = local + "C'D'";
            }
            if (c == 1) {
                local = local + "C'D";
            }
            if (c == 2) {
                local = local + "CD";
            }
            if (c == 3) {
                local = local + "CD'";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][c] = 1;
            checked[(4 + (r - 1)) % 4][c] = 1;
        }
        return search_smaller_group;
    }

    // no grouping
    private void nogrouping(int r, int c) {
        String local = "";
        if (r == 0) {
            local = "A'B'";
        }
        if (r == 1) {
            local = "A'B";
        }
        if (r == 2) {
            local = "AB";
        }
        if (r == 3) {
            local = "AB'";
        }
        if (c == 0) {
            local = local + "C'D'";
        }
        if (c == 1) {
            local = local + "C'D";
        }
        if (c == 2) {
            local = local + "CD";
        }
        if (c == 3) {
            local = local + "CD'";
        }

        if (output.matches("")) {
            output = output + local;
        } else {
            output = output + " + " + local;
        }

        checked[r][c] = 1;
    }

}
