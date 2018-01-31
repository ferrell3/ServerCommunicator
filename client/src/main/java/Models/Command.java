package Models;

public class Command {
    private String type;
    private Request input;

    public Command(String type, Request input){
        this.type = type;
        this.input = input;
    }
}
