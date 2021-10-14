package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProfilesTest {
    @Test
    public void address() {
        Profiles pr = new Profiles();
        List<Address> addresses = List.of(
                new Address("Saint-Petersburg", "Lenina", 19, 22)
        );
        List<Profile> profiles = List.of(
                new Profile(addresses.get(0))
        );
        List<Address> result = pr.collect(profiles);
        assertEquals(addresses, result);
    }

    @Test
    public void addresses() {
        Profiles pr = new Profiles();
        List<Address> addresses = List.of(
                new Address("Moscow", "Lenina", 21, 1),
                new Address("Saint-Petersburg", "Lenina", 19, 22),
                new Address("Tver", "Lenina", 8, 31)
        );
        List<Profile> profiles = List.of(
                new Profile(addresses.get(0)),
                new Profile(addresses.get(1)),
                new Profile(addresses.get(2))
        );
        List<Address> result = pr.collect(profiles);
        assertEquals(addresses, result);
    }
}