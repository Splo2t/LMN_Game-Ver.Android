package com.sploit.lmn;

/**
 * Created by Surface on 2017-03-19.
 */

public class LMNMap {
    char map[][] = new char[8][8];
    int seq = 0;

    LMNMap(){
        this.resetMap();
    }

    char setCoordinate(char lmn, int x, int y){
        x = x+2;
        y = 5-y;
        if(lmn == 'l' || lmn == 'm' || lmn == 'n'){
            map[x][y] = lmn;
            return lmn;
        } return 'b';
    }

    char getCoordinate(int x, int y){
        x = x+2;
        y = 5-y;
        return map[x][y];
    }

    void resetMap(){
        for(int i = 2; i < 6; i++){
            for(int j = 2; j < 6; j++){
                map[i][j] = '#';
            }
        }
    }


    public void addSeq() {
        seq++;
    }

    public String getTrun() {
        String returnString = "";
        return returnString + "P" + String.valueOf(seq%2+1);

    }
}
