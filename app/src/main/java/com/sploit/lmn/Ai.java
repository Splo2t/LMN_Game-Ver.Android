package com.sploit.lmn;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Surface on 2017-09-02.
 */

class Ai {
    LMNMap lmnMap;
    CheckL checkL;
    CheckM checkM;
    CheckN checkN;


    Ai(LMNMap lmnMap, CheckL checkL, CheckM checkM, CheckN checkN){
        this.lmnMap = lmnMap;
        this.checkL = checkL;
        this.checkM = checkM;
        this.checkN = checkN;
    }

    String setLMNCard(){
        ArrayList<String> lmnList = new ArrayList();
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                if(checkL.availableLMN(i,j)) {
                    lmnList.add("l" + String.valueOf(i) + String.valueOf(j));
                }
                if(checkM.availableLMN(i,j)) {
                    lmnList.add("m" + String.valueOf(i) + String.valueOf(j));
                }
                if(checkN.availableLMN(i,j)) {
                    lmnList.add("n" + String.valueOf(i) + String.valueOf(j));
                }
            }
        }

        Random random = new Random();

        String coordinate = lmnList.get(random.nextInt(lmnList.size()))+ String.valueOf(lmnList.size());

        return coordinate;


    }
}
