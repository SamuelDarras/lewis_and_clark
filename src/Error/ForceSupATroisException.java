package Error;

public class ForceSupATroisException extends Exception{

    @Override
    public String getMessage() {
        return "Force > 3";
    }
}
