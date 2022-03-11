package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    public Hero testHero(){
        return new Hero("catWoman",19,"invisibility","lazy",1);
    }

    @AfterEach
    void tearDown() throws Exception {
       Hero.deleteAll();
    }


    @Test
    void addHero_heroInstantiatedWithId() {
        Hero testHero=testHero();
        Hero.addHero(testHero);
        int heroId=testHero.getId();
        assertEquals(heroId,testHero.getId());
    }
    @Test
    void heroInstantiatedWithSquadId() {
        Hero testHero=testHero();
        Hero test=testHero();
        int heroId=testHero.getHeroSquadId();
        assertEquals(heroId,test.getHeroSquadId());
    }
    @Test
    void heroInstantiatedWithSquadIdAddedToList() {
        Hero testHero=testHero();
        Hero test=testHero();
        ArrayList<Integer>testArr=new ArrayList<>();
        testArr.add(testHero.getHeroSquadId());
        testArr.add(test.getHeroSquadId());
        assertEquals(testArr,Hero.getHeroSquadIdList());
    }

    @Test
    void getHeros() {
        Hero testHero=testHero();

        Hero two=testHero();

        Hero three=testHero();

        assertEquals(3,Hero.getHeros().size());
    }

    @Test
    void getById() {
        Hero testHero=testHero();
        assertEquals(testHero,Hero.getById(1));
    }

    @Test
    void updateHero() {
        testHero().updateHero("man",9,"invisibility","lazy",1);
        String name="man";
        assertEquals(name, Hero.getHeros().get(0).getName());
    }

    @Test
    void deleteById() {
        Hero testHero=testHero();

        Hero two=testHero();

        Hero three=testHero();
        Hero.deleteById(2);
        assertEquals(2, Hero.getHeros().size());
    }

    @Test
    void deleteAll() {
        Hero testHero=testHero();

        Hero two=testHero();

        Hero three=testHero();
        Hero.deleteAll();
        assertEquals(0, Hero.getHeros().size());
    }

}