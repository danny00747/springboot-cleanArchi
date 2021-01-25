package be.rentvehicle.service.mapper;

import be.rentvehicle.domain.Teacher;
import be.rentvehicle.service.dto.TeacherDTO;
import org.mapstruct.*;


/**
 * Mapper for the entity {@link Teacher} and its DTO {@link TeacherDTO}.
 */
@Mapper(componentModel = "spring")
public non-sealed interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {

    @Mappings({
            @Mapping(source = "teacherId", target = "id"),
            @Mapping(source = "teacherName", target = "name"),
            @Mapping(source = "teacherEmail", target = "email")
    })
    Teacher toEntity(TeacherDTO teacherDTO);

    @Mappings({
            @Mapping(source = "id", target = "teacherId"),
            @Mapping(source = "name", target = "teacherName"),
            @Mapping(source = "email", target = "teacherEmail")
    })
    TeacherDTO toDto(Teacher teacher);

    default Teacher fromId(int id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}