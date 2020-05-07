import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        ResourceConfig config = new PackagesResourceConfig("controller");
        HttpServer server = HttpServerFactory.create("http://localhost:8081/",config);
        server.start();
    }
}
