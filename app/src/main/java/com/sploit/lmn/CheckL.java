package com.sploit.lmn;

/**
 * Created by Surface on 2017-03-19.
 */

public class CheckL extends Check {
    CheckL(LMNMap map, char LMN){
        super(map,LMN);
    }

    @Override
    public boolean isAvailableLMN(int x, int y) {
        if((map.getCoordinate(x-1,y-1) == 'l' || map.getCoordinate(x-1,y+1)  == 'l' ||map.getCoordinate(x+1,y-1) == 'l' || map.getCoordinate(x+1,y+1) == 'l') && map.getCoordinate(x,y)  == '#'){
            return true;
        }
        return false;
    }
}
