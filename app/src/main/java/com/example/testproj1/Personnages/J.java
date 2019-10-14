package com.example.testproj1.Personnages;
public class J extends Personnage {
    int EvoInvoc = 1;
    Loup loup;
    public J() {
        super("J", 40, 2, 2, new Dice(2, 4), new Dice(2, 4));
        this.loup = new Loup();
    }


    public void Evolution() {
        if(EvoInvoc == 3 && Quete == false)
        {
            this.mana += 2;
            this.name = "J le Druide";
            this.ATKP = new Dice(1,10);
            this.ATKM = new Dice(1,10);
            this.Quete = true;
        }
    }
}

/*int EvoLoup = 1;
    int EvoDragonnet = 1;
    int EvoAiouqes = 1;
    int EvoBouclier = 1;
    int ComptKillLoup = 0;
    int ComptKillDragonnet = 0;
    int ComptHealAiouqes = 0;
    int ComptProtectBouclier = 0;

    Dice LoupATKM = new Dice(1,4);
    Dice Loup2ATKM = new Dice(1,6);
    Dice Loup3ATKM = new Dice(1,8);
    Dice LoupATKP = new Dice(2,4);
    Dice Loup2ATKP = new Dice(2,6);
    Dice Loup3ATKP = new Dice(3,6);

    Dice DragonnetATKP = new Dice(1,4);
    Dice Dragonnet2ATKP = new Dice(1,6);
    Dice Dragonnet3ATKP = new Dice(1,8);
    Dice DragonnetATKM = new Dice(2,4);
    Dice Dragonnet2ATKM = new Dice(2,6);
    Dice Dragonnet3ATKM = new Dice(3,6);

    Dice InvocATK = new Dice(1,4);
    Dice Invoc2ATK = new Dice(1,6);
    Dice Invoc3ATK = new Dice(1,8);

    public void InvoqEvo(){
        EvoInvoc += 1;
    }

    public void LoupEvo(){
        if (ComptKillLoup == 3 && EvoLoup != 3) {
            EvoLoup += 1;
            ComptKillLoup = 0;
        }
    }

    public void DragonnetEvo(){
        if (ComptKillDragonnet == 3 && EvoDragonnet != 3)
        {
        EvoDragonnet += 1;
        ComptKillDragonnet = 0;
        }
    }

    public void ComptAiouqes(int Heal){
        ComptHealAiouqes += Heal;
    }

    public void AiouqesEvo(){
        if (ComptHealAiouqes == 10 && EvoAiouqes != 3) {
            EvoAiouqes += 1;
            ComptHealAiouqes = 0;
        }
    }

    public void ComptBouclier(int Shield){
        ComptProtectBouclier += Shield;
    }
    public void BouclierEvo(){
        if (ComptProtectBouclier == 7 && EvoBouclier != 3) {
            EvoBouclier += 1;
            ComptProtectBouclier = 0;
        }
    }

    public void Evo1(){
        if (EvoInvoc == 1 && (EvoLoup == 1 || EvoDragonnet == 1 || EvoAiouqes == 1 || EvoBouclier == 1)) {
            InvoqEvo();
        }
    }

    public void Evo2() {
        if (EvoInvoc == 2 && (EvoLoup == 2 || EvoDragonnet == 2 || EvoAiouqes == 2 || EvoBouclier == 2)) {
            InvoqEvo();
        }
    }

    public int AttaqueLoupP(){
        if (EvoLoup == 1) { return LoupATKM.RollTheDice();}
        else if (EvoLoup == 2) { return Loup2ATKM.RollTheDice();}
        else {return Loup3ATKM.RollTheDice();}
    }

    public int AttaqueLoupM(){
        if (EvoLoup == 1) {return LoupATKP.RollTheDice();}
        else if (EvoLoup == 2) {return Loup2ATKP.RollTheDice();}
        else {return Loup3ATKP.RollTheDice();}
    }

    public int AttaqueDragonnnetP(){
        if (EvoDragonnet == 1) { return DragonnetATKM.RollTheDice();}
        else if (EvoDragonnet == 2) { return Dragonnet2ATKM.RollTheDice();}
        else {return Dragonnet3ATKM.RollTheDice();}
    }

    public int AttaqueDragonnetM(){
        if (EvoDragonnet == 1) {return DragonnetATKP.RollTheDice();}
        else if (EvoDragonnet == 2) {return Dragonnet2ATKP.RollTheDice();}
        else {return Dragonnet3ATKP.RollTheDice();}
    }

    public int AttaqueAiouqes() {
        if (EvoAiouqes == 1) {
            return InvocATK.RollTheDice();
        } else if (EvoAiouqes == 2) {
            return Invoc2ATK.RollTheDice();
        } else {
            return Invoc3ATK.RollTheDice();
        }
    }

    public int AttaqueBouclier() {
        if (EvoBouclier == 1) {
            return InvocATK.RollTheDice();
        } else if (EvoBouclier == 2) {
            return Invoc2ATK.RollTheDice();
        } else {
            return Invoc3ATK.RollTheDice();
        }
    }*/