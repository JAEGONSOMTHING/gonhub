package jg.com.gonhub.reviews;

import jg.com.gonhub.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private long id;


    private String content;

    @ManyToOne
    private User user;

}
