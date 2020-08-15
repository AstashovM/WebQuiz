package engine.service;

import engine.entity.Quiz;
import engine.exception.QuizNotFoundException;
import engine.repository.QuizRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Override
    public Quiz getQuizByID(long id) throws NotFoundException {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isPresent()) {
            return quiz.get();
        }
        throw new QuizNotFoundException();
    }

    @Override
    public Page<Quiz> getAll(Pageable pageable) {
        return quizRepo.findAll(pageable);
    }

    @Override
    public void addQuiz(Quiz quiz) {
        quizRepo.save(quiz);
    }

    public void deleteAll(){
        quizRepo.deleteAll();
    }

    public void deleteByID(long id) {
        quizRepo.deleteById(id);
    }
}

