package jg.com.gonhub.reviews;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewEditRequest {
    private String title;
    private String content;
    private List<Long> uploadedPictures;
    private List<Long> deletedPictures;
}
