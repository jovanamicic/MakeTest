package controllers;

import builder.UserBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.maketest.MakeTestApplication;
import com.maketest.dto.UserDTO;
import com.maketest.model.Session;
import com.maketest.model.SessionRequest;
import com.maketest.model.User;
import com.maketest.repository.UserRepository;
import com.maketest.service.SessionService;
import com.maketest.service.UserService;
import com.maketest.utility.MD5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import service.UserUtilityService;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jovana Micic on 18-Nov-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MakeTestApplication.class)
@SpringBootTest(classes = MakeTestApplication.class)
@ActiveProfiles("test")
public class UserControllerTest{

    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //LOGIN TESTS

    @Test
    public void testLoginBadRequest() throws Exception{
        mockMvc.perform(post("/api/users/sessions")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testLoginValid() throws Exception {
        SessionRequest createLoginRequest = createAuthenticationRequest();
        String content = mapper.writeValueAsString(createLoginRequest);

        MvcResult result = mockMvc.perform(post("/api/users/sessions")
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isCreated())
                            .andReturn();

        assertNotNull(result.getResponse().getHeader("Location"));
        String[] locationSegments = result.getResponse().getHeader("Location").split("/");
        String token = locationSegments[locationSegments.length - 1];

        Session session = sessionService.getToken(token);
        assertNotNull(session);
        assertEquals(token, session.getSessionToken());
    }

    public SessionRequest createAuthenticationRequest() {
        UserDTO u = UserBuilder.random().build();
        saveUser(u);
        return new SessionRequest(u.getEmail(), u.getPassword());
    }

    public void saveUser(UserDTO u){
        User user = new User();
        user.setEmail(u.getEmail());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        String hashedPassword = MD5Hash.getMD5(u.getPassword());
        user.setPassword(hashedPassword);
        userService.save(user);
    }

    //REGISTRATION TESTS

}
