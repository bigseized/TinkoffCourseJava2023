package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;

public class ContactsList {
    private final static String ASCENDING_ORDER = "ASC";
    private final static String DESCENDING_ORDER = "DESC";

    private ContactsList() {
    }

    public static List<Contact> parseContacts(String[] persons, String sortType) {
        if (persons == null || persons.length == 0) {
            throw new IllegalArgumentException("Неверный массив пользователей");
        }
        List<Contact> newContactList = getListOfContacts(persons);
        sortList(newContactList, sortType);
        return newContactList;
    }

    private static List<Contact> getListOfContacts(String[] persons) {
        List<Contact> newContactList = new ArrayList<>();
        for (String person : persons) {
            switch (person.split(" ").length) {
                case 1 -> newContactList.add(new Contact(person.split(" ")[0]));
                case 2 -> newContactList.add(new Contact(person.split(" ")[0], person.split(" ")[1]));
                default -> throw new IllegalArgumentException("Неверный формат ввода");
            }
        }
        return newContactList;
    }

    private static void sortList(List<Contact> persons, String sortType) {
        if (sortType.equals(ASCENDING_ORDER)) {
            persons.sort(new ContactComparator());
        } else if (sortType.equals(DESCENDING_ORDER)) {
            persons.sort(new ContactComparator().reversed());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
