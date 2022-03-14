package models;

import java.util.ArrayList;

public class Squad {
    private int id;
    private int maxSize;
    private String name;
    private String cause;
    private static ArrayList<Squad> instances = new ArrayList<>();
    private static ArrayList<Integer>squadIdList= new ArrayList<>();

    public Squad(int maxSize,String name,String cause){
        this.maxSize = maxSize;
        this.name = name;
        this.cause = cause;
        instances.add(this);
        this.id=instances.size();
        squadIdList.add(this.id);
    }

    public static ArrayList<Squad> getSquads() {
        return instances;
    }
    public static Squad getById(int id) {
        try {
            return instances.get(id - 1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }
    public static void add(Squad squad){
        instances.add(squad);
    }
    public void updateSquad(int maxSize,String name,String cause){
        this.maxSize = maxSize;
        this.name = name;
        this.cause = cause;
    }
    public static void deleteById(int id){
        instances.remove(Squad.getById(id));
    }
    public static void deleteAll(){
        instances.clear();
    }
//    public boolean getSquadNotFull(int s){
//        boolean add=false;
//        if(Squad.getById(s).maxSize< Squad.getById(s).getSquadMembers().id).size()){
//            add=true;
//        }
//        return add;
//    }
    public ArrayList<Hero> getSquadMembers(){
        ArrayList<Hero>SquadMembers= new ArrayList<>();
        for(int i:Hero.getHeroSquadIdList()){
                if (i == this.getId()) {
                    for (int p=0;p<Squad.getSquadIdList().size();p++) {
                        SquadMembers.add(Hero.getHeros().get(Hero.getHeroSquadIdList().indexOf(this.getId())+p));
                    }
            }
        }
        return SquadMembers;
    }
    public static ArrayList<Integer> getSquadIdList() {
        return squadIdList;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

}
