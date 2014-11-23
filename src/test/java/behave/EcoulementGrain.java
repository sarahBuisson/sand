package behave;

import fr.perso.sand.action.ActionBouge;
import fr.perso.sand.action.ContracteBulle;
import org.jbehave.core.annotations.When;
import org.mockito.Mockito;

/**
 * Created by sbuisson on 23/11/2014.
 */
public class EcoulementGrain extends AbstractSandBDD {
    ActionBouge action = new ActionBouge();
    ActionBouge actionMock = Mockito.mock(ActionBouge.class);

    @When("Run action on $g1")
    public void RunAction(String idGrain) {
        action.execute(grains.get(idGrain), terrain);
        actionMock.execute(grains.get(idGrain), terrain);
    }
}
