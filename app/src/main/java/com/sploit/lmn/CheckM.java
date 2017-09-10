package com.sploit.lmn;

/**
 * Created by Surface on 2017-03-19.
 */

public class CheckM extends Check {
    CheckM(LMNMap map, char LMN){
        super(map,LMN);
    }


    @Override
    public boolean isAvailableLMN(int x, int y) {
        if((map.getCoordinate(x+1,y) == 'm' || map.getCoordinate(x,y+1)  == 'm' ||map.getCoordinate(x-1,y) == 'm' || map.getCoordinate(x,y-1) == 'm') && map.getCoordinate(x,y)  == '#'){
            return true;
        }
        return false;
    }

}
