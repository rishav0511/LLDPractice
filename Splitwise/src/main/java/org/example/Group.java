package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Group {
    private final String id;
    private final String name;
    private final List<User> members;
    private final List<Expense> expenses;

    public Group(String id, String name, List<User> members) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>(members);
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) { expenses.add(expense); }
    public String getId() { return id; }
    public String getName() { return name; }
    public List<User> getMembers() { return Collections.unmodifiableList(members); }
    public List<Expense> getExpenses() { return Collections.unmodifiableList(expenses); }

    @Override
    public String toString() { return name + " (" + members.size() + " members)"; }
}
