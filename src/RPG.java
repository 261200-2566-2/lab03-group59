public class RPG {
    private String name;
    private double baseHp;
    private double hp;
    private double maxHp;
    private double baseMana;
    private double mana;
    private double maxMana;
    private double baseAttack;
    private double attack;
    private double baseSpeed;
    private double speed;
    private double maxSpeed;
    private int level;
    private Sword sword;
    private Shield shield;

    public RPG(String name, int level) {
        this(name, 100, 50, 0, 10, level);
    }

    public RPG(String name, double baseHp, double baseMana, double baseAttack, double baseSpeed, int level) {
        this.name = name;
        this.level = level;
        this.baseHp = baseHp;
        this.hp = baseHp ;
        this.maxHp = baseHp ;
        this.baseMana = baseMana;
        this.mana = baseMana ;
        this.maxMana = baseMana ;
        this.baseAttack = baseAttack;
        this.attack = baseAttack ;
        this.baseSpeed = baseSpeed;
        this.speed = baseSpeed ;
        this.maxSpeed = baseSpeed ;
    }

    private void calculateStats() {
        this.maxHp = baseHp + (10 * level);
        this.hp = maxHp ;
        this.maxMana = baseMana + (2 * level);
        this.mana = maxMana ;
        this.maxSpeed = baseSpeed * (0.1 + 0.03 * level);
        this.attack = baseAttack * (1 + 0.1 * level);
        if (sword != null) {
            this.maxSpeed = maxSpeed - baseSpeed/2 * (0.1 + 0.04 * sword.level);
        }
        if (shield != null) {
            this.maxSpeed = maxSpeed - baseSpeed/2 * (0.1 + 0.08 * shield.level);
        }
        this.speed = maxSpeed ;
    }

    public String getName() {
        return name;
    }

    public double getHp() {
        return hp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public double getMana() {
        return mana;
    }

    public double getMaxMana() {
        return maxMana;
    }

    public double getAttack() {
        return (sword != null) ? attack + sword.getAttack() : attack;
    }

    public double getDefense() {
        return (shield != null) ? shield.getDefense() : 0;
    }

    public double getSpeed() {
        return speed;
    }

    public int getLevel() {
        return level;
    }

    public Sword getSword() {
        return sword;
    }

    public Shield getShield() {
        return shield;
    }

    public void equipSword(Sword sword) {
        this.sword = sword;
        calculateStats();
    }

    public void equipShield(Shield shield) {
        this.shield = shield;
        calculateStats();
    }

    public void upgrade() {
        this.level++;
        calculateStats();
    }

    public void heal(double amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    public void restoreMana(double amount) {
        mana += amount;
        if (mana > maxMana) {
            mana = maxMana;
        }
    }

    public class Sword {
        private String name;
        private double baseAttack;
        private double attack;
        private int level;

        public Sword(String name, double baseAttack, int level) {
            this.name = name;
            this.baseAttack = baseAttack;
            this.attack = baseAttack * (1 + 0.1 * level);
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public double getAttack() {
            return attack;
        }

        public int getLevel() {
            return level;
        }

        public void upgrade() {
            this.level++;
            calculateStats();
        }
    }

    public class Shield {
        private String name;
        private double baseDefense;
        private double defense;
        private int level;

        public Shield(String name, double baseDefense, int level) {
            this.name = name;
            this.baseDefense = baseDefense;
            this.defense = baseDefense * (1 + 0.05 * level);
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public double getDefense() {
            return defense;
        }

        public int getLevel() {
            return level;
        }

        public void upgrade() {
            this.level++;
            calculateStats();
        }
    }
    public void showStats() {
        System.out.println("=== Current Stats ===");
        System.out.println("Name: " + name +" (Level " + level +")");
        System.out.println("Sword: " + (sword != null ? sword.getName() + " (Level " + sword.getLevel() + ")" : "None"));
        System.out.println("Shield: " + (shield != null ? shield.getName() + " (Level " + shield.getLevel() + ")" : "None"));
        System.out.println("HP: " + hp + " / " + maxHp);
        System.out.println("Mana: " + mana + " / " + maxMana);
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
        System.out.println("Speed: " + speed);
        System.out.println("=====================");
    }
}
