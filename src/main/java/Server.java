import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static List<String> fileNames = new ArrayList<>();

    public Server(List<String> fileNames) {
        Server.fileNames = fileNames;
    }

    private static final int COUNT_OF_CONNECTIONS = 64;
    public static ExecutorService executorService = Executors.newFixedThreadPool(COUNT_OF_CONNECTIONS);
    private static Socket socket = new Socket();

    public void listen(int port) {
        try (final var serverSocket = new ServerSocket(port)) {
            while (true) {
                socket = serverSocket.accept();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            Request request = new Request(in);

                            String requestMethod = request.getMethod();
                            String requestPath = request.getPath();
                            System.out.println("hi");
                            System.out.println(requestMethod + " " + requestPath);
                            Handler existingHander = ExistingHandler.getHanderByParams(requestMethod, requestPath);
                            if (existingHander != null) {
                                existingHander.handle(request,
                                        new BufferedOutputStream(socket.getOutputStream()));
                            }
                        } catch (IOException e) {
                            System.out.println("Error inside thread");
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHandler(String method, String path, Handler handler) {
        ExistingHandler.addHandlerToHandlersList(new ExistingHandler(method, path, handler));
    }
}
