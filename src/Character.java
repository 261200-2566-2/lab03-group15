public class Character {
    String name;
    int level = 1;
    double maxHP = 100+10*(level-1);
    double hp = maxHP;
    Boolean dead = false;
    double maxMana = 50+2*(level-1);
    double mana = maxMana;
    double maxRunSpeed = 30*(0.1+0.03*(level-1));
    double runSpeedPenalty = 0;
    double runSpeed = maxRunSpeed*((100-runSpeedPenalty)/100);
    double attack;
    double defense;
    Boolean swordEquipped = false;
    Sword sword;
    Boolean shieldEquipped = false;
    Shield shield;
    Character(String name,int level){
        this.name = name;
        this.level = level;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    Character(String name,int level,Sword sword){
        this.name = name;
        this.level = level;
        attack = sword.damage;
        swordEquipped = true;
        this.sword = sword;
        runSpeedPenalty += 5;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    Character(String name,int level,Shield shield){
        this.name = name;
        this.level = level;
        defense = shield.defense;
        shieldEquipped = true;
        this.shield = shield;
        runSpeedPenalty += 10;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    Character(String name,int level,Sword sword,Shield shield){
        this.name = name;
        this.level = level;
        attack = sword.damage;
        swordEquipped = true;
        this.sword = sword;
        runSpeedPenalty += 5;
        defense = shield.defense;
        shieldEquipped = true;
        this.shield = shield;
        runSpeedPenalty += 10;
        updateStat();
        hp = maxHP;
        mana = maxMana;
    }
    void recovery(){
        if(!dead){
            System.out.println("-------------------------------------");
            System.out.println(name + " is healed");
            System.out.println("-------------------------------------");
            hp = maxHP;
            mana = maxMana;
        }else{
            System.out.println("-------------------------------------");
            System.out.println("We already lost " + name);
            System.out.println("Nothing can heal " + name);
            System.out.println("-------------------------------------");
        }

    }
    void godRevive(){
        if(dead){
            System.out.println("-------------------------------------");
            System.out.println(name + " received god's blessing!");
            System.out.println(name + " has come back to life!!!");
            System.out.println("-------------------------------------");
            dead = false;
            hp = maxHP;
            mana = maxMana;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " has wasted god's blessing!");
            System.out.println("-------------------------------------");
        }
    }
    void updateStat(){
        maxHP = 100+10*(level-1);
        maxMana = 50+2*(level-1);
        maxRunSpeed = 30*(0.1+0.03*(level-1));
        runSpeed = maxRunSpeed*((100-runSpeedPenalty)/100);
        if(swordEquipped) attack = sword.damage;
        if(shieldEquipped) defense = shield.defense;
    }
    void levelUp(int level){
        this.level += level;
        updateStat();
        System.out.println("-------------------------------------");
        System.out.println(name + " level up!!!");
        System.out.println(name + " is now level " + level);
        System.out.println("-------------------------------------");
        if(!dead) recovery();
    }
    void equipSword(Sword sword){
        if(!swordEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is equipping " + sword.name);
            System.out.println("-------------------------------------");
            attack = sword.damage;
            swordEquipped = true;
            this.sword = sword;
            runSpeedPenalty += 5;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " already equipping " + sword.name);
            System.out.println(name + " can't equip any more sword!");
            System.out.println("-------------------------------------");
        }
    }
    void unEquipSword(){
        if(swordEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is unequipping " + sword.name);
            System.out.println("-------------------------------------");
            attack = 0.0;
            swordEquipped = false;
            this.sword = null;
            runSpeedPenalty -= 5;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " isn't equipping any sword!");
            System.out.println("-------------------------------------");
        }
    }
    void equipShield(Shield shield){
        if(!shieldEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is equipping " + shield.name);
            System.out.println("-------------------------------------");
            defense = shield.defense;
            shieldEquipped = true;
            this.shield = shield;
            runSpeedPenalty += 10;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " already equipping " + shield.name);
            System.out.println(name + " can't equip any more shield!");
            System.out.println("-------------------------------------");
        }
    }
    void unEquipShield(){
        if(shieldEquipped){
            System.out.println("-------------------------------------");
            System.out.println(name + " is unequipping " + shield.name);
            System.out.println("-------------------------------------");
            defense = 0.0;
            shieldEquipped = false;
            this.shield = null;
            runSpeedPenalty -= 10;
        }else{
            System.out.println("-------------------------------------");
            System.out.println(name + " isn't equipping any shield!");
            System.out.println("-------------------------------------");
        }
    }
    void showStat(){
        System.out.println("-------------------------------------");
        System.out.println(name + "'s Stat ");
        System.out.println("Level: " + level);
        if(dead){
            System.out.println("HP: " + this.hp + "/" +maxHP + " [DEAD]");
        }else{
            System.out.println("HP: " + this.hp + "/" +maxHP);
        }
        System.out.println("MP: " + this.mana + "/" +maxMana);
        System.out.println("RunSpeed: " + this.runSpeed + "/" +maxRunSpeed);
        System.out.println("Damage: " + this.attack);
        System.out.println("Defense: " + this.defense);
        System.out.println("-------------------------------------");
    }
    void attack(Character opponent){
        if(!dead){
            if(!opponent.dead){
                System.out.println("-------------------------------------");
                System.out.println(this.name + " Attack " + opponent.name + "!");
                double damage = this.attack-opponent.defense;
                System.out.println(this.name + " dealt " + damage +" damage!");
                if(damage >= opponent.hp) damage = opponent.hp;
                if(damage < 0) damage = 0;
                opponent.hp -= damage;
                System.out.println(opponent.name + "'s remaining HP is " + opponent.hp);
                System.out.println("-------------------------------------");
                if(opponent.hp <= 0) {
                    opponent.dead = true;
                    System.out.println(opponent.name + " Dead");
                    System.out.println("-------------------------------------");
                }
            }else{
                System.out.println("-------------------------------------");
                System.out.println(this.name + " can't attack " + opponent.name + "!");
                System.out.println(opponent.name + " is already dead!!!");
                System.out.println("-------------------------------------");
            }
        }else{
            System.out.println(name + " is dead. " + name + " can't attack.");
        }
    }
    void upgrade(Sword sword,int level){
        sword.level += level;
        sword.damage = 15*(1+0.1*(sword.level-1));
        updateStat();
        System.out.println("-------------------------------------");
        System.out.println(name + " is upgrading " + sword.name + "!");
        System.out.println(sword.name + "'s level is now " + sword.level + "!!!");
        System.out.println("-------------------------------------");
    }
    void upgrade(Shield shield,int level){
        shield.level += level;
        shield.defense  = 10*(1+0.05*(shield.level-1));
        updateStat();
        System.out.println("-------------------------------------");
        System.out.println(name + " is upgrading " + shield.name + "!");
        System.out.println(shield.name + "'s level is now " + shield.level + "!!!");
        System.out.println("-------------------------------------");
    }
    void duel(Character opponent){
        System.out.println("-------------------------------------");
        System.out.println(this.name + " and " + opponent.name + " are dueling!!!");
        System.out.println("-------------------------------------");
        if(this.runSpeed > opponent.runSpeed) {
            this.attack(opponent);
        }
        else if (opponent.runSpeed > this.runSpeed) {
            opponent.attack(this);
        }else {
            this.attack(opponent);
            opponent.attack(this);
        }
    }
}
