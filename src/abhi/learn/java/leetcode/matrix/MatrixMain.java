package abhi.learn.java.leetcode.matrix;

import java.util.*;

public class MatrixMain {

    public static void main(String[] args) {
        System.out.println("START");
        long startTime = System.currentTimeMillis();

        MatrixMain main = new MatrixMain();

        int[][] input = new int[][]{{0,2147483647,-1,2147483647,2147483647,-1,-1,0,0,-1,2147483647,2147483647,0,-1,2147483647,2147483647,2147483647,2147483647,0,2147483647,0,-1,-1,-1,-1,2147483647,-1,-1,2147483647,2147483647,-1,-1,0,0,-1,0,0,0,2147483647,0,2147483647,-1,-1,0,-1,0,0,0,2147483647},{2147483647,0,-1,2147483647,0,-1,-1,-1,-1,0,0,2147483647,2147483647,-1,-1,2147483647,-1,-1,2147483647,2147483647,-1,0,-1,2147483647,0,2147483647,-1,2147483647,0,2147483647,0,2147483647,-1,2147483647,0,2147483647,-1,2147483647,0,2147483647,2147483647,0,-1,2147483647,-1,-1,-1,0,2147483647}};
        main.wallsAndGates(input);

        System.out.println("output = " + input);
//        System.out.println("output = " + output);
        System.out.println("Time Taken=" + (System.currentTimeMillis() - startTime));
        System.out.println("END");
    }

    /// https://leetcode.com/problems/walls-and-gates/description/
    public void wallsAndGates(int[][] rooms) { // TODO
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0){
                    /// call the helper
                    wallsAndGatesHelper(rooms, i, j, 0);
                }
            }
        }
    }

    private void wallsAndGatesHelper(int[][] rooms, int i, int j, int value) {
        if (i >= rooms.length || i < 0 || j >= rooms[0].length || j < 0 )
            return;
        if (rooms[i][j] < value) return;
        else rooms[i][j] = value;

        wallsAndGatesHelper(rooms, i, j+1, value+1);
        wallsAndGatesHelper(rooms, i+1, j, value+1);
        wallsAndGatesHelper(rooms, i, j-1, value+1);
        wallsAndGatesHelper(rooms, i-1, j, value+1);
    }



    /// https://leetcode.com/problems/surrounded-regions/description/
    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int j = 0; j < board[0].length; j++) { /// first n last row
            solveDFS(board, 0, j, visited);
            solveDFS(board, board.length-1, j, visited);
        }

        for (int i = 0; i < board.length; i++){
            solveDFS(board, i, 0, visited);
            solveDFS(board, i, board[0].length-1, visited);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (visited[i][j])
                    board[i][j] = 'O';
            }
        }
    }

    private void solveDFS(char[][] board, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X')
            return;

        board[i][j] = 'X';
        visited[i][j] = true;
        solveDFS(board, i, j+1, visited);
        solveDFS(board, i+1, j, visited);
        solveDFS(board, i, j-1, visited);
        solveDFS(board, i-1, j, visited);
    }


    /// https://leetcode.com/problems/island-perimeter/description/
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    if (j-1 < 0 || grid[i][j-1] == 0) perimeter++;
                    if (i-1 < 0 || grid[i-1][j] == 0) perimeter++;
                    if (j+1 >= grid[0].length || grid[i][j+1] == 0) perimeter++;
                    if (i+1 >= grid.length || grid[i+1][j] == 0) perimeter++;
                }
            }
        }
        return perimeter;
    }


    /// https://leetcode.com/problems/flipping-an-image/description/
    public int[][] flipAndInvertImage(int[][] image) {

        for (int i = 0; i < image.length; i++) {
            int j = 0, k = image[i].length-1;
            while ( j <= k){
                int temp = image[i][j];
                image[i][j] = image[i][k] == 0 ? 1 : 0;
                image[i][k] = temp == 0 ? 1 : 0;
                j++; k--;
            }
        }
        return image;
    }

    /// https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
    public String tictactoe(int[][] moves) {
        int[][] board = new int[3][3];

        for (int i = 0; i < moves.length; i++) {
            if (i%2 == 0){
                board[ moves[i][0]] [moves[i][1]] = 1;
            }else {
                board[ moves[i][0]] [moves[i][1]] = 2;
            }
        }
        int checkSum = 0;
        for (int i = 0; i < 3; i++) {
            checkSum = board[i][0] * board[i][1] * board[i][2];
            if (checkSum == 1) return "A";
            if (checkSum == 8) return "B";

            checkSum = board[0][i] * board[1][i] * board[2][i];
            if (checkSum == 1) return "A";
            if (checkSum == 8) return "B";
        }

        checkSum = board[0][0] * board[1][1] * board[2][2];
        if (checkSum == 1) return "A";
        if (checkSum == 8) return "B";

        checkSum = board[0][2] * board[1][1] * board[2][0];
        if (checkSum == 1) return "A";
        if (checkSum == 8) return "B";

        if (moves.length < 9) return "Pending";
        return "Draw";
    }


    /// https://leetcode.com/problems/path-with-maximum-gold/description/
    public int getMaximumGold(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[] sum = new int[]{0};
        int[] maximum = new int[]{0};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                else {
                    getMaximumGoldHelper(grid, visited, sum, maximum, i, j);
                }
            }
        }

        return maximum[0];
    }

    public void getMaximumGoldHelper(int[][] grid, boolean[][] visited, int[] sum, int[] maximum, int row, int col) {
        if (row >= grid.length || col >= grid[0].length) return;
        if (visited[row][col]) return;
        if (grid[row][col] == 0) return;

        visited[row][col] = true;

        sum[0] += grid[row][col];
        if (sum[0] > maximum[0])
            maximum[0] = sum[0];

        getMaximumGoldHelper(grid, visited, sum, maximum, row-1, col);
        getMaximumGoldHelper(grid, visited, sum, maximum, row, col-1);
        getMaximumGoldHelper(grid, visited, sum, maximum, row+1, col);
        getMaximumGoldHelper(grid, visited, sum, maximum, row, col+1);

        visited[row][col] = false;
        sum[0] -= grid[row][col];

    }

    /// https://leetcode.com/problems/number-of-laser-beams-in-a-bank/description/
    public int numberOfBeams(String[] bank) {
        int sum = 0; int multiplier = 0;
        int cntR1 = 0;
        for (String floor: bank) {
            for (int i = 0; i < floor.length(); i++) {
                if ('1' == floor.charAt(i)) cntR1++;
            }
            if (cntR1 > 0) {
                sum = sum + (multiplier * cntR1);
                multiplier = cntR1;
                cntR1 = 0;
            }
        }
        return sum;
    }


    /// https://leetcode.com/problems/prime-in-diagonal/
    public int diagonalPrime(int[][] nums) {
        int maxNum = 0;
        if (nums == null || nums.length != nums[0].length) return maxNum;
        int len = nums.length;
        Set<Integer> primeNos = new HashSet<>();
        Set<Integer> notPrimeNos = new HashSet<>();
        notPrimeNos.add(1);
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i][i];
            int num2 = nums[i][len-1-i];
            if (isPrime(num1, primeNos, notPrimeNos)) {
                maxNum = Math.max(maxNum, num1);
            }
            if (isPrime(num2, primeNos, notPrimeNos))
                maxNum = Math.max(maxNum, num2);
        }
        return maxNum;
    }

    private boolean isPrime(int num, Set<Integer> primeNos, Set<Integer> notPrimeNos){
        if (primeNos.contains(num)) return true;
        if (notPrimeNos.contains(num)) return false;

        for (int i = 2; i <= num/2; i++) {
            if (num% i == 0) {notPrimeNos.add(num); return false;}
        }
        primeNos.add(num);
        return true;
    }

    /// https://leetcode.com/problems/valid-word-square/description/
    public boolean validWordSquare(List<String> words) {
        int rows = words.size();
        for (int i = 0; i < rows; i++) {
            String word = words.get(i);
            if (word.length() > rows) return false;
            for (int j = 0; j < word.length(); j++) {
                if (words.size() > j && words.get(j) != null && words.get(j).length() > i && words.get(j).charAt(i) == word.charAt(j) )
                    continue;
                else return false;
            }
        }
        return true;
    }

    /// https://leetcode.com/problems/image-smoother/
    public int[][] imageSmoother(int[][] img) {
        int[][] result = new int[img.length][img[0].length];

        int len = img.length; int height = img[0].length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < height; j++) {
                int sum = 0; int cnt = 0;

                for (int k = i-1; k <= i+1; k++) {
                    for (int l = j-1; l <= j+1; l++) {

                        if (k < 0 || k >= len || l < 0 || l >= height)
                            continue;
                        sum += img[k][l]; cnt++;
                    }
                }
                result[i][j] = sum/cnt;

            }
        }
        return result;
    }

}
