package edu.hw2.task3;

public class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) {
        Connection connection = manager.getConnection();
        ConnectionException exception = null;
        int attemps = 0;

        while (attemps < maxAttempts) {
            try (connection) {
                connection.execute(command);
                break;
            } catch (ConnectionException e) {
                exception = e;
                attemps++;
            } catch (Exception t) {
                throw new ConnectionException(t);
            }
        }

        if (attemps == maxAttempts) {
            throw new ConnectionException(exception);
        }

    }
}
