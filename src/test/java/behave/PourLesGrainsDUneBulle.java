package behave;

import fr.perso.sand.action.Action;
import fr.perso.sand.action.ActionCreationBulle;
import fr.perso.sand.action.ContracteBulle;
import fr.perso.sand.model.*;
import fr.perso.sand.service.BulleService;
import fr.perso.sand.service.Factory;
import fr.perso.sand.service.ZoneBulle;
import junit.framework.Assert;
import org.jbehave.core.annotations.*;
import org.mockito.MockSettings;
import org.mockito.Mockito;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sbuisson on 22/11/2014.
 */
@Service
public class PourLesGrainsDUneBulle extends AbstractSandBDD {

    BulleService mockedBulleService;
    BulleService bulleService;

    ContracteBulle action;
    ContracteBulle actionMock;
    Terrain terrain;
    Terrain terrainMock;

    @BeforeStory
    public void init(){
         mockedBulleService= Mockito.mock(BulleService.class);
         bulleService=new BulleService();

         action=new ContracteBulle();
         actionMock=Mockito.mock(ContracteBulle.class);
         terrain=factory.initTerrain(10, 10);
        terrainMock=Mockito.mock(Terrain.class);
    }


    @Given("Le grain $g1 est dans la zone $zone")

    public void givenGrainZoneInterne(String idGrain,ZoneBulle zone){
        givenGrain(idGrain);
        Mockito.when(mockedBulleService.zoneOf(Mockito.eq(grains.get(idGrain)))).thenReturn(zone);

    }
    @Given("Le grain $g1 est, par rapport à $e2, dans la zone $interne")

    public void givenGrainZoneInterne(String idGrain,String idEnsemble,ZoneBulle zone){
        givenGrain(idGrain);
        Mockito.when(mockedBulleService.zoneOf(Mockito.eq(grains.get(idGrain)), (Ensemble) Mockito.eq(objets.get(idEnsemble)))).thenReturn(zone);

    }


@Given("$g dans l'ensemble $e")
public void givenEnsemble(String idGrain,String idEnsemble){
    Grain grain=givenGrain(idGrain);
    ((Ensemble)objets.get(idEnsemble)).addElement(grain);

}



    @Given("Un ensemble $e de centre ($x,$y) de taille $size")
    public void givenEnsemble(String idEnsemble,int x,int y,int size){
        Ensemble ensemble=Mockito.mock(Ensemble.class);
        Collection<Grain> contenu=Mockito.mock(ArrayList.class);
        Mockito.when(ensemble.getCentre()).thenReturn(new VecteurInvariant(x,y));
        Mockito.when(ensemble.getContenu()).thenReturn(contenu);
        Mockito.when(contenu.size()).thenReturn(size);
        Mockito.when(contenu.iterator()).thenReturn(grains.values().iterator());
        objets.put(idEnsemble,ensemble);



    }


    @When("Run ContracteBulle sur $e")
    public void RunAction(String idEnsemble){
        Ensemble ensemble= (Ensemble) objets.get(idEnsemble);
        action.execute(ensemble,terrain);
        actionMock.execute(ensemble,terrain);
    }
    @When("Run ActionCreation sur $g")
    public void RunActionCreation(String idGrain){

        new ActionCreationBulle().execute(grains.get(idGrain),terrain);
        Ensemble ensemble=grains.get(idGrain).ensemble;


    }
    @When("Le grain $g1 est lissé")

    public void whenLissage(String idGrain){
        Grain grain=grains.get(idGrain);
       action.lisseGrain(grain);
    }

    @When("Le grain $g1 se rapproche")
    public void whenRapproche(String idGrain){
        Grain grain=grains.get(idGrain);
        action.rapprocheGrain(grain);
    }
    @When("Le grain $g1 quitte l'ensemble")
    public void whenQuitte(String idGrain){
        Grain grain=grains.get(idGrain);
        action.quitteGrain(grain);
    }

    @Then("Le grain $g1 est lissé")

    public void thenLissage(String idGrain){
        Grain grain=grains.get(idGrain);
        Mockito.verify(action,Mockito.times(1)).lisseGrain(Mockito.eq(grain));
    }
    @Then("Le grain $g1 se rapproche")
    public void thenRapproche(String idGrain){
        Grain grain=grains.get(idGrain);
        Mockito.verify(action,Mockito.times(1)).rapprocheGrain(Mockito.eq(grain));
    }
    @Then("Le grain $g1 quitte l'ensemble")
        public void thenQuitte(String idGrain){
        Grain grain=grains.get(idGrain);
        Mockito.verify(action,Mockito.times(1)).quitteGrain(Mockito.eq(grain));
    }

    @Then("Le grain $g est dans la zone $zone")
    public void dansLaZone(String idGrain,ZoneBulle zone){
        Grain grain=grains.get(idGrain);
        Assert.assertEquals(bulleService.zoneOf(grain),zone);
    }

    @Then("fusion de $e1 et $e2")
    public void thenFusion(String e1,String e2){
        Ensemble ensemble1= (Ensemble) objets.get(e1);
        Ensemble ensemble2= (Ensemble) objets.get(e2);
        Mockito.verify(terrainMock,Mockito.times(1)).fusion(Mockito.eq(ensemble1),Mockito.eq(ensemble2));
    }

}
