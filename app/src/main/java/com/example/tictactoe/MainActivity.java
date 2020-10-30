package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String XS = "X";
    private final String OS = "O";

    private String currTurn;
    private TextView playerTurn;

    private Button TopLeft;
    private Button TopCenter;
    private Button TopRight;
    private Button MiddleLeft;
    private Button MiddleCenter;
    private Button MiddleRight;
    private Button BottomLeft;
    private Button BottomCenter;
    private Button BottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TopLeft = findViewById(R.id.TopLeftButton);
        TopCenter = findViewById(R.id.TopCenterButton);
        TopRight = findViewById(R.id.TopRightButton);
        MiddleLeft = findViewById(R.id.MiddleLeftButton);
        MiddleCenter = findViewById(R.id.MiddleCenterButton);
        MiddleRight = findViewById(R.id.MiddleRightButton);
        BottomLeft = findViewById(R.id.BottomLeftButton);
        BottomCenter = findViewById(R.id.BottomCenterButton);
        BottomRight = findViewById(R.id.BottomRightButton);

        currTurn = XS;

        playerTurn = findViewById(R.id.PlayerTurnText);
        playerTurn.setText("Player " + currTurn + "'s Turn");

    }

    public void OnClick(View v){
        Button[] buttons = {TopLeft, TopCenter, TopRight, MiddleLeft, MiddleCenter, MiddleRight, BottomLeft, BottomCenter, BottomRight};
        Button clickedButton = FindClickedButton(v, buttons);
        if(clickedButton != null && clickedButton.getText().equals("")){
            MarkBox(clickedButton);
            SwapPlayers();
        }
    }

    private Button FindClickedButton(View v, Button[] buttons){
        for(int i = 0; i < buttons.length; i++) {
            if(v == buttons[i]) {
                return buttons[i];
            }
        }
        return null;
    }

    private void MarkBox(Button button){
        button.setText(currTurn);
    }

    private void SwapPlayers(){
        if(currTurn == XS){
            currTurn = OS;
        }
        else{
            currTurn = XS;
        }
        playerTurn.setText("Player " + currTurn + "'s Turn");
    }

    public void ResetGame(View v){
        Button[] buttons = {TopLeft, TopCenter, TopRight, MiddleLeft, MiddleCenter, MiddleRight, BottomLeft, BottomCenter, BottomRight};
        for (Button b: buttons) {
            b.setText("");
        }
        if(currTurn == OS){
            SwapPlayers();
        }
    }
}