package com.mentoring.discolibrary.style;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class StyleExistsDAOTest {

    private StyleExistsDAO styleExistsDAO;
    private StyleRepository styleRepository;

    @Before
    public void setUp() {
        styleExistsDAO = new StyleExistsDAO(styleRepository);
    }

    @Test
    public void should_throwIllegal_when_nameIsNull() {
        //when
        final Throwable throwable = catchThrowable(() -> styleExistsDAO.isStyleInTheSystem(null));
        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("StyleDomain name is mandatory");
    }

    @Test
    public void should_throwIllegal_when_nameIsBlank() {
        //when
        final Throwable throwable = catchThrowable(() -> styleExistsDAO.isStyleInTheSystem(""));
        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("StyleDomain name is mandatory");
    }

    @Test
    public void should_executeSkillExists_when_isStyleInTheSystem() {
        //given
        final String styleName = "new";

        //when
        styleExistsDAO.isStyleInTheSystem(styleName);

        //then
        then(styleRepository).should().styleExists(styleName);
    }

    @Test
    public void should_returnIfExists_when_styleIsChecked() {
        //given
        final String styleName = "new";
        given(styleRepository.styleExists(styleName)).willReturn(true);

        //when
        final boolean styleInTheSystem = styleExistsDAO.isStyleInTheSystem(styleName);

        //then
        assertThat(styleInTheSystem).isTrue();
    }
}