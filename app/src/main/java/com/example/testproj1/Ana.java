package com.example.testproj1;
public class Ana extends Personnage {
    public Ana() {
        super("Ana", 40, 3, 1, new Dice(1, 10), new Dice(1, 8));
    }
    int CoupCritique = 0;
    boolean Furtif = false;
    Dice Crit = new Dice(1,6);
    Dice CritFurtif = new Dice(1,4);
    Dice DgtCrit = new Dice(1,10);
    Dice yolo;

    public int getCoupCritique(){
        return this.CoupCritique;
    }

    public boolean getFurtif(){
        return this.Furtif;
    }

    public void Critique() {
        if (Furtif == false){
            if (Crit.RollTheDice() == 1) {
                CoupCritique += 1;
            }
            DgtCrit.RollTheDice();
        }
        else
        {
            if(CritFurtif.RollTheDice() == 1) {
                CoupCritique += 1;
            }
            DgtCrit.RollTheDice();
        }
        if(CoupCritique == 4 && Quete == false) {Evolution();}
    }


    public void Evolution(){
        this.mana += 2;
        this.name = "Ana de l'Ombre";
        this.ATKP = new Dice(2,6);
        this.ATKM = new Dice(1,10);
        this.Crit = new Dice(1,4);
        this.CritFurtif = new Dice(1,3);
        this.Quete  = true;
    }
}