package com.example.testproj1;
import java.util.Random;

public class Dice {
    int NbDé;
    int ValDé;
    public Dice(int NbDé, int ValDé){
        this.NbDé = NbDé;
        this.ValDé = ValDé;
    }

    public int RollTheDice(){
        Random r = new Random();
        int result = 0;
        for(int i=0; i<NbDé; i++){
            result = r.nextInt(ValDé-1)+1;
        }
        return result;
    }
}
