package org.longbox.persistence.stubdatabase;

import org.longbox.domainobjects.dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

public class CommentStub {

    private List<CommentDto> commentsStubData = new ArrayList<>();

    public void loadComments() {

        CommentDto comment1 = new CommentDto(
                "This comic was really good",
                "Always_Scheming",
                "Spider Man"
        );
        commentsStubData.add(comment1);

        CommentDto comment2 = new CommentDto(
                "This comic was really bad",
                "Always_Throwing",
                "Spider man"
        );
        commentsStubData.add(comment2);

        CommentDto comment3 = new CommentDto(
                "This comic changed my life",
                "Phoenix",
                "Arkham Asylum"
        );
        commentsStubData.add(comment3);
    }
}
