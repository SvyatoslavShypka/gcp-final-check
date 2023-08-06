package com.goit.finalproject.note;

import com.goit.finalproject.access.Access;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class NoteDto {
    private Long id;
    private String title;
    private String content;
    private Access access;
    private Long userId;

    public NoteDto(Long id, String title, String content, Access access, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.access = access;
        this.userId = userId;
    }

    public NoteDto(String title, String content, Access access, Long userId) {
        this.title = title;
        this.content = content;
        this.access = access;
        this.userId = userId;
    }

    public NoteDto() {
    }
}
