import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ PlayMusicTest.class, AddMusicTest.class, AddManyMusicTest.class, DeleteMusicTest.class })
public class MusicSuiteTest {
}
