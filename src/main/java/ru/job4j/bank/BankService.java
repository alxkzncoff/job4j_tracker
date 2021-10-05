package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Account> accounts =  users.get(findByPassport(passport));
        if (accounts != null && !accounts.contains(account)) {
            accounts.add(account);
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта.
     * Если пользователь не найдем, возвращает null.
     * @param passport номер паспорта.
     * @return User or null.
     */
    public User findByPassport(String passport) {
        User rsl = null;
        for (User user: users.keySet()) {
            if (user.getPassport().equals(passport)) {
                rsl = user;
                break;
            }
        }
        return rsl;
    }

    /**
     * Метод ищет по номеру паспорта пользователя, а по реквизитам
     * ищет банковский счет данного пользователя.
     * @param passport номер паспорта.
     * @param requisite реквизиты.
     * @return Account or null.
     */
    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account: accounts) {
                if (account.getRequisite().equals(requisite)) {
                    rsl = account;
                    break;
                }
            }
        }
        return rsl;
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
        Account source = findByRequisite(srcPassport, srcRequisite);
        Account destination = findByRequisite(destPassport, destRequisite);
        if (source != null && destination != null) {
            if (source.getBalance() >= amount) {
                source.setBalance(source.getBalance() - amount);
                destination.setBalance(destination.getBalance() + amount);
                rsl = true;
            }
        }
        return rsl;
    }
}