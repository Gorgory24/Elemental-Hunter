package com.example.testproj1;
public class Monster extends Character {
    public Monster(String name, int PV,int DEFP, int DEFM,Dice ATKP,Dice ATKM) {
        super(name,PV,DEFP,DEFM,ATKP,ATKM);
    }

    public void setName(String n){
        this.name = n;
    }

    public void setPV(int PV){
        this.PV = PV;
    }

    public void setDEFP(int DEFP){
        this.DEFP = DEFP;
    }

    public void setDEFM(int DEFM){
        this.DEFM = DEFM;
    }

    public void setATKP(Dice ATKP){
        this.ATKP = ATKP;
    }

    public void setATKM(Dice ATKM){
        this.ATKM = ATKM;
    }

    public int AttaqueM(){
        return ATKM.RollTheDice();
    }

    public int AttaqueP(){
        return ATKP.RollTheDice();
    }

    public int AttaqueM(Dice DiceSupp){
        return ATKM.RollTheDice() + DiceSupp.RollTheDice();
    }

    public int AttaqueP(Dice DiceSupp){
        return ATKP.RollTheDice() + DiceSupp.RollTheDice();
    }
}
