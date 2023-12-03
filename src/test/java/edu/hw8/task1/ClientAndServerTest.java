package edu.hw8.task1;

import edu.hw8.task1.client.Client;
import edu.hw8.task1.server.Server;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientAndServerTest {

    @Test
    @DisplayName("Client and server basic test")
    @SneakyThrows
    public void run_shouldProcessRequestFromClient() {
        Server server = new Server();
        Thread serverThread = new Thread(server::start);
        serverThread.start();
        Thread.sleep(2000);
        String receivedMessage = Client.connect("оскорбления");
        assertThat(receivedMessage.trim()).isEqualTo(
            "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами");
    }

}
