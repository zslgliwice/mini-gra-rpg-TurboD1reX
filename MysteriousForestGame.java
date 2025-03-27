import java.util.Random;
import java.util.Scanner;

class Player {
    int hp = 100;
    int gold = 10;
    int attack = 5;
}

class Enemy{
    String name;
    int hp;
    int attack;

    public Enemy(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }
}

public class MysteriousForestGame {
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);
    static Player player = new Player();
    static boolean game = false;
    public static void main(String[] args) throws PersonalException{
        System.out.println("Witaj w lesie");
        while (true) {
            System.out.println("Your Stats : " + player.hp + " Gold: " + player.gold + " Attack: " + player.attack);
            System.out.println("1. Explore");
            System.out.println("2. Check eq");
            System.out.println("3. Game over");
            
            int choice = scanner.nextInt();
            if(choice ==1) {
                explore();
            }else if(choice ==2) {
                checkEq();
            }else if(choice ==3) {
                game = false;
                System.out.println("Game over.");
                break;
            }else {
                throw new PersonalException("Dales zly wybor");
            }
        }
    }
    public static void explore() throws PersonalException{
        int zdarzenie = random.nextInt(10);
        if(zdarzenie < 4){
            attack();
        }else if(zdarzenie < 7){
            skarb();
        }else if(zdarzenie < 9){
            camp();
        }else{
            foundExit();
        }
    }
    public static void attack() throws PersonalException{
        String[] enemies = {"Wilk", "Goblin", "Bandyta"};
        String name = enemies[random.nextInt(enemies.length)];
        int hp =  random.nextInt(30)+20;
        int attack = random.nextInt(10)+5;
        Enemy enemy = new Enemy(name, hp, attack);
        System.out.println("SPOTKALES ENEMY: "+ enemy.name +" HP = "+ enemy.hp +" ATTACK = "+ enemy.attack);
        while(enemy.hp > 0 && player.hp > 0){
            System.out.println("1. attack");
            System.out.println("2. uciekaj");
            int choice = scanner.nextInt();
            if(choice == 1) {
                int playerDMG = random.nextInt(player.attack/2, player.attack +1);
                enemy.hp -= playerDMG;
                System.out.println("Dales "+ playerDMG +" damage'u "+ enemy.name +" ma teraz "+ enemy.hp +"hp");
                if(enemy.hp > 0) {
                    int enemyDMG = random.nextInt(enemy.attack/2, enemy.attack +1);
                    player.hp -= enemyDMG;
                    System.out.println(enemy.name +" atakowal i dostales "+ enemyDMG +" damage'u teraz masz "+ player.hp +"hp");
                }else {
                    int loot = random.nextInt(5)+5;
                    player.gold += loot;
                    System.out.println("Zabiles "+ enemy.name +" i dostajesz: "+ loot + " goldu");
                }
            }else if(choice == 2) {
                if(random.nextBoolean()) {
                    System.out.println("Uciekles brawo itp");
                    break;
                }else {
                    System.out.println("Nie uciekles unloko sorki");
                    int enemyDMG = random.nextInt(enemy.attack/2, enemy.attack +1);
                    player.hp -= enemyDMG;
                    System.out.println(enemy.name +" atakowal i dostales "+ enemyDMG +" damage'u teraz masz "+ player.hp +"hp");
                }
            }else {
                throw new PersonalException("Dales zly wybor");
            }
        }
        if(player.hp <= 0) {
            System.out.println("Zostales pokonany");
            System.exit(0);
        }
    }
    public static void skarb(){
        int gold = random.nextInt(10)+5;
        System.out.println("Znalazles skarb: " + gold);
        player.gold +=gold;
    }
    public static void camp() throws PersonalException{
        System.out.println("Znalazles camp mozesz odpoczac za 5 golda i regenerowac 20hp");
        System.out.println("1. chill");
        System.out.println("2. continue");
        System.out.print("> ");
        int choice = scanner.nextInt();
        if (choice == 1 && player.gold >= 5) {
            player.gold -= 5;
            player.hp += 20;
            System.out.println("Odpoczales i masz " + player.hp +"hp");
        } else if (choice == 1) {
            throw new PersonalException("Dales zly wybor");
        }
    }
    static void foundExit() {
        System.out.println("Znalazles wyjscie game over HP :" + player.hp + " gold: " + player.gold + "attacK: " + player.attack);
        System.exit(0);
    }
    
    static void checkEq() {
        System.out.println("Eq: HP = " + player.hp + " gold: " + player.gold +" attack: " + player.attack);
    }
}