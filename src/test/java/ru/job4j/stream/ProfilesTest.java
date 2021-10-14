package ru.job4j.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
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
        assertThat(addresses, is(pr.collect(profiles)));
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
        assertThat(addresses, is(pr.collect(profiles)));
    }

    @Test
    public void addressesDuplicate() {
        Profiles pr = new Profiles();
        List<Profile> profiles = List.of(
                new Profile(new Address("Tver", "Lenina", 8, 31)),
                new Profile(new Address("Moscow", "Lenina", 21, 1)),
                new Profile(new Address("Moscow", "Lenina", 21, 1))
        );
        List<Address> expected = List.of(
                new Address("Moscow", "Lenina", 21, 1),
                new Address("Tver", "Lenina", 8, 31)
        );
        assertThat(expected, is(pr.collect(profiles)));
    }

    @Test
    public void addressesSorted() {
        Profiles pr = new Profiles();
        List<Profile> profiles = List.of(
                new Profile(new Address("Sochi", "Lenina", 8, 31)),
                new Profile(new Address("Azov", "Lenina", 21, 1)),
                new Profile(new Address("Bryansk", "Lenina", 21, 1))
        );
        List<Address> expected = List.of(
                new Address("Azov", "Lenina", 21, 1),
                new Address("Bryansk", "Lenina", 21, 1),
                new Address("Sochi", "Lenina", 8, 31)
        );
        assertThat(expected, is(pr.collect(profiles)));
    }
}