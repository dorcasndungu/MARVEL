import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

import static spark.Spark.staticFileLocation;

import static spark.route.HttpMethod.post;

public class App {
    public static void main(String[] args) {


        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that port.
        // Otherwise, if they do not, continue using port 4567.

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            request.session().attribute("squads",Squad.getSquads());
            model.put("squads",request.session().attribute("squads"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //Get squad Form
        get("/squad/new",(request, response) ->{
            Map <String,Object> model = new HashMap<>();
            request.session().attribute("squads",Squad.getSquads());
            model.put("squads",request.session().attribute("squads"));
            return new ModelAndView(model,"Squad-form.hbs");
        },new HandlebarsTemplateEngine() );

        //Post Squad Form ::CREATE
        post("/squads",(request, response) -> {
            String name = request.queryParams("name");
            int size = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            Squad newSquad=new Squad(size,name,cause);
            Map <String,Object> model = new HashMap<>();
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine() );

        //Get Hero Form
        get("/hero/new",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            request.session().attribute("squads",Squad.getSquads());
            request.session().attribute("heroes",Hero.getHeros());
            model.put("squads",request.session().attribute("squads"));
            model.put("heroes",request.session().attribute("heroes"));
            return new ModelAndView(model,"Hero-form.hbs");
        },new HandlebarsTemplateEngine());

        //Post Hero Form ::CREATE
        post("/heroes",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            Hero newHero=new Hero(name,age,power,weakness,squadId);
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine() );

        //heroes::READ
        get("/hero",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            request.session().attribute("squads",Squad.getSquads());
            request.session().attribute("heroes",Hero.getHeros());
            model.put("squads",request.session().attribute("squads"));
            model.put("heroes",request.session().attribute("heroes"));
            return new ModelAndView(model,"Hero.hbs");
        },new HandlebarsTemplateEngine());

        //Heroes In a specific Squad ::READ
        get("/squads/:id",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int squadId = Integer.parseInt( request.params("id"));
            request.session().attribute("squad",Squad.getById(squadId));
            request.session().attribute("heroes",Squad.getById(squadId).getSquadMembers());
            model.put("squad",request.session().attribute("squad"));
            model.put("heroes",request.session().attribute("heroes"));
            return new ModelAndView(model,"Squad.hbs");
        },new HandlebarsTemplateEngine());

        //Get Form For Squad Updates
        get("/squads/:squadId/update",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("edit",true);
            int squadToEdit = Integer.parseInt(request.params("squadId"));
            model.put("squad",Squad.getById(squadToEdit));
            model.put("squads",Squad.getSquads());
            return new ModelAndView(model,"Squad-form.hbs");
        },new HandlebarsTemplateEngine());

        //Update Squad ::UPDATE
        post("/squad/:squadId",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int idOfSquad = Integer.parseInt(request.params("squadId"));
            String newName = request.queryParams("newName");
            int newSize = Integer.parseInt(request.queryParams("newSize"));
            String newCause = request.queryParams("newCause");
            Squad editSquad=Squad.getById(idOfSquad);
            editSquad.updateSquad(newSize,newName,newCause);
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine() );

        //Get form for hero updates

        get("/hero/:heroId/update",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            model.put("update",true);
            int heroToUpdate = Integer.parseInt(request.params("heroId"));
            model.put("hero",Hero.getById(heroToUpdate));
            model.put("squad",Squad.getSquads());
            return new ModelAndView(model,"Hero-form.hbs");
        },new HandlebarsTemplateEngine());

        //Update Hero ::UPDATE
        post("/heroes/:heroId",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int heroId = Integer.parseInt(request.params("heroId"));
            String newName = request.queryParams("newName");
            int newAge = Integer.parseInt(request.queryParams("newAge"));
            String newPower = request.queryParams("newPower");
            String newWeakness = request.queryParams("newWeakness");
            int squadId = Integer.parseInt(request.queryParams("squadId"));
            Hero updateHero=Hero.getById(heroId);
            updateHero.updateHero(newName,newAge,newPower,newWeakness,squadId);
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine() );

        // Delete All Squads and Heroes :: DELETE
        get("/squad/delete",(request, response) -> {
            Hero.deleteAll();
            Squad.deleteAll();
            Map <String,Object> model = new HashMap<>();
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine() );

        //Delete squad at given id
        get("/squads/:squadId/delete",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int squadId = Integer.parseInt(request.params("squadId"));
            Squad.deleteById(squadId);
            model.put("squads",Squad.getSquads());
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine() );


        //Delete Heroes At A given Id
        get("/heroes/:heroId/delete",(request, response) -> {
            Map <String,Object> model = new HashMap<>();
            int heroId = Integer.parseInt(request.params("heroId"));
            Hero.deleteById(heroId);
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine() );

    }
}

