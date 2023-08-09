import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Request {

    private String method;
    private String path;
    private List<NameValuePair> query;
    private String body;

    public List getQuery() {
        return query;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
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
        method = parts[0];
        path = StringUtils.substringBefore(parts[1], '?');
        String queryStr = StringUtils.substringAfter(parts[1], '?');

        // getQueryParam and getQueryParams were deprecated, so

        query = URLEncodedUtils.parse(queryStr, StandardCharsets.UTF_8);
    }
}
