import java.io.BufferedOutputStream;

public class Main {
    public static void main(String[] args){

        // Задача ещё не готова, в процессе разработки

        final var server = new Server();

        server.addHandler("GET", "/messages", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                // TODO: handlers code
            }
        });
        server.addHandler("POST", "/messages", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                // TODO: handlers code
            }
        });

        server.listen(9999);
    }
}