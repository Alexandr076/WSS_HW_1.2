import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private ConcurrentHashMap <HashMap<String, String>, Handler> handlers = new ConcurrentHashMap<>();
    private HashMap<String, String> methodAndPath = new HashMap<>();
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
                            final var out = new BufferedOutputStream(socket.getOutputStream());
                            Request request = new Request(in, out);

                            for (Map.Entry<HashMap<String, String>, Handler> entry: handlers.entrySet()) {
                                HashMap<String, String> key = entry.getKey();
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
        methodAndPath.put(method, path);
        handlers.put(methodAndPath, handler);
    }
}
