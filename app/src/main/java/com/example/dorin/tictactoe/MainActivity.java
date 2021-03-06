package com.example.dorin.tictactoe;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Game game;
    // all the 'cells' of the board
    ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    TextView textView;
    ImageButton computerButton;

    // put all button names in a list
    String[] buttons = {"button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8" , "button9"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);

        textView = findViewById(R.id.textView);
        textView.setText("You're turn");

        game = new Game();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save variables for rotating mobile
        outState.putString("but1", b1.getTag().toString());
        outState.putString("but2", b2.getTag().toString());
        outState.putString("but3", b3.getTag().toString());
        outState.putString("but4", b4.getTag().toString());
        outState.putString("but5", b5.getTag().toString());
        outState.putString("but6", b6.getTag().toString());
        outState.putString("but7", b7.getTag().toString());
        outState.putString("but8", b8.getTag().toString());
        outState.putString("but9", b9.getTag().toString());
        outState.putString("text", textView.getText().toString());
        outState.putBoolean("player", game.playerOneTurn);
        outState.putInt("movesplayed", game.movesPlayed);
        // save TileState for every button
        outState.putString("board1", game.getBoard(0, 0));
        outState.putString("board2", game.getBoard(1, 0));
        outState.putString("board3", game.getBoard(2, 0));
        outState.putString("board4", game.getBoard(0, 1));
        outState.putString("board5", game.getBoard(1, 1));
        outState.putString("board6", game.getBoard(2, 1));
        outState.putString("board7", game.getBoard(0, 2));
        outState.putString("board8", game.getBoard(1, 2));
        outState.putString("board9", game.getBoard(2, 2));
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        // restore variables after rotating mobile
        textView.setText(inState.getString("text"));
        game.playerOneTurn = inState.getBoolean("player");
        game.movesPlayed = inState.getInt("movesplayed");
        b1.setTag(inState.getString("but1"));
        b2.setTag(inState.getString("but2"));
        b3.setTag(inState.getString("but3"));
        b4.setTag(inState.getString("but4"));
        b5.setTag(inState.getString("but5"));
        b6.setTag(inState.getString("but6"));
        b7.setTag(inState.getString("but7"));
        b8.setTag(inState.getString("but8"));
        b9.setTag(inState.getString("but9"));
        // restore TileState for every button
        game.setBoard(0, 0, inState.getString("board1"));
        game.setBoard(1, 0, inState.getString("board2"));
        game.setBoard(2, 0, inState.getString("board3"));
        game.setBoard(0, 1, inState.getString("board4"));
        game.setBoard(1, 1, inState.getString("board5"));
        game.setBoard(2, 1, inState.getString("board6"));
        game.setBoard(0, 2, inState.getString("board7"));
        game.setBoard(1, 2, inState.getString("board8"));
        game.setBoard(2, 2, inState.getString("board9"));

        // iterate over all buttons to set images
        for (String part: buttons) {
            int id = getResources().getIdentifier(part, "id", getPackageName());
            ImageButton but = findViewById(id);
            // if button is blank
            if (but.getTag() == "0") {
                but.setImageResource(0);
            // if button is a cross
            } else if (but.getTag() == "1") {
                but.setImageResource(R.drawable.cross);
            // if button is a circle
            } else if (but.getTag() == "2") {
                but.setImageResource(R.drawable.circle);
            }
        }
    }

    public void tileClicked(View view) {
        // if player one on turn
        if (game.won() == GameState.IN_PROGRESS && game.playerOneTurn) {

            // save button where is clicked on
            ImageButton button = (ImageButton) view;
            // get row and column of button in grid
            int row = 0;
            int column = 0;

            switch(button.getId()) {
                case R.id.button1:
                    row = 0;
                    column = 0;
                    break;
                case R.id.button2:
                    row = 1;
                    column = 0;
                    break;
                case R.id.button3:
                    row = 2;
                    column = 0;
                    break;
                case R.id.button4:
                    row = 0;
                    column = 1;
                    break;
                case R.id.button5:
                    row = 1;
                    column = 1;
                    break;
                case R.id.button6:
                    row = 2;
                    column = 1;
                    break;
                case R.id.button7:
                    row = 0;
                    column = 2;
                    break;
                case R.id.button8:
                    row = 1;
                    column = 2;
                    break;
                case R.id.button9:
                    row = 2;
                    column = 2;
                    break;
            }

            // call for tilestate method, return tilestate
            TileState state = game.choose(row, column);

            // change image on button
            switch (state) {
                case CROSS:
                    // set cross in button
                    button.setImageResource(R.drawable.cross);
                    button.setTag(1);
                    break;
                case CIRCLE:
                    // set circle in button
                    button.setImageResource(R.drawable.circle);
                    button.setTag(2);
                    break;
                case INVALID:
                    // invalid buttonclick
                    break;
            }
        }
        if (game.won() == GameState.IN_PROGRESS) {
            if (game.playerOnTurn()) {
                textView.setText("You're turn");
            }
            else {
                textView.setText("Computers turn");
                int row = 0;
                int column = 0;
                String check;
                Boolean checked = false;
                while (checked == false) {
                    Random r = new Random();
                    // 4 exclusive and 1 inclusive for random number of column and row
                    row = r.nextInt((3 - 1) + 1);
                    column = r.nextInt((3 - 1) + 1);
                    // check if cell is empty, else take another random number
                    check = game.check(row, column);
                    if (check == "true") {
                        checked = true;
                    }
                }
                // fill in computers turn
                TileState state = game.fillin(row, column);
                computerButton = findViewById(R.id.button1);
                // look for button which computer has choose
                if (row == 0 && column == 0) {
                    computerButton = findViewById(R.id.button1);
                }
                else if (row == 1 && column == 0) {
                    computerButton = findViewById(R.id.button2);
                }
                else if (row == 2 && column == 0) {
                    computerButton = findViewById(R.id.button3);
                }
                else if (row == 0 && column == 1) {
                    computerButton = findViewById(R.id.button4);
                }
                else if (row == 1 && column == 1) {
                    computerButton = findViewById(R.id.button5);
                }
                else if (row == 2 && column == 1) {
                    computerButton = findViewById(R.id.button6);
                }
                else if (row == 0 && column == 2) {
                    computerButton = findViewById(R.id.button7);
                }
                else if (row == 1 && column == 2) {
                    computerButton = findViewById(R.id.button8);
                }
                else if (row == 2 && column == 2) {
                    computerButton = findViewById(R.id.button9);
                }
                // wait 3 seconds for 'thinking' for the computer
                new CountDownTimer(3000, 1000) {
                    public void onTick(long millisUntilFinished) {
                       // every second nothing happen
                    }
                    public void onFinish() {
                        // set imageButton to circle for computer and it's users turn
                        computerButton.setImageResource(R.drawable.circle);
                        computerButton.setTag(2);
                        textView.setText("You're turn");
                    }
                }.start();
            }
        }
        // when it is computers turn, wait 3 seconds for thinking of computer has won
        if (game.playerOnTurn()) {
            // wait 3 seconds for 'thinking' for the computer
            new CountDownTimer(3000, 1000) {
                public void onTick(long millisUntilFinished) {
                    // every second nothing happen
                }
                public void onFinish() {
                    // check if game is won
                    winnerCheck();
                }
            }.start();
        }
        else {
            winnerCheck();
        }
    }

    // method for reset the game
    public void resetClicked(View view) {
        // iterate over all buttons
        for (String part: buttons) {
            int id = getResources().getIdentifier(part, "id", getPackageName());
            ImageButton but = findViewById(id);
            // reset image for every button
            but.setImageResource(0);
            // reset tag for every button
            but.setTag(0);
        }
        textView.setText("Player one turn");
        game = new Game();
    }

    public void winnerCheck() {
        // check if game is won
        if (game.won() == GameState.PLAYER_ONE) {
            textView.setText("You won!");
        }
        else if (game.won() == GameState.PLAYER_TWO) {
            textView.setText("You lose!");
        }
        else if (game.won() == GameState.DRAW) {
            textView.setText("Draw!");
        }
    }
}
