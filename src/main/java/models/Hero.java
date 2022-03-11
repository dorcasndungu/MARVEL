package models;

import java.util.ArrayList;


public class Hero {
    private String name;
    private int age;
    private String power;
    private String weakness;
    private static ArrayList<Hero> instances = new ArrayList<>();
    private static ArrayList<Integer>heroSquadIdList= new ArrayList<>();
    private int heroSquadId;
    private int id;

    public Hero(String name, int age, String power, String weakness,int heroSquadId) {
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
        this.heroSquadId = heroSquadId;
        this.id=instances.size();
        instances.add(this);
        heroSquadIdList.add(this.heroSquadId);
    }
    public static void addHero(Hero hero){
        instances.add(hero);
    }
    public static ArrayList<Hero> getHeros() {
        return instances;
    }
    public static Hero getById(int id) {
        try {
            return instances.get(id - 1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public void updateHero(String name, int age, String power, String weakness,int heroSquadId){
        this.name = name;
        this.age = age;
        this.power = power;
        this.weakness = weakness;
        this.heroSquadId = heroSquadId;
    }
    public static void deleteById(int id){
    instances.remove(Hero.getById(id));
    }
    public static void deleteAll(){
        instances.clear();
    }
    public static ArrayList<Integer> getHeroSquadIdList() {
        return heroSquadIdList;
    }
    public int getHeroSquadId() {
        return heroSquadId;
    }

    public void setHeroSquadId(int heroSquadId) {
        this.heroSquadId = heroSquadId;
    }

    public int getId() {
        return id;
    }

    public void setHeroId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }


}

