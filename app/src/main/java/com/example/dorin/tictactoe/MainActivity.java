package com.example.dorin.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.GridLayout;
import android.widget.GridView;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Game game;
    ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9;

    // put all button names in a list
    String[] buttons = {"button1", "button2", "button3", "button4", "button5", "button6", "button7", "button8" , "button9"};

    TextView textView;
    private GridView gridView;


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
        textView.setText("Player one turn");

        game = new Game();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        textView.setText(inState.getString("text"));
        game.playerOneTurn = inState.getBoolean("player");
        b1.setTag(inState.getString("but1"));
        b2.setTag(inState.getString("but2"));
        b3.setTag(inState.getString("but3"));
        b4.setTag(inState.getString("but4"));
        b5.setTag(inState.getString("but5"));
        b6.setTag(inState.getString("but6"));
        b7.setTag(inState.getString("but7"));
        b8.setTag(inState.getString("but8"));
        b9.setTag(inState.getString("but9"));

        // for iterating over rows
        int ro = 0;
        // for iterating over columns
        int co = 0;
        // iterate over all buttons
        for (String part: buttons) {
            int id = getResources().getIdentifier(part, "id", getPackageName());
            ImageButton but = findViewById(id);
            // if button is blank
            if (but.getTag() == "0") {
                but.setImageResource(0);
                game.board[ro % 3][co] = TileState.BLANK;
            // if button is a cross
            }
            else if (but.getTag() == "1") {
                but.setImageResource(R.drawable.cross);
                game.board[ro % 3][co] = TileState.CROSS;
            // if button is a circle
            }
            else if (but.getTag() == "2") {
                but.setImageResource(R.drawable.circle);
                game.board[ro % 3][co] = TileState.CIRCLE;
            }
            // only increase column when row is 2
            if (ro == 2) {
                co++;
            }
            // ro is always modulo 3
            ro++;
        }
    }

        public void tileClicked(View view) {

        if (game.won() == GameState.IN_PROGRESS) {
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
                textView.setText("Player one turn");
            }
            else {
                textView.setText("Player two turn");
            }
        }
        if (game.won() == GameState.PLAYER_ONE) {
            textView.setText("Player one won!");
        }
        else if (game.won() == GameState.PLAYER_TWO) {
            textView.setText("Player two won!");
        }
        else if (game.won() == GameState.DRAW) {
            textView.setText("Draw!");
        }
    }

    public void resetClicked(View view) {

        // iterate over all buttons
        for (String part: buttons) {
            int id = getResources().getIdentifier(part, "id", getPackageName());
            ImageButton but = findViewById(id);
            but.setImageResource(0);
            but.setTag(0);
        }
        textView.setText("Player one turn");
        game = new Game();
    }
}
