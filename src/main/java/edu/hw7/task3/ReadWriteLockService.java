package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.SneakyThrows;

public class ReadWriteLockService extends BaseCachingService {
    private final static Integer LOCK_TIME = 1000;
    private final static String TRY_LOCK_EXCEPTION = "tryLock time limit exceeded";
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    @SneakyThrows
    public void add(Person person) {
        if (lock.writeLock().tryLock(LOCK_TIME, TimeUnit.MILLISECONDS)) {
            try {
                super.add(person);
            } finally {
                lock.writeLock().unlock();
            }
        } else {
            throw new RuntimeException(TRY_LOCK_EXCEPTION);
        }
    }

    @Override
    @SneakyThrows
    public void delete(int id) {
        if (lock.writeLock().tryLock(LOCK_TIME, TimeUnit.MILLISECONDS)) {
            try {
                super.delete(id);
            } finally {
                lock.writeLock().unlock();
            }
        } else {
            throw new RuntimeException(TRY_LOCK_EXCEPTION);
        }
    }

    @Override
    @SneakyThrows
    public List<Person> findByName(String name) {
        if (lock.readLock().tryLock(LOCK_TIME, TimeUnit.MILLISECONDS)) {
            try {
                return super.findByName(name);
            } finally {
                lock.readLock().unlock();
            }
        } else {
            throw new RuntimeException(TRY_LOCK_EXCEPTION);
        }
    }

    @Override
    @SneakyThrows
    public List<Person> findByAddress(String address) {
        if (lock.readLock().tryLock(LOCK_TIME, TimeUnit.MILLISECONDS)) {
            try {
                return super.findByAddress(address);
            } finally {
                lock.readLock().unlock();
            }
        } else {
            throw new RuntimeException(TRY_LOCK_EXCEPTION);
        }
    }

    @Override
    @SneakyThrows
    public List<Person> findByPhone(String phone) {
        if (lock.readLock().tryLock(LOCK_TIME, TimeUnit.MILLISECONDS)) {
            try {
                return super.findByPhone(phone);
            } finally {
                lock.readLock().unlock();
            }
        } else {
            throw new RuntimeException(TRY_LOCK_EXCEPTION);
        }
    }
}
