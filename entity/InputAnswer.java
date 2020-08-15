package engine.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InputAnswer {

    private List<Integer> answer;

    public InputAnswer() {
    }

    public InputAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    @JsonProperty(value = "answer")
    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
