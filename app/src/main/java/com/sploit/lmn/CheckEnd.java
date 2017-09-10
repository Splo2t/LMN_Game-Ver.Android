package com.sploit.lmn;

/**
 * Created by Surface on 2017-03-24.
 */

public class CheckEnd {
    LMNMap map;
    CheckL l;
    CheckM m;
    CheckN n;

    int returnEndCount;
    int endCount = 0;

    CheckEnd(LMNMap map, CheckL l, CheckM m, CheckN n){
        this.map = map;
        this.l = l;
        this.m = m;
        this.n = n;
    }

    boolean checkend(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(map.getCoordinate(i,j) == '#'){
                    if(!l.isAvailableLMN(i,j)){
                        if(!m.isAvailableLMN(i,j)){
                            if(!n.isAvailableLMN(i,j)){
                                endCount++;
                            }
                        }
                    }
                }

            }
        }
        returnEndCount = endCount;

        if((endCount == (16-map.seq) && map.seq != 0 && l.getCount() != 0 && m.getCount() != 0 && n.getCount()!= 0) || map.seq == 16){
            endCount = 0;
            return true;
        }else {
            endCount = 0;
            return false;
        }
    }
}
