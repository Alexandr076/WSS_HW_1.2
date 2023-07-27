import java.io.BufferedOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<String> initializeFiles() {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(System.getProperty("user.dir")+"\\public");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles.length > 0) {
            for (File file: listOfFiles) {
                fileNames.add("/" + file.getName());
            }
        }
        return fileNames;
    }


    public static void main(String[] args){

        List<String> fileNames = initializeFiles();

        final var server = new Server(fileNames);

        server.addHandler("GET", "/spring.svg", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                // TODO: handlers code
                System.out.println("hello from GET");
            }
        });
        server.addHandler("POST", "/messages", new Handler() {
            public void handle(Request request, BufferedOutputStream responseStream) {
                // TODO: handlers code
                System.out.println("hello from POST");
            }
        });

        server.listen(9999);
    }
}