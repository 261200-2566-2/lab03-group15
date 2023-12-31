// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //create sword and shield
        Sword Caliburn = new Sword("Caliburn", 50);
        Shield Aegis = new Shield("Aegis",20);
        //create character
        Character John = new Character("John",5);
        Character Peter = new Character("Peter",30, Caliburn);
        Character Odin = new Character("Odin",100,Caliburn,Aegis);
        Character Brian = new Character("Brian",1,Aegis);
        //test character creation
        Odin.showStat();
        Peter.showStat();
        John.showStat();
        Brian.showStat();
        //test weapon equip/unequip
        Brian.equipShield(Aegis);
        Brian.unEquipShield();
        John.equipShield(Aegis);
        //test level up
        Peter.levelUp(5);
        //test attack
        Peter.attack(Odin);
        Peter.attack(John);
        //test duel
        John.duel(Peter);
        John.showStat();
        Peter.showStat();
        Peter.duel(John);
        John.showStat();
        Peter.showStat();
        //test recovery()
        John.recovery();
        John.showStat();
        //test upgrade sword/shield
        Caliburn.showStat();
        Odin.upgrade(Caliburn, 20);
        Caliburn.showStat();
        Aegis.showStat();
        Odin.upgrade(Aegis, 20);
        Aegis.showStat();
        //test dead
        Odin.attack(John);
        John.showStat();
        John.attack(Odin);
        John.recovery();
        //test revive
        John.godRevive();
        Peter.godRevive();
        //late test (just calling remaining method())
        Odin.unEquipSword();
    }
}