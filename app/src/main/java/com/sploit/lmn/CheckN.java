package com.sploit.lmn;

/**
 * Created by Surface on 2017-03-19.
 */

public class CheckN extends Check {
    CheckN(LMNMap map, char LMN){
        super(map,LMN);
    }

    @Override
    public boolean isAvailableLMN(int x, int y) {
        if((map.getCoordinate(x+2,y) == 'n' || map.getCoordinate(x,y+2)  == 'n' ||map.getCoordinate(x-2,y) == 'n' || map.getCoordinate(x,y-2) == 'n') && map.getCoordinate(x,y)  == '#'){
            return true;
        }
        return false;
    }
}
