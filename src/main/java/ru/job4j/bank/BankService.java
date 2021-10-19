package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает работу простой банковской системы, которая может:
 *      1. Регистрировать пользователя;
 *      2. Удалять пользователя;
 *      3. Добавлять пользователю банковский счет;
 *      4. Переводить деньги с одного банковского счета на другой счет.
 * @author ALEKSANDR KUZNETSOV
 * @version 1.0
 */
public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и добавляет его в банковскую систему,
     * если его еще нет в системе.
     * @param user пользователь.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход номер паспорта и номер банковского счета.
     * Если пользователь с таким паспортом существует, и если такого счета
     * у него еще нет, то добавляет новый банковский счет.
     * @param passport номер паспорта.
     * @param account номер банковского счета.
     */
    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts =  users.get(user.get());
            if (accounts != null && !accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта.
     * Если пользователь не найдем, возвращает null.
     * @param passport номер паспорта.
     * @return User or null.
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод ищет по номеру паспорта пользователя, а по реквизитам
     * ищет банковский счет данного пользователя.
     * @param passport номер паспорта.
     * @param requisite реквизиты.
     * @return Account or null.
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        return user.map(value -> users.get(value).stream()
                .filter(account -> account.getRequisite().equals(requisite))
                .findFirst()).orElse(null);
    }

    /**
     * Метод перечисляет деньги с одного счета на другой.
     * Если счет не найден или не хватает денег на счете для перевода,
     * то метод возвращает false.
     * @param srcPassport номер паспорта, с которого перечисляют деньги.
     * @param srcRequisite номер реквизита, с которого перечисляют деньги.
     * @param destPassport номер паспорта, куда перечисляют деньги.
     * @param destRequisite номер реквизита, куда перечисляют деньги.
     * @param amount сумма перевода.
     * @return boolean true or boolean false.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Optional<Account> source = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destination = findByRequisite(destPassport, destRequisite);
        if (source.isPresent() && destination.isPresent()) {
            if (source.get().getBalance() >= amount) {
                source.get().setBalance(source.get().getBalance() - amount);
                destination.get().setBalance(destination.get().getBalance() + amount);
                rsl = true;
            }
        }
        return rsl;
    }
}