
import org.junit.platform.suite.api.IncludeTags;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;



public class TestSuites {


    @IncludeTags("HappyPath")
    @Suite
    @SelectPackages({"WEare.adminpart", "WEare.privatepart", "WEare.publicpart"})
    public static class HappyPathTestSuite  {
    }

    @IncludeTags("UnHappyPath")
    @Suite
    @SelectPackages({"WEare.adminpart", "WEare.privatepart", "WEare.publicpart"})
    public static class UnhappyPathTestSuite {

    }

}
