package behave;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sbuisson on 22/11/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/story/testContext.xml")
public class AllStoriesTest extends JUnitStories {
    @Autowired
    private ApplicationContext context;
    private final CrossReference xref = new CrossReference();

    public AllStoriesTest() {
        configuredEmbedder()//
                .embedderControls()//
                .doGenerateViewAfterStories(true)//
                .doIgnoreFailureInStories(false)//
                .doIgnoreFailureInView(false)//
                .doVerboseFailures(false)//
        ;
    }

    @Override
    public Configuration configuration() {
        // configure as TraderStory except
        Class<? extends Embeddable> embeddableClass = this.getClass();

        StoryReporterBuilder storyReporter = //
                new StoryReporterBuilder() //
                        .withCodeLocation(CodeLocations.codeLocationFromPath(CodeLocations.codeLocationFromClass(this.getClass()).getFile())) //
                        .withFailureTrace(true) //
                        .withFailureTraceCompression(true) //
                        .withCrossReference(xref).withFormats(Format.CONSOLE, Format.STATS, Format.HTML);
        ;
        return new MostUsefulConfiguration().useStoryControls(new StoryControls().doDryRun(false).doSkipScenariosAfterFailure(false))
                .useStoryLoader(new LoadFromURL()).useStoryReporterBuilder(storyReporter)

                .useStepMonitor(xref.getStepMonitor()).useStepPatternParser(new RegexPrefixCapturingPatternParser("%"));


    }

    ;


    /*
        @Override
        protected List<String> storyPaths() {
            String codeLocation = CodeLocations.codeLocationFromClass(this.getClass()).getFile()+"story/";
            return new StoryFinder().findPaths(codeLocation, Arrays.asList("*.story"),
                    null, "story/");
        }*/
    @Override
    protected List<String> storyPaths() {

        String codeLocation = CodeLocations.codeLocationFromClass(this.getClass()).getFile() + "story/";
        return new StoryFinder().findPaths(codeLocation, Arrays.asList("*.story"), null, "file:" + codeLocation);
    }
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                new PourLesGrainsDUneBulle()
        ,new EcoulementGrain());
    }

}
