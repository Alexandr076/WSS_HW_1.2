import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Request {

    private String method;
    private String path;
    private String headers;
    private String body;

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public Request(BufferedReader in, List<String> fileNames) throws IOException {
        final var requestLine = in.readLine();
        final var parts = requestLine.split(" ");

        if (parts.length != 3) {
            return;
        }

        path = parts[1];
        if (!fileNames.contains(path)) {
            return;
        }

        method = parts[0];
    }
}
