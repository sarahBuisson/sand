package behave;

import fr.perso.sand.model.*;
import fr.perso.sand.service.Factory;

import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sbuisson on 23/11/2014.
 */

public abstract class AbstractSandBDD{
    Factory factory ;
    Map<String, Grain> grains ;
    Map<String,ObjetTerrain> objets;
    Terrain terrain;
@BeforeStory
public void init(){

     factory = new Factory();
   grains = new HashMap<String, Grain>();
     terrain = factory.initTerrain(10, 10);
}
    @Given("Un grain $g1")
    public Grain givenGrain(String idGrain) {
        return givenGrain(idGrain, null);
    }

    @Given("Aux coordonées ($x,$y) le grain $g1")
    public Grain givenGrain(int x,int y,String idGrain) {
        Grain grain=terrain.getAt(x,y).getContenu();
        grains.put(idGrain,grain);
        return  grain;
    }

    @Given("Le grain $g1 a la position ($x,$y)")
    public Grain givenGrainPosition(String idGrain, int x, int y) {
        Grain grain = grains.get(idGrain);
        terrain.addElement(grain, x, y);
        return grain;
    }

    @Given("Un grain $g1 de type $type")
    public Grain givenGrain(String idGrain, TypeGrain type) {
        if (!grains.containsKey(idGrain)) {
            Grain grain = factory.initGrain(type);
            grains.put(idGrain, grain);
        }
        return grains.get(idGrain);
    }
    @Given("Un terrain de dimention($largeur,$hauteur)")
    public void givenTerrain(int largeur,int hauteur){
        terrain=factory.initTerrain(largeur,hauteur);


    }
    @Given ("Un terrain \"$s\"")
    public void givenTerrain(String strTerrain){
        terrain=factory.initTerrain(strTerrain);


    }
    @Then("Le grain $g1 est a ($x,$y)")
    public void thenGrainAt(String idGrain,int x,int y){
        Assert.assertEquals(grains.get(idGrain).getPosition().x,x);
        Assert.assertEquals(grains.get(idGrain).getPosition().y,y);

    }

    @Then("Le grain $g1 est a (?,$y)")
    public void thenGrainAt(String idGrain,int y){
        Assert.assertEquals(grains.get(idGrain).getPosition().y, y);

    }
    @Then("Le grain $g a pour vitesse ($vx,$vy)")
    public void thenVitesse(String idGrain,int x,int y){
        Assert.assertEquals(grains.get(idGrain).vitesseRestante.getRealX(),x);
        Assert.assertEquals(grains.get(idGrain).vitesseRestante.getRealY(),y);

    }
    @Then("Le grain $g1 n'est pas dans un ensemble")
    public void thenNotInEnsemble(String idGrain){
        Assert.assertNull( grains.get(idGrain).ensemble);
    }
    @Then("Le grain $g1 est dans un ensemble")
    public void thenInEnsemble(String idGrain){
        Assert.assertNotNull(grains.get(idGrain).ensemble);
    }
    @Then("Le grain $g1 est dans l'ensemble $e")
    public void thenInEnsemble(String idGrain,String idEnsemble){
        Assert.assertEquals(grains.get(idGrain).ensemble, objets.get(idEnsemble));

    }
    @Then("Le grain $g1 est dans le meme ensemble que $g2")
    public void thenSameEnsemble(String idGrain1,String idGrain2){
        Assert.assertEquals(grains.get(idGrain1).ensemble,grains.get(idGrain2).ensemble);
    }

    @Then("Le grain $g1 n'est pas dans le meme ensemble que $g2")
    public void thenNotSameEnsemble(String idGrain1,String idGrain2){

        Assert.assertNotSame(grains.get(idGrain1).ensemble, grains.get(idGrain2).ensemble);

    }


}
