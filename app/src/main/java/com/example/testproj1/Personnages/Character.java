package com.example.testproj1.Personnages;

import java.io.Serializable;

public class Character implements Serializable {
    String name;
    int PV;
    int DEFP;
    int DEFM;
    Dice ATKP;
    Dice ATKM;
    boolean Brulure = false;
    boolean Gel = false;
    boolean Suffocation = false;
    boolean Empoisonnement = false;
    int NbTurnBrulure = 0;
    int NbTurnGel = 0;
    int NbTurnSuffocation = 0;
    int NbTurnEmpoisonnement = 0;
    //Photo

    public Character(String name, int PV,int DEFP, int DEFM,Dice ATKP,Dice ATKM){
        this.name = name;
        this.PV = PV;
        this.DEFP = DEFP;
        this.DEFM = DEFM;
        this.ATKP = ATKP;
        this.ATKM = ATKM;
    }

    public int getPV(){
        return this.PV;
    }

    public int getDEFM(){
        return this.DEFM;
    }

    public int getDEFP(){
        return this.DEFP;
    }

    public Dice getATKP(){
        return this.ATKP;
    }

    public Dice getATKM(){
        return this.ATKM;
    }
}
