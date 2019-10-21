package com.example.testproj1.Personnages;

import java.io.Serializable;

public class Personnage extends Character implements Serializable {
    int mana;
    boolean Quete = false;
    public Personnage(String name, int PV,int DEFP, int DEFM,Dice ATKP,Dice ATKM) {
        super(name,PV,DEFP,DEFM,ATKP,ATKM);
        this.mana = 2;
    }

    public String toString() {
        return ("" + this.name + " : PV = " + PV + " | Mana: " + this.mana
                + "\n | ATKP: " + this.getATKP() + " | ATKM: " + this.getATKM()
                + "\n | DEFP: " + this.getDEFP() + " | DEFM: " + this.getDEFM());
    }

    public void ArmureDEFP(int BonusP) {DEFP += BonusP;}
    public void ArmureDEFM(int BonusM) {DEFM += BonusM;}
    public void Comptvie(int Damage) {PV+=Damage;}
    public int getMana() {return this.mana;}
    public void setMana(int m) {this.mana = m;}

    public String getName() { return this.name;}
    public int AttaqueP(){
        return ATKP.RollTheDice();
    }

    public int AttaqueM(){
        return ATKM.RollTheDice();
    }
}
