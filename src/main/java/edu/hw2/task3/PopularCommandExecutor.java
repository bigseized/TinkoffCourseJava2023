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
        int attemps = 0;

        while (attemps < maxAttempts) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                attemps++;
                if (attemps == maxAttempts) {
                    throw new ConnectionException(e);
                }
            } catch (Exception t) {
                throw new ConnectionException(t);
            }
        }
    }
}
