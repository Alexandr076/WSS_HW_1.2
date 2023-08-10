import java.io.BufferedOutputStream;
import java.io.IOException;

public class ResponseError {
    public static void getNotFoundError(BufferedOutputStream out) throws IOException {
        out.write((
        "HTTP/1.1 404 Not Found\r\n" +
                "Content-Length: 0\r\n" +
                "Connection: close\r\n" +
                "\r\n"
        ).getBytes());
        out.flush();
    }
}
