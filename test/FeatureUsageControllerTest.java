
import com.codio.feature_usage_mod.controller.FeatureUsageController;
import com.codio.feature_usage_mod.controller.IController;
import com.codio.feature_usage_mod.view.IView;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FeatureUsageControllerTest {

  class MockView implements IView{

    private StringBuffer logs = new StringBuffer();
    private Scanner scanner;

    MockView(Readable in) {
      this.scanner = new Scanner(in);
    }

    @Override
    public String getNextInput() {
      if (scanner.hasNext()) {
        return scanner.next();
      }
      return null;
    }

    @Override
    public void show(Appendable out) {
      logs.append("Received from Controller: ").append(out.toString()).append("\n\n");
    }
  }

  private static final String CONSTRUCTS_PATH = "test/org/examples/ConstructsTest.java";
//  private static final String DATASTRUCTURES_PATH = "test/org/examples/DataStructuresTest.java";
//  private static final String TECHNIQUES_PATH = "test/org/examples/TechniquesTest.java";

  private CompilationUnit constructs_cu;
//  private CompilationUnit ds_cu;
//  private CompilationUnit techniques_cu;

  {
    try {
      constructs_cu = StaticJavaParser.parse(new File(CONSTRUCTS_PATH));
//      ds_cu = StaticJavaParser.parse(new File(DATASTRUCTURES_PATH));
//      techniques_cu = StaticJavaParser.parse(new File(TECHNIQUES_PATH));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private MockView defineView(String input) {
    Readable in = new StringReader(input);
    return new MockView(in);
  }

  @Test
  public void testControllerForClassesConstruct(){
    MockView view = defineView("constructs classes ");
    IController controller = new FeatureUsageController(view, constructs_cu);
    try {
      controller.start();
    } catch (NullPointerException e) {
      // This has been intentionally left blank to ignore this case when testing.
    }
    String expected = "2 classes in Student Code."
            + "\nClass names:\nConstructsTest\nConstructsSubClass\n";
    String actual = view.logs.toString();
    assertTrue(actual.contains(expected));
  }

  @Test
  public void testForConstructorsConstruct(){
    MockView view = defineView("constructs constructors ");
    IController controller = new FeatureUsageController(view, constructs_cu);
    try {
      controller.start();
    } catch (NullPointerException e) {
      // This has been intentionally left blank to ignore this case when testing.
    }
    String expected = "3 constructors in Student Code."
            + "\nConstructor names:\nConstructsTest(String arg)\nprivate ConstructsTest()\n"
            + "ConstructsSubClass(int offset)\n";
    String actual = view.logs.toString();
    assertTrue(actual.contains(expected));

  }
//  @Test
//  public void testControllerForDataStructures(){
//    Readable in = new StringReader("constructs classes ");
//    MockView view = new MockView(in);
//    IController controller = new FeatureUsageController(view, ds_cu);
//    controller.start();
//  }

//  @Test
//  public void testControllerForTechniques(){
//    Readable in = new StringReader("constructs classes ");
//    MockView view = new MockView(in);
//    IController controller = new FeatureUsageController(view, techniques_cu);
//    controller.start();
//  }
}
