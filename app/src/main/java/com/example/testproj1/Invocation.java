package com.example.testproj1;
public class Invocation extends Personnage {
    int level;
    int Compteurlvlup;
    boolean IsSummoned;

    public Invocation(String name, int PV, int DEFP, int DEFM, Dice ATKP, Dice ATKM) {
        super(name, PV, DEFP, DEFM, ATKP, ATKM);
        this.mana = 0;
        this.level = 1;
        this.Compteurlvlup = 0;
        this.IsSummoned = false;
    }

    public void LevelUp(int NbMaxCompteur){
        if (level < 3 && Compteurlvlup >= NbMaxCompteur)
        {
            level++;
            Compteurlvlup = 0;
        }
    }

    public void Summonise()
    {
        this.IsSummoned = true;
    }

    public void UnSummonise() {this.IsSummoned = false; }
}
