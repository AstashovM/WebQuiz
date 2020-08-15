package engine.service;

import engine.entity.Quiz;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuizService {

    Quiz getQuizByID (long id) throws NotFoundException;
    Page<Quiz> getAll(Pageable pageable);
    void addQuiz(Quiz quiz);

}
