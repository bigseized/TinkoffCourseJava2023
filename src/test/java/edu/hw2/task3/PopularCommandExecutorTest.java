package edu.hw2.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class PopularCommandExecutorTest {
    @Test
    @DisplayName("Проверка на возврат плохого соединения через менеджер")
    void faultyConnectionManager_shouldReturnFaultyConnection_whenCallMethodGetConnection() {
        assertThat(new FaultyConnectionManager().getConnection()).isInstanceOf(FaultyConnection.class);
    }

    static Stream<Arguments> remoteConnectionArguments() {
        return Stream.of(
            Arguments.of(new DefaultConnectionManager(), 5),
            Arguments.of(new FaultyConnectionManager(), 10)
        );
    }

    @DisplayName("Проверка работы типов соединений (через логирование)")
    @ParameterizedTest
    @MethodSource("remoteConnectionArguments")
    void remoteConnectionTest(ConnectionManager manager, int maxAttempts) {
        PopularCommandExecutor popularCommandExecutor = new PopularCommandExecutor(manager, maxAttempts);
        popularCommandExecutor.updatePackages();
    }
}
