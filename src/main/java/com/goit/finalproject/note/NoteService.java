package com.goit.finalproject.note;

import com.goit.finalproject.access.Access;
import com.goit.finalproject.user.UserDto;
import com.goit.finalproject.user.UserMapper;
import com.goit.finalproject.user.UserService;
import com.goit.finalproject.validation.Validator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    public List<NoteDto> listAll() {
        UserDto userDto = userService.getUserById(userService.getUserId());
        return noteMapper.mapEntityToDto(noteRepository
                .findAllByUser(userMapper.mapDtoToEntity(userDto)));
    }

    public void add(NoteDto noteDto, Long userId) {
        if (noteDto.getUserId() == null) {
            if (userId != null) {
                noteDto.setUserId(userId);
            } else {
                noteDto.setUserId(userService.getUserId());
            }
        }
        Validator.validateNoteDto(noteDto);
        Note note = noteMapper.mapDtoToEntity(noteDto);
        noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        Validator.validateNote(note);
        noteRepository.save(note);
    }

    public NoteDto getById(Long id) {
        return noteMapper.mapEntityToDto(noteRepository.findById(id).orElseThrow());
    }

    public void updateNoteById(Long id, HttpServletRequest request) {
        NoteDto noteDto = getById(id);
        String title = request.getParameter("title");
        if (title == null || title.isEmpty()) {
            title = noteDto.getTitle();
        }
        String content = request.getParameter("content");
        if (content == null || content.isEmpty()) {
            content = noteDto.getContent();
        }
        Access access;
        String accessType = request.getParameter("access");
        if (accessType == null) {
            access = noteDto.getAccess();
        } else {
            access = Access.getAccess(accessType);
        }
        Long userId = userService.getUserId();
        NoteDto updatedNote = new NoteDto(id, title, content, access, userId);
        update(noteMapper.mapDtoToEntity(updatedNote));
    }
}
