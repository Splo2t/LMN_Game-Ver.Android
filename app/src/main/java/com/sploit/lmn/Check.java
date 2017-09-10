package com.sploit.lmn;

/**
 * Created by Surface on 2017-03-19.
 */

public class Check implements LMNCheck {
    char LMN;
    int count = 0;
    LMNMap map;


    Check(LMNMap map ,char LMN){
        this.map = map;
        this.LMN = LMN;
    }

    @Override
    public boolean isAvailableLMN(int x, int y) {
        return false;
    }

    @Override
    public boolean setLMN(int x, int y) {
        if(count == 0 && (map.getCoordinate(x,y)== '#')){
            map.setCoordinate(LMN,x,y);
            this.count++;
            return true;
        } else if(this.isAvailableLMN(x,y)){
            map.setCoordinate(LMN,x,y);
            return true;
        }
        return false;
    }

    public boolean availableLMN(int x, int y) {
        if(count == 0 && (map.getCoordinate(x,y)== '#')){
            return true;
        } else if(this.isAvailableLMN(x,y)){
            return true;
        }
        return false;
    }

    int getCount(){
        return count;
    }
}
