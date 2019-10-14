package com.example.testproj1;
public class Personnage extends Character{
    int mana;
    boolean Quete = false;
    public Personnage(String name, int PV,int DEFP, int DEFM,Dice ATKP,Dice ATKM) {
        super(name,PV,DEFP,DEFM,ATKP,ATKM);
    }

    public void ArmureDEFP(int BonusP) {DEFP += BonusP;}
    public void ArmureDEFM(int BonusM) {DEFM += BonusM;}
    public void Comptvie(int Damage) {PV+=Damage;}
    public int getMana() {return this.mana;}
    public void setMana(int m) {this.mana = m;}
    public int AttaqueP(){
        return ATKP.RollTheDice();
    }

    public int AttaqueM(){
        return ATKM.RollTheDice();
    }
}
