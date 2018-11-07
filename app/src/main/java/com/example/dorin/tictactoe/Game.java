package com.example.dorin.tictactoe;

public class Game {

    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    private Boolean gameOver;

    public Game() {
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++) {
            for(int j=0; j<BOARD_SIZE; j++) {
                board[i][j] = TileState.BLANK;
            }
        }
        playerOneTurn = true;
        gameOver = false;
    }

    public GameState won() {

        for (int i = 0; i < 3; i++) {
            // checking rows
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != TileState.BLANK) {
                if (board[i][0] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
                else {
                    return GameState.PLAYER_TWO;
                }
            }
            // checking columns
            else if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != TileState.BLANK) {
                if (board[0][i] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
                else {
                    return GameState.PLAYER_TWO;
                }
            }
            // checking diagonal(up-down)
            else if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != TileState.BLANK) {
                if (board[0][0] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
                else {
                    return GameState.PLAYER_TWO;
                }
            }
            // checking diagonal(down-up)
            else if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != TileState.BLANK) {
                if (board[0][2] == TileState.CROSS) {
                    return GameState.PLAYER_ONE;
                }
                else {
                    return GameState.PLAYER_TWO;
                }
            }
            else if (movesPlayed == 9) {
                return GameState.DRAW;
            }
        }
        return GameState.IN_PROGRESS;
    }

    public TileState choose(int row, int column) {
        if (board[row][column] == TileState.BLANK) {
            if (playerOneTurn) {
                board[row][column] = TileState.CROSS;
                movesPlayed++;
                // turn to other player
                playerOneTurn = false;
                return TileState.CROSS;
            }
            else if (!playerOneTurn) {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
                movesPlayed++;
                return TileState.CIRCLE;
            }
        }
        return TileState.INVALID;
    }

    public Boolean playerOnTurn() {
        return playerOneTurn;
    }

}
