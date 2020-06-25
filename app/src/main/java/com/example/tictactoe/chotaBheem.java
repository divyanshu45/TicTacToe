package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class chotaBheem extends AppCompatActivity {

    TextView txtMess;
    TextView txtMess2, msg;
    Button btnPlayAgain;
    int player = 0;
    int [] boardState = {2,2,2,2,2,2,2,2,2};
    int[][] positionWinnings = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean isContinue = true;
    int bheem = 0;
    int raju = 0;

    public void playAgain(View view){
        player = 0;
        for(int i=0;i<boardState.length;i++){
            boardState[i] = 2;
        }
        isContinue = true;
        GridLayout gridLayout = findViewById(R.id.Board);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        msg.setText("");
    }
    public void dropIn(View view){
        ImageView board = (ImageView) view;
        board.setTranslationY(-1000f);
        int position = Integer.parseInt(board.getTag().toString());
        if(boardState[position] == 2 && isContinue) {
            board.setTranslationY(-1000f);
            boardState[position] = player;
            if (player == 0) {
                board.setImageResource(R.drawable.bheem);
                player = 1;
            } else {
                board.setImageResource(R.drawable.raju);
                player = 0;
            }
            board.animate().translationYBy(1000f).rotationBy(360).setDuration(300);

            for(int[] positionWinning : positionWinnings){
                if(boardState[positionWinning[0]] == boardState[positionWinning[1]] && boardState[positionWinning[1]] == boardState[positionWinning[2]] && boardState[positionWinning[0]]!=2){
                    isContinue = false;
                    String winner;
                    if(boardState[positionWinning[0]] == 0){
                        winner = "BHEEM";
                        bheem++;
                        txtMess.setText("BHEEM : " + bheem);
                    }else {
                        winner = "RAJU";
                        raju++;
                        txtMess2.setText("RAJU : " + raju);
                    }
                    msg.setText(winner + " WINS");
                    break;
                } else {
                    boolean isOver = true;
                    for(int countState : boardState){
                        if(countState == 2){
                            isOver = false;
                            break;
                        }
                    }
                    if(isOver){
                        msg.setText("IT'S A TIE");
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chota_bheem);
        txtMess = findViewById(R.id.txtMess);
        txtMess2 = findViewById(R.id.txtMess2);
        btnPlayAgain = findViewById(R.id.restart);
        msg = findViewById(R.id.msg);
    }
}