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

    public void onSaveInStanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("button1", b1.getImageAlpha());
        outState.putInt("button2", b2.getId());
        outState.putInt("button3", b3.getId());
        outState.putInt("button4", b4.getId());
        outState.putInt("button5", b5.getId());
        outState.putInt("button6", b6.getId());
        outState.putInt("button7", b7.getId());
        outState.putInt("button8", b8.getId());
        outState.putInt("button9", b9.getId());
    }



    public void tileClicked(View view) {

        if (game.won() == GameState.IN_PROGRESS) {
            ImageButton button = (ImageButton) view;
            // get row and column of button in grid
            int row = 0;
            int column = 0;

            if (button.getId() == b1.getId()) {
                row = 0;
                column = 0;
            } else if (button.getId() == b2.getId()) {
                row = 1;
                column = 0;
            } else if (button.getId() == b3.getId()) {
                row = 2;
                column = 0;
            } else if (button.getId() == b4.getId()) {
                row = 0;
                column = 1;
            } else if (button.getId() == b5.getId()) {
                row = 1;
                column = 1;
            } else if (button.getId() == b6.getId()) {
                row = 2;
                column = 1;
            } else if (button.getId() == b7.getId()) {
                row = 0;
                column = 2;
            } else if (button.getId() == b8.getId()) {
                row = 1;
                column = 2;
            } else if (button.getId() == b9.getId()) {
                row = 2;
                column = 2;
            }


            // call for tilestate method, return tilestate
            TileState state = game.choose(row, column);

            // change text on button
            switch (state) {
                case CROSS:
                    // set cross in button
                    button.setImageResource(R.drawable.cross);
                    break;
                case CIRCLE:
                    // set circle in button
                    button.setImageResource(R.drawable.circle);
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
        b1.setImageResource(0);
        b2.setImageResource(0);
        b3.setImageResource(0);
        b4.setImageResource(0);
        b5.setImageResource(0);
        b6.setImageResource(0);
        b7.setImageResource(0);
        b8.setImageResource(0);
        b9.setImageResource(0);
        textView.setText("Player one turn");

        game = new Game();

    }
}
