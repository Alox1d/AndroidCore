package ru.alox1d.androidcore.access_modifiers;

//: access/E08_ConnectionManager2.java
// Connections that can be checked in.
//package access;


import ru.alox1d.androidcore.access_modifiers.connection2.Connection;
import ru.alox1d.androidcore.access_modifiers.connection2.ConnectionManager;

public class E08_ConnectionManager2 {
    public static void main(String[] args) {
        Connection[] ca = new Connection[10];
        // Use up all the connections
        for (int i = 0; i < 10; i++)
            ca[i] = ConnectionManager.getConnection();
        // Should produce "null" since there are no
        // more connections:
        System.out.println(ConnectionManager.getConnection());
        // Return connections, then get them out:
        for (int i = 0; i < 5; i++) {
            ca[i].checkIn();
            Connection c = ConnectionManager.getConnection();
            System.out.println(c);
            c.doSomething();
//              c.checkIn(); // because we check in Connection 0 here, it will be on zero place in array and -> this connection always will be printed
        }
    }
}
/* Output:
null
Connection 0
Connection 0
Connection 0
Connection 0
Connection 0
*///:~
//: access/connection2/Connection.java
