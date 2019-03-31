package com.mentoring.discolibrary.style.create;

import com.mentoring.discolibrary.style.StyleDomain;
import com.mentoring.discolibrary.style.StyleDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CreateStyleControllerTest {

    private CreateStyleController createStyleController;
    @Mock
    private CreateSkillApiMapper createSkillApiMapper;
    @Mock
    private CreateStyleService createStyleService;

    @Before
    public void setUp() {
        createStyleController = new CreateStyleController(createSkillApiMapper, createStyleService);
    }

    @Test
    public void should_mapToStyle_when_requestIsValid() {
        //given
        final CreateStyleRequest createStyleRequest = CreateStyleRequest.builder().build();

        //when
        createStyleController.createStyle(createStyleRequest);

        //then
        then(createSkillApiMapper).should().mapToDomain(createStyleRequest);
    }

    @Test
    public void should_createMappedStyle_when_requestIsValid() {
        //given
        final CreateStyleRequest createStyleRequest = CreateStyleRequest.builder().build();
        final CreateStyle newStyle = CreateStyle.builder().build();

        given(createSkillApiMapper.mapToDomain(createStyleRequest)).willReturn(newStyle);

        //when
        createStyleController.createStyle(createStyleRequest);

        //then
        then(createStyleService).should().createStyle(newStyle);
    }

    @Test
    public void should_mapToDto_when_styleIsCreated() {
        //given
        final CreateStyleRequest createStyleRequest = CreateStyleRequest.builder().build();
        final CreateStyle newStyle = CreateStyle.builder().build();
        final StyleDomain createdStyle = mock(StyleDomain.class);

        given(createSkillApiMapper.mapToDomain(createStyleRequest)).willReturn(newStyle);
        given(createStyleService.createStyle(newStyle)).willReturn(createdStyle);

        //when
        createStyleController.createStyle(createStyleRequest);

        //then
        then(createSkillApiMapper).should().mapToDto(createdStyle);
    }

    @Test
    public void should_returnOk_when_styleIsCreated() {
        //given
        final CreateStyleRequest createStyleRequest = CreateStyleRequest.builder().build();
        final CreateStyle newStyle = CreateStyle.builder().build();
        final StyleDomain createdStyle = mock(StyleDomain.class);
        final StyleDTO expectedDTO = StyleDTO.builder().build();

        given(createSkillApiMapper.mapToDomain(createStyleRequest)).willReturn(newStyle);
        given(createStyleService.createStyle(newStyle)).willReturn(createdStyle);
        given(createSkillApiMapper.mapToDto(createdStyle)).willReturn(expectedDTO);

        //when
        final ResponseEntity<StyleDTO> response = createStyleController.createStyle(createStyleRequest);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void should_returnDto_when_styleIsCreated() {
        //given
        final CreateStyleRequest createStyleRequest = CreateStyleRequest.builder().build();
        final CreateStyle newStyle = CreateStyle.builder().build();
        final StyleDomain createdStyle = mock(StyleDomain.class);
        final StyleDTO expectedDTO = StyleDTO.builder().build();

        given(createSkillApiMapper.mapToDomain(createStyleRequest)).willReturn(newStyle);
        given(createStyleService.createStyle(newStyle)).willReturn(createdStyle);
        given(createSkillApiMapper.mapToDto(createdStyle)).willReturn(expectedDTO);

        //when
        final ResponseEntity<StyleDTO> response = createStyleController.createStyle(createStyleRequest);

        //then
        assertThat(response.getBody()).isEqualTo(expectedDTO);
    }

    @Test
    public void should_returnConflict_when_styleIsDuplicated() {
        //given
        final CreateStyleRequest createStyleRequest = CreateStyleRequest.builder().build();
        final CreateStyle newStyle = CreateStyle.builder().build();
        given(createSkillApiMapper.mapToDomain(createStyleRequest)).willReturn(newStyle);
        given(createStyleService.createStyle(newStyle)).willThrow(new StyleDuplicatedException("is duplicated"));

        //when
        final ResponseEntity<StyleDTO> response = createStyleController.createStyle(createStyleRequest);


        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }
}