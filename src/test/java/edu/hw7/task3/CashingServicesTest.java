package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CashingServicesTest {
    @Test
    @DisplayName("ReadWriteLockService delete method test")
    public void readWriteLockPersonDatabase_shouldDeleteCorrectlyWithMultithreading() {
        PersonDatabase personDatabase = new ReadWriteLockService();
        deleteTest(personDatabase);
    }

    @Test
    @DisplayName("ReadWriteLockService find test")
    public void readWriteLockPersonDatabase_shouldCorrectlyReturnPersonWithMultithreading() {
        PersonDatabase personDatabase = new ReadWriteLockService();
        findTest(personDatabase);
    }

    @Test
    @DisplayName("SynchronizedService delete method test")
    public void synchronizedPersonDatabase_shouldDeleteCorrectlyWithMultithreading() {
        PersonDatabase personDatabase = new SynchronizedService();
        deleteTest(personDatabase);
    }

    @Test
    @DisplayName("SynchronizedService find test")
    public void synchronizedPersonDatabase_shouldCorrectlyReturnPersonWithMultithreading() {
        PersonDatabase personDatabase = new SynchronizedService();
        findTest(personDatabase);
    }

    private void deleteTest(PersonDatabase personDatabase) {
        Person person = new Person(123, "Ivan", "Tver", "+79996665212");
        Person secondPerson = new Person(1234, "Ivan", "Moscow", "+79996662521");
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            executorService.execute(() -> personDatabase.add(person));
            executorService.execute(() -> personDatabase.add(secondPerson));
            executorService.execute(() -> personDatabase.delete(123));
            Future<List<Person>> names = executorService.submit(() -> personDatabase.findByName("Ivan"));

            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            assertThat(names.get()).doesNotContain(person);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void findTest(PersonDatabase personDatabase) {
        Person person = new Person(123, "Ivan", "Tver", "+79996665212");
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            executorService.execute(() -> personDatabase.add(person));
            Future<List<Person>> names =
                executorService.submit(() -> personDatabase.findByName(person.name()));
            Future<List<Person>> addresses =
                executorService.submit(() -> personDatabase.findByAddress(person.address()));
            Future<List<Person>> phones =
                executorService.submit(() -> personDatabase.findByPhone(person.phoneNumber()));
            executorService.shutdown();
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            assertAll(
                () -> assertThat(names.get()).contains(person),
                () -> assertThat(addresses.get()).contains(person),
                () -> assertThat(phones.get()).contains(person)
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
