package spring.gradle.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Utilities methods
 */
public class Utilities {

    /**
     * Method to validate server SQS connectivity.
     * @param url url string
     * @return boolean
     * @throws URISyntaxException uri syntax exception
     */
    public static boolean isReachable(String url) throws URISyntaxException {
        int ttl = 3000;

        URI uri = new URI(url);
        if (uri.getPort() > 0) {
            return isReachable(uri.getHost(), uri.getPort(), ttl);
        }
        return isReachable(uri.getHost(), uri.getScheme().equalsIgnoreCase("https") ? 443 : 80, ttl);
    }

    /**
     * Socket connect to the server host
     * @param host host string
     * @param openPort port
     * @param timeOutMillis timeout in milli seconds
     * @return boolean
     */
    private static boolean isReachable(String host, int openPort, int timeOutMillis) {
        try {
            try (Socket soc = new Socket()) {
                soc.connect(new InetSocketAddress(host, openPort), timeOutMillis);
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

}
