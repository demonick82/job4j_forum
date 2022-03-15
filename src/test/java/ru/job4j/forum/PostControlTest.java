package ru.job4j.forum;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest (classes = ForumApplication.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class PostControlTest {

    @Autowired
    private  MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnCreateMessage() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/create"));
    }

    @Test
    @WithMockUser
    public void shouldReturnMyPostsMessage() throws Exception {
        this.mockMvc.perform(get("/myPosts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/myPosts"));
    }
}
