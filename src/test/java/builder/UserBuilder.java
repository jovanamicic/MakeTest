package builder;

import com.github.mirreck.FakeFactory;
import com.maketest.dto.UserDTO;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 18-Nov-16.
 */
public class UserBuilder {
    private static FakeFactory fakeFactory = new FakeFactory();
    private UserDTO user;

    protected UserBuilder(UserDTO user){
        this.user = user;
    }

    public static UserBuilder random(){
        UserDTO user = new UserDTO();
        user.setEmail(fakeFactory.email());
        user.setPassword(fakeFactory.letters(20));
        user.setFirstName(fakeFactory.firstName());
        user.setLastName(fakeFactory.lastName());
        return new UserBuilder(user);
    }

    public UserDTO build(){
        return user;
    }
}
