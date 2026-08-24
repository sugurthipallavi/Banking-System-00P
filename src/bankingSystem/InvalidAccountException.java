package bankingSystem;

//Custom Exception for Banking Operations
public class InvalidAccountException extends Exception {

 public InvalidAccountException(String message) {
     super(message);
 }
}