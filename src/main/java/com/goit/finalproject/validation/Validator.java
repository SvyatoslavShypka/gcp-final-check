package com.goit.finalproject.validation;

import com.goit.finalproject.access.Access;
import com.goit.finalproject.note.Note;
import com.goit.finalproject.note.NoteDto;

public class Validator {

    public static void validateNoteDto(NoteDto noteDto) {
        checkTitle(noteDto.getTitle());
        checkContent(noteDto.getContent());
        checkAccess(noteDto.getAccess());
        checkUserId(noteDto.getUserId());
    }

    public static void validateNote(Note note) {
        checkTitle(note.getTitle());
        checkContent(note.getContent());
        checkAccess(note.getAccess());
        checkUserId(note.getUser().getId());
    }

    private static void checkTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new ValidationException("Note's title can not be empty.");
        }
        if (title.length() > 100 || title.length() < 5) {
            throw new ValidationException("Note's title must have 5-100 length.");
        }
    }

    private static void checkContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new ValidationException("Note's content can not be empty.");
        }
        if (content.length() > 10000 || content.length() < 5) {
            throw new ValidationException("Note's content must have 5-10.000 length.");
        }
    }

    private static void checkAccess(Access access) {
        if (access == null) {
            throw new ValidationException("Note's access has to be chosen.");
        }
    }

    private static void checkUserId(Long userId) {
        if (userId == null) {
            throw new ValidationException("UserId can not be null");
        }
    }
}
