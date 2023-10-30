package edu.hw3.task5;

import java.util.Comparator;

public class ContactComparator implements Comparator<Contact> {
    @Override
    public int compare(Contact contact1, Contact contact2) {
        String toCompareFirst = (contact1.surname().isEmpty()) ? contact1.name() : contact1.surname();
        String toCompareSecond = (contact2.surname().isEmpty()) ? contact2.name() : contact2.surname();
        return toCompareFirst.compareTo(toCompareSecond);
    }
}
