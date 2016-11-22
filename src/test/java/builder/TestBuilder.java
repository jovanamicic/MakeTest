package builder;

import com.github.mirreck.FakeFactory;
import com.maketest.dto.TestDTO;
import org.junit.Test;

/**
 * Created by Jovana Micic on 22-Nov-16.
 */
public class TestBuilder {
    private static FakeFactory fakeFactory = new FakeFactory();
    private TestDTO test;

    protected TestBuilder(TestDTO test) {this.test = test;}

    public static TestBuilder random(){
        TestDTO test = new TestDTO();
        test.setId(Integer.parseInt(fakeFactory.digits(3)));
        test.setCategory(fakeFactory.letters(10));
        test.setDescription(fakeFactory.sentence(20));
        test.setTestName(fakeFactory.name()+" test");
        return new TestBuilder(test);
    }

    public TestDTO build(){return test;}
}
