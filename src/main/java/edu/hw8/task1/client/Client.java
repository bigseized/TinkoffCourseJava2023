package edu.hw8.task1.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;

    @SneakyThrows
    public String connect(String message) {
        InetSocketAddress hostAddress = new InetSocketAddress(HOST, PORT);
        try (SocketChannel channel = SocketChannel.open(hostAddress)) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            while (byteBuffer.hasRemaining()) {
                channel.write(byteBuffer);
            }
            byteBuffer.flip();
            byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            int bytesRead;
            while ((bytesRead = channel.read(byteBuffer)) != -1) {
                if (bytesRead == 0) {
                    continue;
                }
                return new String(byteBuffer.array(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
