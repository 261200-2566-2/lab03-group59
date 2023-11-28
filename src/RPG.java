public class RPG {
    public static class Characters {
        private String name;
        private double HP = 100;
        private double maxHP = 100;
        private double mana = 50;
        private double maxMana = 50;
        private double swordBaseDamage = 10;
        private double shieldBaseDefense = 10;
        private double defense = 10;
        private double baseRunSpeed = 10;
        private double you = 10;
        private double swordRunSpeedDecreased = 0;
        private double shieldRunSpeedDecreased = 0;
        private int level = 1;
        private boolean isHoldSwordable = false;
        private Sword equippedSword;
        private Shield equippedShield;

        public Characters(String name) {
            this.name = name;
            System.out.println("Hello " + name);
        }

        public void levelUp() {
            maxHP = 100 + 10 * level;
            maxMana = 50 + (2 * level);
            you = baseRunSpeed * (0.1 + 0.03 * level);
            level++;
        }

        public void equipSword(Sword sword) {
            equippedSword = sword;
            swordRunSpeedDecreased = baseRunSpeed * (0.1 + 0.04 * level);
            baseRunSpeed -= swordRunSpeedDecreased;
        }

        public void equipShield(Shield shield) {
            equippedShield = shield;
            shieldRunSpeedDecreased = baseRunSpeed * (0.1 + 0.08 * level);
            baseRunSpeed -= shieldRunSpeedDecreased;
        }

        public void unequipSword() {
            equippedSword = null;
            baseRunSpeed += swordRunSpeedDecreased;
            swordRunSpeedDecreased = 0;
        }

        public void unequipShield() {
            equippedShield = null;
            baseRunSpeed += shieldRunSpeedDecreased;
            shieldRunSpeedDecreased = 0;
        }

        public void printCharacterStats() {
            // Display Characters stats
            System.out.println("Characters Stats after Level Up:");
            System.out.println("Max HP: " + maxHP);
            System.out.println("Max Mana: " + maxMana);
            System.out.println("Sword Damage: " + getSwordDamage());
            System.out.println("Shield Defense: " + getShieldDefense());
            System.out.println("Max Run Speed: " + you);
        }

        public double calculateDamage(double damage) {
            // If there is an equipped shield, reduce damage based on shield defense
            if (equippedShield != null) {
                double effectiveDamage = damage - equippedShield.getBaseDefense();
                // Ensure the effective damage is non-negative
                return Math.max(effectiveDamage, 0);
            } else {
                return damage; // No shield, no reduction
            }
        }

        private double getSwordDamage() {
            return swordBaseDamage + (equippedSword != null ? equippedSword.getBaseDamage() : 0);
        }

        private double getShieldDefense() {
            return shieldBaseDefense + (equippedShield != null ? equippedShield.getBaseDefense() : 0);
        }
    }

    public static class Sword {
        private int level;
        private double baseDamage;

        public Sword(int level, double baseDamage) {
            this.level = level;
            this.baseDamage = baseDamage;
        }

        public void levelUp() {
            baseDamage *= (1 + (0.1 * level));
        }

        public double getBaseDamage() {
            return baseDamage;
        }

        public void printSwordStats(Characters characters) {
            if (characters.equippedSword != null) {
                System.out.println("Sword Stats:");
                System.out.println("Level: " + characters.equippedSword.level);
                System.out.println("Base Damage: " + characters.equippedSword.baseDamage);
            } else {
                System.out.println("No sword equipped.");
            }
        }
    }

    public static class Shield {
        private int level;
        private double baseDefense;

        public Shield(int level, double baseDefense) {
            this.level = level;
            this.baseDefense = baseDefense;
        }

        public void levelUp() {
            baseDefense *= (1 + (0.05 * level));
        }

        public double getBaseDefense() {
            return baseDefense;
        }

        public void printShieldStats(Characters characters) {
            if (characters.equippedShield != null) {
                System.out.println("Shield Stats:");
                System.out.println("Level: " + characters.equippedShield.level);
                System.out.println("Base Defense: " + characters.equippedShield.baseDefense);
            } else {
                System.out.println("No shield equipped.");
            }
        }
    }

    public static void main(String[] args) {
        Characters player = new Characters("Player");
        Sword sword = new Sword(1, 5);
        Shield shield = new Shield(1, 5);

        // Initial Characters stats
        player.printCharacterStats();

        // Equip sword and shield
        player.equipSword(sword);
        player.equipShield(shield);

        // Level up and print stats
        player.levelUp();
        player.printCharacterStats();
        sword.levelUp();
        shield.levelUp();
        sword.printSwordStats(player);
        shield.printShieldStats(player);

        // Test damage calculation with shield
        double incomingDamage = 15;
        double effectiveDamage = player.calculateDamage(incomingDamage);
        System.out.println("Effective Damage after Shield: " + effectiveDamage);

        // Print HP before and after taking damage
        System.out.println("Current HP: " + player.HP);
        System.out.println("Taking Damage: " + incomingDamage);
        player.HP -= effectiveDamage;
        System.out.println("Effective HP after Damage: " + player.HP);

        // Unequip shield and test damage calculation
        player.unequipShield();
        effectiveDamage = player.calculateDamage(incomingDamage);
        System.out.println("Effective Damage without Shield: " + effectiveDamage);

        // Print HP before and after taking damage without shield
        System.out.println("Current HP: " + player.HP);
        System.out.println("Taking Damage: " + incomingDamage);
        player.HP -= effectiveDamage;
        System.out.println("Effective HP after Damage without Shield: " + player.HP);

        // Unequip sword and shield and print stats
        player.unequipSword();
        player.unequipShield();
        player.printCharacterStats();
        sword.printSwordStats(player);
        shield.printShieldStats(player);
    }
}
