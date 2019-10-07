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
public class TwVarSolver {

    private String output = "";
    private final int A[][] = new int[2][2];
    private final int checked[][] = new int[2][2];

    public TwVarSolver(int[] val) {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                A[i][j] = val[count++];
                checked[i][j] = 0;
            }
        }
    }

    public String solve() {
        if (!check4()) {
            // reaches if all values are 1
            output = "1";
        } else {
            // reaches if smaller groups are to be found rather than 4
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (A[i][j] == 1 && checked[i][j] == 0) {
                        if (check2(i, j)) {
                            nogrouping(i, j);
                        }

                    }
                }
            }
        }
        return output;
    }

    // check for 4
    private boolean check4() {
        boolean search_smaller_group = false;

        outer:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
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

    // check for 2
    private boolean check2(int r, int c) {
        boolean search_smaller_group = true;
        String local = "";

        if (A[r][c] == 1 && A[r][(c + 1) % 2] == 1) { // columns ++
            if (r == 0) {
                local = "A'";
            }
            if (r == 1) {
                local = "A";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][c] = 1;
            checked[r][(c + 1) % 2] = 1;
        } else if (A[r][c] == 1 && A[(r + 1) % 2][c] == 1) { // rows ++
            if (c == 0) {
                local = "B'";
            }
            if (c == 1) {
                local = "B";
            }

            if (output.matches("")) {
                output = output + local;
            } else {
                output = output + " + " + local;
            }

            search_smaller_group = false;
            // make checked
            checked[r][c] = 1;
            checked[(r + 1) % 2][c] = 1;
        }
        return search_smaller_group;
    }

    // no grouping
    private void nogrouping(int r, int c) {
        String local = "";

        if (r == 0) {
            local = "A'";
        }
        if (r == 1) {
            local = "A";
        }
        if (c == 0) {
            local = local + "B'";
        }
        if (c == 1) {
            local = local + "B";
        }

        if (output.matches("")) {
            output = output + local;
        } else {
            output = output + " + " + local;
        }

        checked[r][c] = 1;
    }
}
