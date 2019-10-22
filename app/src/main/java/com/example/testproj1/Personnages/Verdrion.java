package com.example.testproj1.Personnages;

import java.io.Serializable;

public class Verdrion extends Personnage {
    public Verdrion() {
        super("Verdrion", 40, 3, 1, new Dice(1, 10), new Dice(1, 8));
    }
    int NbCumulAtk = 0;
    int DgtSuppCumul = 2;
    int NbDgtSupp = 0;

    public void EstAttaqué(){
        NbCumulAtk++;
        NbDgtSupp++;
        if (NbCumulAtk == 4 && Quete == false){
            Evolution();
        }
    }

    @Override
    public void Comptvie(int Damage) {
        super.Comptvie(Damage);
        if(Damage < 0) {
            NbCumulAtk++;
            NbDgtSupp++;
            if (NbCumulAtk == 4 && Quete == false) {
                Evolution();
            }
        }
    }

    public void FinCombat(){
        NbCumulAtk = 0;
        NbDgtSupp = 0;
    }

    @Override
    public int AttaqueP(){
        int Dgt = ATKP.RollTheDice()+(DgtSuppCumul*NbDgtSupp);
        NbDgtSupp = 0;
        return Dgt;
    }

    @Override
    public int AttaqueM(){
        int Dgt = ATKM.RollTheDice()+(DgtSuppCumul*NbDgtSupp);
        NbDgtSupp = 0;
        return Dgt;
    }

    public int getNbCumulAtk() {
        return NbCumulAtk;
    }

    public void Evolution() {
        {
            this.mana += 2;
            this.name = "Verdrion le Berserker";
            this.ATKP = new Dice(2,6);
            this.ATKM = new Dice(1,10);
            this.Quete = true;
            DgtSuppCumul = 3;
        }
    }


}
