public class Main {
    public static void main(String[] args) {
        RPG myCharacter = new RPG("lica", 20);
        RPG.Sword sword = myCharacter.new Sword("Excalibur", 10, 1);
        RPG.Shield shield = myCharacter.new Shield("Steel Shield", 10, 1);

        myCharacter.equipSword(sword);
        myCharacter.equipShield(shield);
        myCharacter.showStats();
    }
}