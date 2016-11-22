package controllers;

import builder.UserBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.maketest.MakeTestApplication;
import com.maketest.dto.UserDTO;
import com.maketest.model.Session;
import com.maketest.model.SessionRequest;
import com.maketest.model.User;
import com.maketest.model.UserRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jovana Micic on 18-Nov-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MakeTestApplication.class)
@ActiveProfiles("test")
public class UserControllerTest {

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

    //LOGIN

    @Test
    public void testLoginBadRequest() throws Exception {
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

        Session session = sessionService.getSession(token);
        assertNotNull(session);
        assertEquals(token, session.getSessionToken());
    }

    //REGISTRATION
    @Test
    public void testRegisterNewUserBadRequest() throws Exception {
        mockMvc.perform(post("/api/users")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testRegisterNewUserValid() throws Exception {
        UserDTO u = UserBuilder.random().build();  //kreira random podatke kao sa forme
        String content = mapper.writeValueAsString(u);

        MvcResult result = mockMvc.perform(post("/api/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(result);

    }

    @Test
    public void testRegisterNewUserEmailExists() throws Exception {
        UserDTO u = UserBuilder.random().build();  //kreira random podatke kao sa forme
        u.setEmail("test@gmail.com");  //ovo je neki vec uneti mejl
        String content = mapper.writeValueAsString(u);

        mockMvc.perform(post("/api/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //UPDATE
    @Test
    public void testUpdateUserBadRequest() throws Exception {
        mockMvc.perform(put("/api/users")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testUpdateUserValid() throws Exception {
        UserRequest createUpdateRequest = createRequest();
        createUpdateRequest.setLastName("Changed name");
        String content = mapper.writeValueAsString(createUpdateRequest);

        MvcResult result = mockMvc.perform(put("/api/users")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result);

        String retVal = result.getResponse().getContentAsString();
        UserRequest userResponse = mapper.readValue(retVal, UserRequest.class);
        assertEquals(userResponse.getLastName(), "Changed name");

    }

    //GETTING USER
    @Test
    @Transactional
    public void testUserProfileValid() throws Exception {
        //Korisnik se unapred doda u bazu a ovde se vrati userDTO
        UserRequest u = createRequest();
        //Uloguj ga
        User loggedUser = userService.login(new UserDTO(0,u.getEmail(),null,null,u.getPassword(),null)); //saljem samo email i psw
        String token = loggedUser.getUserSession().getSessionToken();
        int id = loggedUser.getId();

        MvcResult result = mockMvc.perform(get("/api/users/{id}",id)
                .header("mtt", token))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result);
    }

    @Test
    @Transactional
    public void testUserProfileWrongId() throws Exception {
        //Korisnik se unapred doda u bazu a ovde se vrati userDTO
        UserRequest u = createRequest();
        //Uloguj ga
        User loggedUser = userService.login(new UserDTO(0,u.getEmail(),null,null,u.getPassword(),null)); //saljem samo email i psw
        String token = loggedUser.getUserSession().getSessionToken();

        int id = 99999999;  //id koji ne postoji u bp

        mockMvc.perform(get("/api/users/{id}", id)
                .header("mtt", token))
                .andExpect(status().isNotFound());

    }

    @Test
    @Transactional
    public void testUserProfileWrongToken() throws Exception {
        //Korisnik se unapred doda u bazu a ovde se vrati userDTO
        UserRequest u = createRequest();
        //Uloguj ga
        User loggedUser = userService.login(new UserDTO(0,u.getEmail(),null,null,u.getPassword(),null));
        String token = loggedUser.getUserSession().getSessionToken()+"wrongToken"; //token nije isti kao sto ga ima user sa tim id-om

        int id = loggedUser.getId();

        mockMvc.perform(get("/api/users/{id}", id)
                .header("mtt", token))
                .andExpect(status().isUnauthorized());
    }

    //GETTING SESSION
    @Test
    @Transactional
    public void testGetTokenValid() throws Exception {
        UserRequest u = createRequest();
        //Uloguj ga
        User loggedUser = userService.login(new UserDTO(0,u.getEmail(),null,null,u.getPassword(),null));
        String token = loggedUser.getUserSession().getSessionToken();

        MvcResult result = mockMvc.perform(get("/api/users/sessions/{token}", token))
                                    .andExpect(status().isOk())
                                    .andReturn();

        assertNotNull(result);

        String retVal = result.getResponse().getContentAsString();
        UserRequest userResponse = mapper.readValue(retVal, UserRequest.class);
        assertEquals((int)loggedUser.getId(), userResponse.getId());
    }

    @Test
    public void testGetTokenNotFound() throws Exception {
        UserRequest u = createRequest();
        //Uloguj ga
        User loggedUser = userService.login(new UserDTO(0,u.getEmail(),null,null,u.getPassword(),null));
        String token = loggedUser.getUserSession().getSessionToken()+"invalidToken"; //token nije isti kao sto ga ima user sa tim id-om

        mockMvc.perform(get("/api/users/sessions/{token}", token))
                .andExpect(status().isNotFound());
    }

    public SessionRequest createAuthenticationRequest() {
        UserDTO u = UserBuilder.random().build();
        saveUser(u);
        return new SessionRequest(u.getEmail(), u.getPassword());
    }

    public UserRequest createRequest() {
        UserDTO u = UserBuilder.random().build();
        saveUser(u);
        return new UserRequest(u.getId(),u.getFirstName(), u.getLastName(), u.getPassword(), u.getEmail());
    }

    public void saveUser(UserDTO u) {
        User user = new User();
        user.setEmail(u.getEmail());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        String hashedPassword = MD5Hash.getMD5(u.getPassword());
        user.setPassword(hashedPassword);
        userService.save(user);
    }


}
