package fer.or.api.util;

import org.springframework.http.HttpHeaders;

public class HeaderUtil {
    public static HttpHeaders createHeaders(String status, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("status", status);
        headers.set("message", message);

        return headers;
    }
}
