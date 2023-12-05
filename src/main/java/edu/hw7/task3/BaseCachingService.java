package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCachingService implements PersonDatabase {
    private final Map<Integer, Person> cachedIds = new HashMap<>();
    private final Map<String, List<Person>> cachedNames = new HashMap<>();
    private final Map<String, List<Person>> cachedAddresses = new HashMap<>();
    private final Map<String, List<Person>> cachedPhones = new HashMap<>();

    @Override
    public void add(Person person) {
        if (!areAllFieldsExist(person)) {
            throw new RuntimeException("All fields must be not Null");
        }

        cachedNames.computeIfAbsent(person.name(), name -> new ArrayList<>()).add(person);
        cachedAddresses.computeIfAbsent(person.address(), address -> new ArrayList<>()).add(person);
        cachedPhones.computeIfAbsent(person.phoneNumber(), phone -> new ArrayList<>()).add(person);

        if (cachedIds.containsKey(person.id())) {
            throw new RuntimeException("ID of person already exist");
        }

        cachedIds.put(person.id(), person);
    }

    @Override
    public void delete(int id) {
        cachedNames.get(cachedIds.get(id).name()).remove(cachedIds.get(id));
        cachedAddresses.get(cachedIds.get(id).address()).remove(cachedIds.get(id));
        cachedPhones.get(cachedIds.get(id).phoneNumber()).remove(cachedIds.get(id));
        cachedIds.remove(id);
    }

    @Override
    public List<Person> findByName(String name) {
        return cachedNames.getOrDefault(name, null);
    }

    @Override
    public List<Person> findByAddress(String address) {
        return cachedAddresses.getOrDefault(address, null);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return cachedPhones.getOrDefault(phone, null);
    }

    private boolean areAllFieldsExist(Person person) {
        return !(person.address() == null || person.name() == null || person.phoneNumber() == null);
    }
}
