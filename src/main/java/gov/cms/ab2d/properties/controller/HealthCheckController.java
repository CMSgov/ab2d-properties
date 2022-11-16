package gov.cms.ab2d.properties.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
public class HealthCheckController {
    public static final String HEALTH_ENDPOINT = "/health";
    private final DataSource dataSource;
    private final static List<String> URLS = List.of("http://www.google.com", "http://www.facebook.com");

    @GetMapping(HEALTH_ENDPOINT)
    public ResponseEntity<Void> getHealth(HttpServletRequest request) {
        if (healthy()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean healthy() {
        return
                // Check for access to the database
                isDbAvailable(dataSource) &&
                        // Internet is accessible
                        isAnyAvailable(URLS);
    }

    /**
     * Returns true if you can connect to the database and do a simple select
     *
     * @param datasource - the datasource to test
     * @return true if you can access the db, false otherwise
     */
    public static boolean isDbAvailable(DataSource datasource) {
        try (Connection conn = datasource.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("SELECT 1");
                return true;
            } catch (Exception ex) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Given a URL, test to see if you can open a socket to that domain on the specified or default port
     *
     * @param testUrl - The URL to test
     * @return true if you can open a socket to that port on that server
     */
    public static boolean available(String testUrl) {
        try {
            URL url = new URL(testUrl);
            int port = url.getPort() == -1 ? url.getDefaultPort() : url.getPort();
            try (Socket socket = new Socket(url.getHost(), port)) {
                log.debug("Socket opened to " + testUrl + " and is connected: " + socket.isConnected());
            } catch (IOException e) {
                log.info("Unable to open socket to " + testUrl);
                return false;
            }
            return true;
        } catch (MalformedURLException e1) {
            log.info("Malformed URL: " + testUrl);
            return false;
        }
    }

    /**
     * Given a list of URLs, make sure at least one is available
     *
     * @param testUrls - the list of URLs
     * @return true if at least one of them is available
     */
    public static boolean isAnyAvailable(List<String> testUrls) {
        for (String url : testUrls) {
            if (available(url)) {
                return true;
            }
        }

        log.info("No URLs available");

        return false;
    }
}
