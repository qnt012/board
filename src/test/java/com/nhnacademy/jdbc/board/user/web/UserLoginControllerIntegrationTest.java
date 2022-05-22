package com.nhnacademy.jdbc.board.user.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nhnacademy.jdbc.board.config.RootConfig;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringJUnitConfig(RootConfig.class)
@WebAppConfiguration
@Rollback
public class UserLoginControllerIntegrationTest {
    @Autowired
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserLoginController(userService))
            .build();
    }

    @Test
    @DisplayName("통합테스트 UserService 주입 상태 정상인지 확인.")
    void userServiceNonNullTest(){
        assertThat(userService).isNotNull();
        assertThat(userService.login("user","1234").get().getUserNum()).isEqualTo(2);
    }


    @Test
    @DisplayName("로그인 성공시 지정된 리다이렉션 여부 확인")
    void userDoLoginTestIsSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .param("id", "user")
                .param("pwd", "1234"))
            .andExpect(handler().handlerType(UserLoginController.class))
            .andExpect(handler().methodName("postLogin"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/postView/0"))
            .andReturn();
        assertThat(mvcResult.getRequest().getSession()).isNotNull();
        assertThat(mvcResult.getRequest().getParameter("id")).isEqualTo("user");
    }


    @Test
    @DisplayName("로그인 실패시 지정된 리다이렉션 여부 확인")
    void userDoLoginTestIsNotSuccessful() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .param("id", "user")
                .param("pwd", "1333"))
            .andExpect(handler().handlerType(UserLoginController.class))
            .andExpect(handler().methodName("postLogin"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"))
            .andReturn();
    }


}
