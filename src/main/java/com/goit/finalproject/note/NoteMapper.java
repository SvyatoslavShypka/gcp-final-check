package com.goit.finalproject.note;

import com.goit.finalproject.user.UserRepository;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class NoteMapper implements Mapper<Note, NoteDto> {
    private final UserRepository userRepository;

    public NoteMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public NoteDto mapEntityToDto(Note source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        NoteDto target = new NoteDto();
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        target.setAccess(source.getAccess());
        target.setUserId(source.getUser().getId());
        return target;
    }

    @Override
    public Note mapDtoToEntity(NoteDto source) {
        if (isNull(source)) {
            return null;
        }
        Note target = new Note();
        target.setId(source.getId());
        target.setTitle(source.getTitle());
        target.setContent(source.getContent());
        target.setAccess(source.getAccess());
        target.setUser(userRepository.findById(source.getUserId()).orElseThrow());
        return target;
    }
}
