package jg.com.gonhub.reviews;

import jg.com.gonhub.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private long id;

    @ManyToOne
    private User user;

    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;


}
