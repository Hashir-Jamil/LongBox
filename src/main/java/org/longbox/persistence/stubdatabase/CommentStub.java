package org.longbox.persistence.stubdatabase;

import org.longbox.domainobjects.dto.CommentDTO;

import java.util.ArrayList;
import java.util.List;

public class CommentStub {

    private List<CommentDTO> commentsStubData = new ArrayList<>();

    public void loadComments() {

        CommentDTO comment1 = new CommentDTO(
                "This comic was really good",
                "Always_Scheming",
                "Spider Man"
        );
        commentsStubData.add(comment1);

        CommentDTO comment2 = new CommentDTO(
                "This comic was really bad",
                "Always_Throwing",
                "Spider man"
        );
        commentsStubData.add(comment2);

        CommentDTO comment3 = new CommentDTO(
                "This comic changed my life",
                "Phoenix",
                "Arkham Asylum"
        );
        commentsStubData.add(comment3);
    }
}
