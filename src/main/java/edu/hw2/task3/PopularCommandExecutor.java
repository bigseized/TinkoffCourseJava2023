package edu.hw2.task3;

public class PopularCommandExecutor {
    private static ConnectionManager manager;
    private static final int maxAttempts = 2;


    public static void main(String[] args) {
        manager = new FaultyConnectionManager();
        updatePackages();
    }

    public static void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    static void tryExecute(String command){
        Connection connection = manager.getConnection();
        ConnectionException exception = null;
        int attemps = 0;

        while(attemps < maxAttempts) {
            try(connection) {
                connection.execute(command);
                break;
            }catch (ConnectionException e){
                exception = e;
                attemps++;
            }catch (Exception c) {
                throw new ConnectionException(c);
            }
        }

        if (attemps == maxAttempts){
            throw new ConnectionException(exception);
        }

    }
}
