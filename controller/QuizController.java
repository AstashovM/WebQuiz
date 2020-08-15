package engine.controller;

import engine.entity.InputAnswer;
import engine.entity.Quiz;
import engine.entity.Response;
import engine.entity.User;
import engine.service.QuizServiceImpl;
import engine.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
public class QuizController {

    @Autowired
    UserService userService;

    @Autowired
    QuizServiceImpl quizService;


    @PostMapping(value = "/api/quizzes", consumes = "application/json")
        public Quiz addQuiz (@Valid @RequestBody Quiz quiz) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByEmail(authentication.getName());
        quiz.setAuthor(currentUser);
        quizService.addQuiz(quiz);
        return quiz;
    }


    @PostMapping("/api/quizzes/{id}/solve")
    public Response solveTask(@PathVariable("id") long id, @RequestBody InputAnswer answer) throws NotFoundException {
        return Response.check(answer.getAnswer(), getByID(id).getAnswer());
    }

    @GetMapping("/api/quizzes")
    public Page<Quiz> getAll (@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        return quizService.getAll(pageable);
    }


    @GetMapping("/api/quizzes/{id}")
    public Quiz getByID (@PathVariable("id") long id) throws NotFoundException {
        return quizService.getQuizByID(id);
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity deleteByID(@PathVariable("id") long id) throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByEmail(authentication.getName());
        if (getByID(id) == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (getByID(id).getAuthor().equals(currentUser)) {
            quizService.deleteByID(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
