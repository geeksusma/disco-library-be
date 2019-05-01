package com.mentoring.discolibrary.style.get;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentoring.discolibrary.style.GetAllStylesDAO;
import com.mentoring.discolibrary.style.StyleDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({@Sql(scripts = "classpath:sql/get-all-styles-sample-data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:sql/clean-database.sql", executionPhase = AFTER_TEST_METHOD)})
@ActiveProfiles("test")
public class GetAllStylesControllerITest {

    private static final String ENDPOINT = "/styles";
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    private StyleApiMapper styleApiMapper;

    @Autowired
    private GetAllStylesDAO getAllStylesDAO;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new GetAllStylesController(getAllStylesDAO, styleApiMapper)).build();
        objectMapper = new ObjectMapper();

    }

    @Test
    public void should_returnOkHttpStatus_when_passwordAndEmailAreCorrect() throws Exception {


        // when
        final String responseAsString = mockMvc.perform(get(ENDPOINT))
                // then
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        final List<StyleDTO> styles = objectMapper.readValue(responseAsString, new TypeReference<List<StyleDTO>>() {
        });

        assertThat(styles).extracting("id", "name")
                .containsExactly(
                        tuple(4, "flamenco"),
                        tuple(2, "heavy"),
                        tuple(3, "punk"),
                        tuple(1, "rock"));
    }

}