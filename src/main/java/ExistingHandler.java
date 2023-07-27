import java.util.ArrayList;
import java.util.List;

public class ExistingHandler {

    private static List<ExistingHandler> existingHandlers = new ArrayList<>();

    public static void addHandlerToHandlersList(ExistingHandler existingHandler) {
        existingHandlers.add(existingHandler);
    }

    public static Handler getHanderByParams(String method, String path) {
        for (ExistingHandler existingHandlerElement: existingHandlers) {
            if (existingHandlerElement.getMethod().equals(method)) {
                if (existingHandlerElement.getPath().equals(path)) {
                    return existingHandlerElement.getHandler();
                }
            }
        }
        return null;
    }

    private final String method;
    private final String path;
    private final Handler handler;

    public ExistingHandler(String method, String path, Handler handler) {
        this.method = method;
        this.path = path;
        this.handler = handler;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Handler getHandler() {
        return handler;
    }
}
