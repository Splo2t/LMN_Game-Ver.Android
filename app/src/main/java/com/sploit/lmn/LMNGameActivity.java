package com.sploit.lmn;

import android.content.Intent;
import android.content.ClipData;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


class Coordinate{
    static int x;
    static int y;
    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    String getCoordinate(){
        return String.valueOf(x) + String.valueOf(y);
    }
}


public class LMNGameActivity extends AppCompatActivity {

    String gameState;

    long lastTimeBack;
    LMNMap lmnMap = new LMNMap();
    CheckL checkL = new CheckL(lmnMap,'l');
    CheckM checkM = new CheckM(lmnMap,'m');
    CheckN checkN = new CheckN(lmnMap,'n');
    CheckEnd endMap = new CheckEnd(lmnMap,checkL,checkM,checkN);

    ImageView[][] mapImageView;
    Button btnL;
    Button btnM;
    Button btnN;
    EditText edit;
    EditText gameModeLable;

    Ai gameAi;

    LMNonDragLisListener[] LMNDragListener= new LMNonDragLisListener[16] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmngame);

        initialize();
        View.OnLongClickListener LLongClockListener = new LMNOnLongClickListener("l");
        btnL.setOnLongClickListener(LLongClockListener);

        View.OnLongClickListener MLongClockListener = new LMNOnLongClickListener("m");
        btnN.setOnLongClickListener(MLongClockListener);

        View.OnLongClickListener NLongClockListener = new LMNOnLongClickListener("n");
        btnM.setOnLongClickListener(NLongClockListener);

    }

    void initialize(){
        btnL = (Button)findViewById(R.id.btnL);
        btnN = (Button)findViewById(R.id.btnM);
        btnM = (Button)findViewById(R.id.btnN);
        gameModeLable = (EditText)findViewById(R.id.gameModeLable);
        edit = (EditText)findViewById(R.id.editText);
        edit.setText(lmnMap.getTrun());
        mapImageView = resetMapImageView();

        Intent intent = getIntent();
        gameState = intent.getStringExtra("mode");
        switch(gameState) {
            case "SINGLE":
                gameModeLable.setText("Single");
                gameAi = new Ai(lmnMap, checkL, checkM, checkN);
                break;

            case "MULTI":
                gameModeLable.setText("Multi");break;

        }
    }


    ImageView[][] resetMapImageView(){
        ImageView[][] returnMap = new ImageView[4][4];

        returnMap[0][0] = (ImageView)findViewById(R.id.coordinate11);
        returnMap[0][1] = (ImageView)findViewById(R.id.coordinate12);
        returnMap[0][2] = (ImageView)findViewById(R.id.coordinate13);
        returnMap[0][3] = (ImageView)findViewById(R.id.coordinate14);

        returnMap[1][0] = (ImageView)findViewById(R.id.coordinate21);
        returnMap[1][1] = (ImageView)findViewById(R.id.coordinate22);
        returnMap[1][2] = (ImageView)findViewById(R.id.coordinate23);
        returnMap[1][3] = (ImageView)findViewById(R.id.coordinate24);

        returnMap[2][0] = (ImageView)findViewById(R.id.coordinate31);
        returnMap[2][1] = (ImageView)findViewById(R.id.coordinate32);
        returnMap[2][2] = (ImageView)findViewById(R.id.coordinate33);
        returnMap[2][3] = (ImageView)findViewById(R.id.coordinate34);

        returnMap[3][0] = (ImageView)findViewById(R.id.coordinate41);
        returnMap[3][1] = (ImageView)findViewById(R.id.coordinate42);
        returnMap[3][2] = (ImageView)findViewById(R.id.coordinate43);
        returnMap[3][3] = (ImageView)findViewById(R.id.coordinate44);


        int count = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Coordinate Crd = new Coordinate(i,j);
                returnMap[i][j].setTag(Crd.getCoordinate());
                LMNDragListener[count] = new LMNonDragLisListener();
                returnMap[i][j].setOnDragListener(LMNDragListener[count++]);
            }
        }

        return returnMap;

    }
    class LMNOnLongClickListener implements View.OnLongClickListener{
        String lmn;
        LMNOnLongClickListener(String m){
            this.lmn = m;
        }
        @Override
        public boolean onLongClick(View v) {
            ClipData LMNData = ClipData.newPlainText("LMN", lmn);
            v.startDrag(LMNData, new View.DragShadowBuilder(v),null,0);
            return false;
        }
    }

    class  LMNonDragLisListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_STARTED:
                    return true;
                case DragEvent.ACTION_DROP:
                    String coordinate = (String)v.getTag();
                    int x = coordinate.charAt(0)-'0';
                    int y = coordinate.charAt(1)-'0';
                    char LMN =event.getClipData().getItemAt(0).getText().toString().charAt(0);

                    switch (LMN){
                        case 'l':
                            if(checkL.setLMN(x,y)) {
                                v.setBackgroundResource(R.drawable.l_button) ;
                                lmnMap.addSeq();
                            }
                            break;
                        case 'm':
                            if(checkM.setLMN(x,y)) {
                                v.setBackgroundResource(R.drawable.m_button) ;
                                lmnMap.addSeq();
                            }
                            break;
                        case 'n':
                            if(checkN.setLMN(x,y)) {
                                v.setBackgroundResource(R.drawable.n_button) ;
                                lmnMap.addSeq();
                            }
                            break;
                    }
                    return true;

                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;

                case DragEvent.ACTION_DRAG_ENDED:
                   dragEnd();
                    return true;


            }
            return false;


        }
    }

    void dragEnd(){
        if(endMap.checkend()){
            //      lmnMap.seq--;
            edit.setText("GAME END");
            gameModeLable.setText(lmnMap.getTrun() + " Lose...");
        } else if(gameState.equals("MULTI")) {
            edit.setText(lmnMap.getTrun() + " TURN");
        } else if(gameState.equals("SINGLE")){

            if(lmnMap.getTrun().equals("P2")) {
                String returnCoordinate = gameAi.setLMNCard();
                switch (returnCoordinate.charAt(0)) {
                    case 'l':
                        if(checkL.setLMN(returnCoordinate.charAt(1) - '0', returnCoordinate.charAt(2) - '0')) {
                            mapImageView[returnCoordinate.charAt(1) - '0'][(returnCoordinate.charAt(2) - '0')].setBackgroundResource(R.drawable.l_button);
                        }
                        break;
                    case 'm':
                        if(checkM.setLMN(returnCoordinate.charAt(1) - '0', returnCoordinate.charAt(2) - '0')) {
                            mapImageView[returnCoordinate.charAt(1) - '0'][(returnCoordinate.charAt(2) - '0')].setBackgroundResource(R.drawable.m_button);
                        }

                        break;
                    case 'n':
                        if(checkN.setLMN(returnCoordinate.charAt(1) - '0', returnCoordinate.charAt(2) - '0')) {
                            mapImageView[returnCoordinate.charAt(1) - '0'][(returnCoordinate.charAt(2) - '0')].setBackgroundResource(R.drawable.n_button);
                        }
                        break;
                }
                edit.setText(lmnMap.getTrun() + "Turn");
                lmnMap.addSeq();
                dragEnd();
            }
        }
    }
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBack < 1500){
            finish();
        }
        Toast.makeText(this, "If you want to back, press one more back button.", Toast.LENGTH_SHORT).show();
        lastTimeBack = System.currentTimeMillis();
    }

}


