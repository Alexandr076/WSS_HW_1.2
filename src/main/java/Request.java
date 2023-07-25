import java.io.BufferedOutputStream;
import java.io.BufferedReader;


public class Request {
    private BufferedReader in;
    private BufferedOutputStream out;

    private String method;
    private String headers;
    private String body;


    public Request(BufferedReader in, BufferedOutputStream out) {
        this.in = in;
        this.out = out;
    }
}
