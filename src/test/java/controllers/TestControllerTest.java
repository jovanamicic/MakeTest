package controllers;

import builder.TestBuilder;
import builder.UserBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maketest.MakeTestApplication;
import com.maketest.dto.TestDTO;
import com.maketest.dto.UserDTO;
import com.maketest.model.TestRequest;
import com.maketest.model.Test;
import com.maketest.model.User;
import com.maketest.model.UserRequest;
import com.maketest.repository.UserRepository;
import com.maketest.service.TestService;
import com.maketest.service.UserService;
import com.maketest.utility.MD5Hash;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jovana Micic on 22-Nov-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MakeTestApplication.class)
@ActiveProfiles("test")
@Transactional
public class TestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Autowired
    TestService testService;

    @Autowired
    UserService userService;

    //CREATE NEW TEST

    @org.junit.Test
    public void testAddNewTestBadRequest() throws Exception {

        mockMvc.perform(post("/api/tests")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @org.junit.Test
    public void testAddNewTestValid() throws Exception {
        //uloguj korsnika
        UserRequest u = createRequest();
        User loggedUser = userService.login(new UserDTO(0, u.getEmail(), null, null, u.getPassword(), null)); //saljem samo email i psw
        String token = loggedUser.getUserSession().getSessionToken();
        //napravi random test request
        TestDTO request = TestBuilder.random().build();
        ;
        String content = mapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post("/api/tests")
                .header("mtt", token)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertNotNull(request);
    }

    //UPDATE TEST

    @org.junit.Test
    public void testUpdateTestValid() throws Exception {
        //uloguj korsnika
        UserRequest u = createRequest();
        User loggedUser = userService.login(new UserDTO(0, u.getEmail(), null, null, u.getPassword(), null)); //saljem samo email i psw

        TestRequest request = createTestRequest(loggedUser);
        request.setTestName("Changed name");
        String content = mapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(put("/api/tests")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);

        String retVal = result.getResponse().getContentAsString();
        TestRequest userResponse = mapper.readValue(retVal, TestRequest.class);
        assertEquals(userResponse.getTestName(), "Changed name");
    }

    @org.junit.Test
    public void testUpdateTestBadRequest() throws Exception {
        mockMvc.perform(put("/api/tests")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    //GET ALL TESTS
    @org.junit.Test
    public void testGetAllTestsValid() throws Exception {
        //Pravljenje prvog testa
        UserRequest u = createRequest();
        User loggedUser = userService.login(new UserDTO(0, u.getEmail(), null, null, u.getPassword(), null)); //saljem samo email i psw

        TestRequest request1 = createTestRequest(loggedUser);
        //Pravljenje drugog testa
        TestRequest request2 = createTestRequest(loggedUser);

        MvcResult result = mockMvc.perform(get("/api/tests/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(result);

        int dbSizeAfterInsert = testService.findAll().size();
        assertEquals(3, dbSizeAfterInsert); //jer imam vec jedan rucno ubacen test a gore su snimljena 2 testa
    }

    //GET TEST BY ID
    @org.junit.Test
    public void testGetTestNotFount() throws Exception {
        //Pravljenje testa
        UserRequest u = createRequest();
        User loggedUser = userService.login(new UserDTO(0, u.getEmail(), null, null, u.getPassword(), null)); //saljem samo email i psw
        TestRequest request = createTestRequest(loggedUser);
        int id = 99999;   //ovaj id ne postoji u bazi

        mockMvc.perform(get("/api/tests/{id}", id))
                .andExpect(status().isNotFound());
    }

    @org.junit.Test
    public void testGetTestValid() throws Exception {
        //Pravljenje testa
        UserRequest u = createRequest();
        User loggedUser = userService.login(new UserDTO(0, u.getEmail(), null, null, u.getPassword(), null)); //saljem samo email i psw
        TestRequest request = createTestRequest(loggedUser);
        int id = request.getId();

        MvcResult result = mockMvc.perform(get("/api/tests/{id}", id))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);
    }

    //GET ALL CATEGORIES
    @org.junit.Test
    public void testGetCategoriesValid() throws Exception{
        //Pravljenje testa
        UserRequest u = createRequest();
        User loggedUser = userService.login(new UserDTO(0, u.getEmail(), null, null, u.getPassword(), null)); //saljem samo email i psw
        TestRequest request = createTestRequest(loggedUser);

        MvcResult result = mockMvc.perform(get("/api/tests/categories"))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);
    }

    //GET TEST BY CATEGORY
    @org.junit.Test
    public void testGetTestByCategoryValid() throws Exception{
        //Pravljenje testa
        UserRequest u = createRequest();
        User loggedUser = userService.login(new UserDTO(0, u.getEmail(), null, null, u.getPassword(), null)); //saljem samo email i psw
        TestRequest request = createTestRequest(loggedUser);

        String categoryName = request.getCategory();
        MvcResult result = mockMvc.perform(get("/api/tests/categories/{categoryName}", categoryName))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertNotNull(result);
    }

    private UserRequest createRequest() {
        UserDTO u = UserBuilder.random().build();
        saveUser(u);
        return new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getPassword(), u.getEmail());
    }

    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        String hashedPassword = MD5Hash.getMD5(userDto.getPassword());
        user.setPassword(hashedPassword);
        userService.save(user);
    }

    private TestRequest createTestRequest(User loggedUser) {
        TestDTO t = TestBuilder.random().build();
        com.maketest.model.Test test = saveTest(t, loggedUser);
        return new TestRequest(test.getId(), test.getTestName(), test.getDescription(), test.getCategory());
    }

    private com.maketest.model.Test saveTest(TestDTO t, User loggedUser) {
        com.maketest.model.Test test = new com.maketest.model.Test();
        test.setTestName(t.getTestName());
        test.setCategory(t.getCategory());
        test.setDescription(t.getDescription());
        User user = userService.findOne(loggedUser.getEmail());
        test.setUserTests(user);
        test.setCreatingDate(new Date());
        testService.save(test);
        return test;
    }


}
