import org.junit.jupiter.api.Tag;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import WEare.privatepart.*;
import WEare.publicpart.*;
import WEare.adminpart.*;


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
