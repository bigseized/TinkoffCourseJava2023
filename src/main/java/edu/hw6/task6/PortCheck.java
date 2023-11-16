package edu.hw6.task6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

@SuppressWarnings("RegexpSinglelineJava")
public class PortCheck {

    private PortCheck() {
    }

    private final static int MAX_PORT = 49151;
    private final static String OUTPUT_FORMAT = "%-9s %-8s %s";
    private static final Map<Integer, String> MOST_POPULAR_PORTS = Map.ofEntries(
        Map.entry(21, "FTP"),
        Map.entry(22, "SSH"),
        Map.entry(23, "Telnet"),
        Map.entry(25, "SMTP"),
        Map.entry(53, "DNS"),
        Map.entry(67, "DHCP"),
        Map.entry(80, "HTTP"),
        Map.entry(110, "POP3"),
        Map.entry(123, "NTP"),
        Map.entry(135, "EPMAP"),
        Map.entry(137, "NETBIOS-NS"),
        Map.entry(138, "NETBIOS-DGM"),
        Map.entry(139, "NETBIOS-SSN"),
        Map.entry(445, "MICROSOFT-DS"),
        Map.entry(1900, "SSDP"),
        Map.entry(5353, "MDNS"),
        Map.entry(5355, "LLMNR"),
        Map.entry(8080, "HTTP Proxy")
    );

    public static void scanPorts() {
        System.out.printf((OUTPUT_FORMAT) + "%n", "Протокол", "Порт", "Сервис");
        for (int i = 0; i <= MAX_PORT; i++) {
            try (ServerSocket serverSocket = new ServerSocket(i)) {
                //ServerSocket successfully created
            } catch (Exception e) {
                System.out.printf((OUTPUT_FORMAT) + "%n", "TSP", i, MOST_POPULAR_PORTS.getOrDefault(i, ""));
            }
            try (DatagramSocket datagramSocket = new DatagramSocket(i)) {
                //DatagramSocket successfully created
            } catch (Exception e) {
                System.out.printf((OUTPUT_FORMAT) + "%n", "UDP", i, MOST_POPULAR_PORTS.getOrDefault(i, ""));
            }
        }
    }

}
