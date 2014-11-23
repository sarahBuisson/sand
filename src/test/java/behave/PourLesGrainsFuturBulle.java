package behave;

import fr.perso.sand.action.ActionCreationBulle;
import fr.perso.sand.action.ContracteBulle;
import fr.perso.sand.service.BulleService;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Then;
import org.mockito.Mockito;

/**
 * Created by sbuisson on 23/11/2014.
 */
public class PourLesGrainsFuturBulle extends AbstractSandBDD {
    ActionCreationBulle action;
    ActionCreationBulle actionMock;

    @BeforeStory
    public void init(){

        action=new ActionCreationBulle();
        actionMock=Mockito.mock(ActionCreationBulle.class);

    }

}
