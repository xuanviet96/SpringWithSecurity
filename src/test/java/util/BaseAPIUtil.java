package util;


import org.springframework.http.HttpHeaders;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import static org.testng.Assert.*;

public class BaseAPIUtil {
    private static final Client client = ClientBuilder.newClient();
    private static WebTarget webTarget;
    private static Response response;

    public static Response sendGetRequest(String url, String jwtToken, MultivaluedMap<String, String> params, Integer httpStatus) {
        webTarget = client.target(url);

        for(Map.Entry<String, List<String>> entry : params.entrySet()) {
            if (entry.getValue().size() == 1) {
                webTarget = webTarget.queryParam(entry.getKey(), entry.getValue().get(0));
            } else {
                webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
            }
        }

        response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .get();

        if (httpStatus == 200) {
            assertEquals(response.getStatus(), 200);
        } else {
            assertNotEquals(response.getStatus(), 200);
        }

        return response;
    }

    public static Response sendPostRequest(String url, String jwtToken, Object object, Integer httpStatus) {
        webTarget = client.target(url);

        response = webTarget
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .post(Entity.entity(object, MediaType.APPLICATION_JSON));

        if (httpStatus == 200) {
            assertEquals(response.getStatus(), 200);
        } else {
            assertNotEquals(response.getStatus(), 200);
        }

        return response;
    }
}
