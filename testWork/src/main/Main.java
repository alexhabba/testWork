package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static int M, N;
    private static int[][] board;

    static {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Write number M");
            M = Integer.parseInt(reader.readLine());
            System.out.println("Write number N");
            N = Integer.parseInt(reader.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
        board = new int[M][N];
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                board[y][x] = (int) (Math.random() + 0.5);
            }
        }
    }

    public static void main(String[] args) {

        while (true) {
            printCurrentState();
            checkLiveNeighbor();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printCurrentState() {
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (board[y][x] == 1) {
                    System.out.print("\u001B[32m" + 1 + "\u001B[0m" + "  ");
                } else {
                    System.out.print("\u001B[31m" + 0 + "\u001B[0m" + "  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void checkLiveNeighbor() {
        int boardClone[][] = new int[M][N];
        int count = 0;
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                count = checkLiveNeighborCount(x, y);
                if (board[y][x] == 1 && count < 2) boardClone[y][x] = 0;
                if (board[y][x] == 1 && (count == 2 || count == 3)) boardClone[y][x] = 1;
                if (board[y][x] == 1 && count > 3) boardClone[y][x] = 0;
                if (board[y][x] == 0 && count == 3) boardClone[y][x] = 1;
            }
        }
        board = boardClone;
    }

    private static int checkLiveNeighborCount(int x, int y) {
        int count = 0;

        try {
            if (board[y][x + 1] == 1) count++;
        } catch (Exception e) {
        }
        try {
            if (board[y + 1][x + 1] == 1) count++;
        } catch (Exception e) {
        }
        try {
            if (board[y - 1][x + 1] == 1) count++;
        } catch (Exception e) {
        }
        try {
            if (board[y][x - 1] == 1) count++;
        } catch (Exception e) {
        }
        try {
            if (board[y + 1][x - 1] == 1) count++;
        } catch (Exception e) {
        }
        try {
            if (board[y - 1][x - 1] == 1) count++;
        } catch (Exception e) {
        }
        try {
            if (board[y + 1][x] == 1) count++;
        } catch (Exception e) {
        }
        try {
            if (board[y - 1][x] == 1) count++;
        } catch (Exception e) {
        }

        return count;
    }
}