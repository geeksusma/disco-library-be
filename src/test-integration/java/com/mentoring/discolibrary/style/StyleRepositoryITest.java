package com.mentoring.discolibrary.style;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Is a good practice to have integration tests when you add new queries to a jpa repository, in order to detect if your query will work
 * before releasing the code.
 * So, don't forget when you add a custom method to a JPA Repository to add their proper tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(scripts = "classpath:sql/find-all-styles-ordered-sample-data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:sql/clean-database.sql", executionPhase = AFTER_TEST_METHOD)})
@ActiveProfiles("test")
public class StyleRepositoryITest {

    @Autowired
    private StyleRepository styleRepository;

    @Test
    public void should_returnStylesOrderedByName_when_stylesExist() {
        //when
        final List<Style> styles = styleRepository.findAllByOrderByNameAsc();

        //then
        assertThat(styles).extracting("id", "name")
                .containsExactly(
                        tuple(3, "a"),
                        tuple(1, "b"),
                        tuple(4, "c"),
                        tuple(2, "d")
                );
    }
}