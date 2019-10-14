package com.example.testproj1;
public class Synderella extends Personnage {
    public Synderella() {
        super("Synderella", 40, 1, 3, new Dice(1, 8), new Dice(1, 10));
    }

    int ConsoGlyphe = 0;
    int SortUtilisé = 0;
    int NbSort = 4;

    public int getConsoGlyphe(){return this.ConsoGlyphe;}
    public int getSortUtilisé(){return this.SortUtilisé;}

    public void ComptGlyph(){
        ConsoGlyphe++;
        if(ConsoGlyphe == 4 && Quete == false)
        {
            Evolution();
        }
    }

    public void Sort(){
        if (SortUtilisé != NbSort) {
            SortUtilisé++;
        }
        else
        {
            NbSort=0;
        }
    }

    public void Evolution() {
        this.mana += 2;
        this.name = "Archimage Synderella";
        this.ATKP = new Dice(1,10);
        this.ATKM = new Dice(2,6);
        this.Quete = true;
        this.SortUtilisé = 3;
    }
}
