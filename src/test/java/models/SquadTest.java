package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SquadTest {
    public Squad testSquad(){
        return new Squad(3,"mlk","protect");
    }
    @BeforeEach
    void clear(){
        Squad.deleteAll();
    }

    @Test
    void getSquads() {
        Squad one=testSquad();
        Squad two=testSquad();
        Squad three=testSquad();
        assertEquals( 3,Squad.getSquads().size());
    }

    @Test
    void updateSquad() {
        Squad one=testSquad();
        one.updateSquad(3,"milk","noprotect");
        String name="milk";
        assertEquals(name,Squad.getById(1).getName());
    }


    @Test
    void deleteById() {
       Squad testHero=testSquad();

       Squad two=testSquad();

       Squad three=testSquad();
       Squad.deleteById(2);
        assertEquals(2,Squad.getSquads().size());
    }

    @Test
    void deleteAll() {
       Squad testSquad=testSquad();

       Squad two=testSquad();

       Squad three=testSquad();
       Squad.deleteAll();
        assertEquals(0,Squad.getSquads().size());
    }

//    @Test
//    void ifSquadNotFull() {
//        Hero testHero=new Hero("catWoman",19,"invisibility","lazy",1);
//        Hero twoHero=new Hero("catWoman",19,"invisibility","lazy",1);
//        Squad one=testSquad();
//        assertEquals(false, one.getSquadNotFull(testHero.getHeroSquadId()));
//    }
@Test
void heroSquadIdEqualSquadId() {
    Hero twHero=new Hero("catWoman",19,"invisibility","lazy",1);
    Squad one=testSquad();
    assertEquals(twHero.getHeroSquadId(), one.getId());
}

    @Test
    void getSquadMembers() {
        Hero twHero=new Hero("catWoman",19,"invisibility","lazy",1);
        Hero tHero=new Hero("catWoman",19,"invisibility","lazy",1);
        Hero th=new Hero("catWoman",19,"invisibility","lazy",1);
        Hero fo=new Hero("catWoman",19,"invisibility","lazy",1);
        Squad one=testSquad();
        Squad on=new Squad(3,"mlk","protect");
        ArrayList<Hero>testArray=new ArrayList<>();
        ArrayList<Hero>testB=new ArrayList<>();
        testArray.add(twHero);
        testArray.add(tHero);
        testArray.add(th);
        testArray.add(fo);
//        testB.add(tHero);
        assertEquals(testArray, one.getSquadMembers());
//        assertEquals(testB, on.getSquadMembers());
    }

    @Test
    void getSquadIdList() {
        Squad one=testSquad();
        Squad two=testSquad();
        Squad three=testSquad();
        ArrayList<Integer>testArray=new ArrayList<>();
        testArray.add(one.getId());
        testArray.add(two.getId());
        testArray.add(three.getId());
        assertEquals(testArray, Squad.getSquadIdList());
    }

}