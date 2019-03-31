package com.mentoring.discolibrary.style;

import com.mentoring.discolibrary.style.create.CreateStyle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class CreateStyleDAOTest {

    private CreateStyleDAO createStyleDAO;

    private CreateStyleModelMapper createStyleModelMapper;
    private StyleRepository styleRepository;

    @Before
    public void setUp() {
        createStyleDAO = new CreateStyleDAO(createStyleModelMapper, styleRepository);
    }

    @Test
    public void should_throwIllegal_when_styleIsNull() {
        //when
        final Throwable throwable = catchThrowable(() -> createStyleDAO.createStyle(null));
        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("StyleDomain is mandatory");
    }

    @Test
    public void should_throwIllegal_when_styleNameIsNull() {
        //when
        final Throwable throwable = catchThrowable(() -> createStyleDAO.createStyle(CreateStyle.builder()
                .name(null)
                .build()));
        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("StyleDomain name is mandatory");
    }

    @Test
    public void should_throwIllegal_when_styleNameIsBlank() {
        //when
        final Throwable throwable = catchThrowable(() -> createStyleDAO.createStyle(CreateStyle.builder()
                .name("")
                .build()));
        //then
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("StyleDomain name is mandatory");
    }

    @Test
    public void should_mapToEntity_when_createStyle() {
        //given
        final CreateStyle createStyle = CreateStyle.builder()
                .build();

        //when
        createStyleDAO.createStyle(createStyle);

        //then
        then(createStyleModelMapper).should().mapToEntity(createStyle);
    }

    @Test
    public void should_saveMappedEntity_when_createStyle() {
        //given
        final CreateStyle createStyle = CreateStyle.builder()
                .build();
        final StyleEntity styleEntity = StyleEntity.builder().build();
        given(createStyleModelMapper.mapToEntity(createStyle)).willReturn(styleEntity);

        //when
        createStyleDAO.createStyle(createStyle);

        //then
        then(styleRepository).should().save(styleEntity);
    }

    @Test
    public void should_mapSavedEntity_when_styleIsSaved() {
        //given
        final CreateStyle createStyle = CreateStyle.builder()
                .build();
        final StyleEntity styleEntity = StyleEntity.builder().build();
        given(createStyleModelMapper.mapToEntity(createStyle)).willReturn(styleEntity);
        given(styleRepository.save(styleEntity)).willReturn(styleEntity);

        //when
        createStyleDAO.createStyle(createStyle);

        //then
        then(createStyleModelMapper).should().mapToDomain(styleEntity);
    }

    @Test
    public void should_returnCreatedStyle_when_styleIsMappedToDomain() {
        //given
        final CreateStyle createStyle = CreateStyle.builder()
                .build();
        final StyleEntity styleEntity = StyleEntity.builder().build();
        final StyleDomain created = StyleDomain.builder().build();

        given(createStyleModelMapper.mapToEntity(createStyle)).willReturn(styleEntity);
        given(styleRepository.save(styleEntity)).willReturn(styleEntity);
        given(createStyleModelMapper.mapToDomain(styleEntity)).willReturn(created);

        //when
        final StyleDomain style = createStyleDAO.createStyle(createStyle);

        //then
        assertThat(style).isEqualTo(created);
    }
}