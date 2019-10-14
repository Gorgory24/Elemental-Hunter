package com.example.testproj1;
public class Loup extends Invocation {
    Dice Compétence;
    public Loup() {
        super("Loup", 8, 1, 1, new Dice(2, 4), new Dice(1, 4));
        this.Compétence = new Dice(1,4);
    }

    @Override
    public void LevelUp(int NbMaxCompteur) {
        super.LevelUp(NbMaxCompteur);
        switch (this.level){
            case 2:
                this.PV = 12;
                this.ATKP = new Dice(2,6);
                this.ATKM = new Dice(1,6);
                this.Compétence = new Dice(1,6);
                break;
            case 3:
                this.PV = 15;
                this.ATKP = new Dice(3,6);
                this.ATKM = new Dice(1,8);
                this.Compétence = new Dice(2,4);
                break;
        }
    }
}
