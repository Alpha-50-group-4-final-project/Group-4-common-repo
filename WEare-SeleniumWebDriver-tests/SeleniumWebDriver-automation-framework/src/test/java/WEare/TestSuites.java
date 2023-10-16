package WEare;
import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
public class TestSuites {


    @Tag("Happy path")
    @Suite
    @SelectPackages({"adminpart","privatepart","publicpart"})
    public static class SmokeTestSuite {
    }

    @Tag("ui")
    @Suite
    @SelectPackages("com.example.tests.ui")
    public class UITestSuite {

    }

}
