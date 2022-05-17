import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ PlayMusicTest.class, AddMusicTest.class, DeleteMusicTest.class })
public class MusicSuiteTest {
}
