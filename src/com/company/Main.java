package com.company;


import java.util.Random;

public class Main {
    public static int counter = 0;
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = " ";
    public static int[] heroesHealth = {250, 250, 250,240};
    public static int[] heroesDamage = {20, 20, 20, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental","Medical"};

    public static void main(String[] args) {
        System.out.println("Start Game ---->");

        fightInfo();
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        counter++;
        System.out.println("Raund = " + counter);
        changeBossDefence();
        bossHit();
        healingHero();
        heroesHit();
        fightInfo();
    }

    private static void healingHero() {
        Random num1 = new Random();
        int hero_index = num1.nextInt(heroesHealth.length);
        Random num2 = new Random();
        int health = num2.nextInt(15) + 5;
        if (heroesHealth[hero_index] <= 100
                && heroesHealth[hero_index] > 0
                && heroesHealth[3] > 0){
            if (heroesHealth[hero_index] == heroesHealth[3]) {
                System.out.println("Medic нашел себя " + hero_index + " и не лечил " + heroesHealth[hero_index]);
                heroesHealth[hero_index] = heroesHealth[hero_index];
            } else {
                heroesHealth[hero_index] = heroesHealth[hero_index] + health;
                System.out.println("Medic нашел " + hero_index + " и лечил " + heroesHealth[hero_index]);
            }
        } else if (heroesHealth[hero_index] >= 100) {
            System.out.println("Medic выбрал " + hero_index + " и пропустил " + heroesHealth[hero_index]);
        } else if (heroesHealth[3] <= 0)
            System.out.println("Medic был убить ->" + heroesHealth[3]);

    }



    public static void changeBossDefence() {
        Random random1 = new Random();
        int randomIndex = random1.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
    }


    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boos won!");
            return true;
        }
        return false;
    }


    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {

                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random random2 = new Random();
                    int koeff = random2.nextInt(9) + 2;
                    if (bossHealth - heroesDamage[i] * koeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * koeff;
                    }
                    System.out.println(heroesAttackType[i] + " critical hit " + heroesDamage[i] * koeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {

                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    // Статистика боя
    public static void fightInfo() {
        System.out.println("_________________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("_________________________");
    }


}