
import com.codio.feature_usage_mod.controller.FeatureUsageController;
import com.codio.feature_usage_mod.controller.IController;
import com.codio.feature_usage_mod.view.FeatureUsageView;
import com.codio.feature_usage_mod.view.IView;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class FeatureUsageControllerTest {

  private static final String CONSTRUCTS_PATH = "test/org/examples/ConstructsTest.java";
  private static final String DATASTRUCTURES_PATH = "test/org/examples/DataStructures.java";
  private static final String TECHNIQUES_PATH = "test/org/examples/TechniquesTest.java";

  private CompilationUnit constructs_cu;
  private CompilationUnit ds_cu;
  private CompilationUnit techniques_cu;

  {
    try {
      constructs_cu = StaticJavaParser.parse(new File(CONSTRUCTS_PATH));
      ds_cu = StaticJavaParser.parse(new File(DATASTRUCTURES_PATH));
      techniques_cu = StaticJavaParser.parse(new File(TECHNIQUES_PATH));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private Readable in = new InputStreamReader(System.in);
  private IView view = new FeatureUsageView(in);

  @Test
  public void testControllerForConstructs(){
    IController controller = new FeatureUsageController(view, constructs_cu);
    controller.start();
  }

  @Test
  public void testControllerForDataStructures(){
    IController controller = new FeatureUsageController(view, ds_cu);
    controller.start();
  }

  @Test
  public void testControllerForTechniques(){
    IController controller = new FeatureUsageController(view, techniques_cu);
    controller.start();
  }
}
