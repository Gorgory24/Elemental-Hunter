package com.example.testproj1;
public class Gorgory extends Personnage {
    public Gorgory() {
        super("Gorgory", 40, 1, 3, new Dice(1, 8), new Dice(1, 10));
    }

    int ConsoCompteur = 0;
    int Feu = 0;
    int Eau = 0;
    int Air = 0;
    int Terre = 0;

    public void CompteElement(String element)
    {
        switch(element){
            case "FEU" : Feu++;
                break;
            case "EAU" : Eau++;
                break;
            case "AIR" : Air++;
                break;
            case "TERRE" : Terre++;
                break;
        }
    }

    public void ConsommeElement(String element) {
        switch (element) {
            case "FEU":
                Feu = 0;
                break;
            case "EAU":
                Eau = 0;
                break;
            case "AIR":
                Air = 0;
                break;
            case "TERRE":
                Terre = 0;
                break;
        }
        ConsoCompteur++;
        if (ConsoCompteur == 4 && Quete == false) {
            Evolution();
        }
    }

    public int getConsoCompteur(){return this.ConsoCompteur;}
    public int getFeu(){return this.Feu;}
    public int getEau(){return this.Eau;}
    public int getAir(){return this.Air;}
    public int getTerre(){return this.Terre;}

    public void FinCombat(){
        Feu = Air = Terre = Eau = 0;
    }

    public void Evolution() {
        {
            this.mana += 2;
            this.name = "Avatar Gorgory";
            this.ATKP = new Dice(1,10);
            this.ATKM = new Dice(2,6);
            this.Quete = true;
        }
    }

}