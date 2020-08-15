package engine.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Quiz implements Serializable {

    @Id
    @SequenceGenerator(name = "quizSequence", sequenceName = "QUIZ_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quizSequence")
    @Column(name = "id")
    private long id;

    @NotBlank
    private String title;
    @NotBlank
    private String text;

    @NotEmpty
    @Size(min = 2)
    @ElementCollection
    @CollectionTable(name = "option")
    private List<String> options;

    @ElementCollection
    @CollectionTable(name = "answer")
    private List<Integer> answer;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    private static final long SerialVersionUID = 1;

    public Quiz() {
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() { return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @JsonIgnore
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User user) {
        this.author = user;
    }


    @JsonIgnore
    @JsonProperty(value = "answer")
    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
