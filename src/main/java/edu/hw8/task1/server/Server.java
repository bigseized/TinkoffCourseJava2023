package edu.hw8.task1.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;

public class Server {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static final int BUFFER_SIZE = 1024;
    private static final int NUMBER_OF_THREADS = 6;

    public void start() {
        try (Selector selector = Selector.open();
             ServerSocketChannel channel = ServerSocketChannel.open();
             ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)) {
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(HOST, PORT));
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (channel.isOpen()) {
                if (selector.selectNow() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            handleKey(channel, key, executorService);
                            iterator.remove();
                        }
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleKey(ServerSocketChannel channel, SelectionKey key, ExecutorService executorService) {
        try {
            if (key.isAcceptable()) {
                SocketChannel socketChannel = channel.accept();
                socketChannel.configureBlocking(false);
                executorService.execute(() -> new ClientInteraction(socketChannel).run());

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private record ClientInteraction(SocketChannel clientSocket) {
        @SneakyThrows
        public void run() {
            Selector selector = Selector.open();
            clientSocket.configureBlocking(false);
            clientSocket.register(selector, SelectionKey.OP_READ);
            while (clientSocket.isConnected()) {
                if (selector.selectNow() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        resolveUserData(key);
                        iterator.remove();
                    }
                }
            }
        }

        @SneakyThrows
        private void resolveUserData(SelectionKey key) {
            if (key.isReadable()) {
                ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
                int bytesRead = clientSocket.read(buffer);
                if (bytesRead > 0) {
                    String request = new String(buffer.array(), StandardCharsets.UTF_8);
                    buffer.clear();
                    sendData(clientSocket, request.trim());
                }

                key.channel().close();
                key.cancel();
            }
        }

        @SneakyThrows
        private void sendData(SocketChannel socketChannel, String request) {
            String response = MessagesUtils.MESSAGES.get(request);
            socketChannel.write(ByteBuffer
                .wrap(Objects.requireNonNullElse(response, MessagesUtils.DEFAULT)
                    .getBytes(StandardCharsets.UTF_8)));
        }
    }

}
