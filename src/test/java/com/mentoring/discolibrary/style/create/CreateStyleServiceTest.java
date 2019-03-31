package com.mentoring.discolibrary.style.create;

import com.mentoring.discolibrary.style.CreateStyleDAO;
import com.mentoring.discolibrary.style.StyleDomain;
import com.mentoring.discolibrary.style.StyleExistsDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CreateStyleServiceTest {

    private CreateStyleService createStyleService;
    private StyleExistsDAO styleExistsDAO;
    private CreateStyleDAO createStyleDAO;

    @Before
    public void setUp() {
        createStyleService = new CreateStyleService(styleExistsDAO, createStyleDAO);
    }

    @Test
    public void should_checkIfStyleExists_when_createStyle() {
        //given
        final String newStyle = "new style";
        final CreateStyle createStyle = CreateStyle.builder()
                .name(newStyle)
                .build();

        //when
        createStyleService.createStyle(createStyle);

        //then
        then(styleExistsDAO).should().isStyleInTheSystem(newStyle);
    }

    @Test
    public void should_throwException_when_styleIsDuplicated() {
        //given
        final String newStyle = "new style";
        final CreateStyle createStyle = CreateStyle.builder()
                .name(newStyle)
                .build();
        given(styleExistsDAO.isStyleInTheSystem(newStyle)).willReturn(true);

        //when
        final Throwable throwable = catchThrowable(() -> createStyleService.createStyle(createStyle));

        //then
        assertThat(throwable).isInstanceOf(StyleDuplicatedException.class).hasMessageContaining("The style is already in the system");
    }

    @Test
    public void should_saveStyle_when_doesNotExists() {
        //given
        final String newStyle = "new style";
        final CreateStyle createStyle = CreateStyle.builder()
                .name(newStyle)
                .build();
        given(styleExistsDAO.isStyleInTheSystem(newStyle)).willReturn(false);

        //when
        createStyleService.createStyle(createStyle);

        //then
        then(createStyleDAO).should().createStyle(createStyle);
    }

    @Test
    public void should_returnCreatedStyle_when_styleIsCreated() {
        final String newStyle = "new style";
        final CreateStyle createStyle = CreateStyle.builder()
                .name(newStyle)
                .build();
        final StyleDomain createdStyle = mock(StyleDomain.class);

        given(styleExistsDAO.isStyleInTheSystem(newStyle)).willReturn(false);
        given(createStyleDAO.createStyle(createStyle)).willReturn(createdStyle);

        //when
        final StyleDomain style = createStyleService.createStyle(createStyle);

        //then
        assertThat(style).isEqualTo(createdStyle);
    }
}