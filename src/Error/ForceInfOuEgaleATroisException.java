package Error;

public class ForceInfOuEgaleATroisException extends Exception {
    @Override
    public String getMessage() {
        return "Force <= 0";
    }
}
