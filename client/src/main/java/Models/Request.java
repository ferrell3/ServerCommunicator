package Models;

public class Request {
    private String data;

    public Request(String data) {
        this.data = data;
    }

    public Request() {}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
