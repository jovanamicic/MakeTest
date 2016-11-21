package com.maketest.service_implementations;

import com.maketest.MakeTestApplication;
import com.maketest.model.User;
import com.maketest.service.UserService;
import constants.UserConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Jovana Micic on 18-Nov-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MakeTestApplication.class)
@WebIntegrationTest
//@TestPropertySource(locations = "classpath:test.properties") //koristi se test baza

public class UserServiceImplementationTest {

    @Autowired
    UserService userService;

    @org.junit.Test
    public void findOne() throws Exception {
        User dbUser = userService.findOne(UserConstants.DB_EMAIL);
        assertThat(dbUser).isNotNull();

        assertThat(dbUser.getEmail()).isEqualTo(UserConstants.DB_EMAIL);
        assertThat(dbUser.getId()).isEqualTo(UserConstants.DB_ID);
        assertThat(dbUser.getFirstName()).isEqualTo(UserConstants.DB_FIRST_NAME);
        assertThat(dbUser.getLastName()).isEqualTo(UserConstants.DB_LAST_NAME);
        assertThat(dbUser.getProfilePhotoRelativePath()).isEqualTo(UserConstants.DB_PROFILE_PHOTO_PATH);
        assertThat(dbUser.getToken()).isEqualTo(UserConstants.DB_TOKEN);
        assertThat(dbUser.getTokenExpireDate()).isEqualTo(UserConstants.DB_TOKEN_EXPIRE_DATE);
    }
}