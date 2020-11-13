package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String XS = "X";
    private final String OS = "O";
    private final int NUMBOXESPERROW = 3;
    private final int MIDDLECENTERINDEX = 4;

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
        Button[] boxes = {TopLeft, TopCenter, TopRight, MiddleLeft, MiddleCenter, MiddleRight, BottomLeft, BottomCenter, BottomRight};
        Button clickedButton = FindClickedButton(v, boxes);
        if(clickedButton != null && clickedButton.getText().equals("") ){
            MarkBox(clickedButton);
            if(hasWon(boxes)){
                //Declare a winner
                DisableBoxes(boxes);
                DisplayResults(currTurn + " Wins");
            }

            else if(AllBoxesFilled(boxes)){
                DisableBoxes(boxes);
                DisplayResults("It's a Tie");
            }

            else {
                SwapPlayers();
            }
        }
    }

    private boolean AllBoxesFilled(Button[] buttons) {
        for(Button b : buttons){
            if(b.getText() == ""){
                return false;
            }
        }
        return true;
    }

    private void DisableBoxes(Button[] boxes) {
        for (Button b: boxes) {
            b.setClickable(false);
        }
    }

    private void EnableBoxes(Button[] boxes) {
        for (Button b: boxes) {
            b.setClickable(true);
        }
    }

    private void DisplayResults(String message) {
        playerTurn.setText(message);
    }

    private Button FindClickedButton(View v, Button[] buttons){
        for (Button button : buttons) {
            if (v == button) {
                return button;
            }
        }
        return null;
    }

    private void MarkBox(Button button){
        button.setText(currTurn);
    }

    private void SwapPlayers(){
        if(currTurn.equals(XS)){
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
        if(currTurn.equals(OS)){
            SwapPlayers();
        }
        else{
            playerTurn.setText("Player " + currTurn + "'s Turn");
        }
        EnableBoxes(buttons);
    }
    public boolean hasWon(Button[] boxes){
        return ThreeInRow(boxes) ||
               ThreeInCol(boxes) ||
               ThreeInDia(boxes);
    }

    private boolean ThreeInDia(Button[] boxes) {
        return (boxes[MIDDLECENTERINDEX].getText().equals(currTurn) &&
                boxes[MIDDLECENTERINDEX - 4].getText().equals(currTurn) &&
                boxes[MIDDLECENTERINDEX + 4].getText().equals((currTurn))) ||
                (boxes[MIDDLECENTERINDEX].getText().equals(currTurn) &&
                boxes[MIDDLECENTERINDEX - 2].getText().equals(currTurn) &&
                boxes[MIDDLECENTERINDEX + 2].getText().equals(currTurn));
    }

    private boolean ThreeInCol(Button[] boxes) {
        for(int i = 0; i < NUMBOXESPERROW; i++){
            if(boxes[i].getText().equals(currTurn) &&
                    boxes[i + 3].getText().equals(currTurn) &&
                    boxes[i + 6].getText().equals((currTurn))) {
                return true;
            }
        }
        return false;
    }

    private boolean ThreeInRow(Button[] boxes) {
        for(int i = 0; i < boxes.length; i += 3){
            if(boxes[i].getText().equals(currTurn) &&
                    boxes[i + 1].getText().equals(currTurn) &&
                    boxes[i + 2].getText().equals((currTurn))) {
                return true;
            }
        }
        return false;
    }
}