package api.common.exception;

public class InvalidResponseException extends Exception {  //custom exception extending java.lang.Exception class

    public InvalidResponseException(String message) { //constructor that takes a String error message
        super(message); //By calling super(message), we initialize the exception's error message and the base class takes care of setting up the custom message, according to the message.
    }
}
