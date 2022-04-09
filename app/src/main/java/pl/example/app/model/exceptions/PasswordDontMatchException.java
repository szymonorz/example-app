package pl.example.app.model.exceptions;

public class PasswordDontMatchException extends Exception{
    public PasswordDontMatchException(String msg){
        super(msg);
    }
}
