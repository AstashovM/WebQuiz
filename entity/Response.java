package engine.entity;

import java.util.List;
import java.util.Objects;

public class Response {
    private static String FEEDBACK_SUCCESS = "Congratulations, you're right!";
    private static String FEEDBACK_FAIL = "Wrong answer! Please, try again.";

    private static Response SUCCESS = new Response(true, FEEDBACK_SUCCESS);
    private static Response FAIL = new Response(false, FEEDBACK_FAIL);

    private boolean success;
    private String feedback;

    private Response(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }

    public static Response getSuccess() {
        return SUCCESS;
    }

    public static Response getFail() {
        return FAIL;
    }
    
    public static Response check (List<Integer> answ1, List<Integer> answ2) {
        if (Objects.equals(answ1, answ2)) {
           return getSuccess();
        } else 
            return getFail();
    }
}