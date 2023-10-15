package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager{
    @Override
    public Connection getConnection() {
        if(Math.random() * 100 <= 25){
            return new FaultyConnection();
        }
        else{
            return new StableConnection();
        }
    }
}
