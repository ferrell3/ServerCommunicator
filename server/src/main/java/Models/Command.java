package Models;

public class Command {
    private String type;
    private Request input;

    public Command(String type, Request input){
        this.type = type;
        this.input = input;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Request getInput() {
        return input;
    }

    public void setInput(Request input) {
        this.input = input;
    }
}
