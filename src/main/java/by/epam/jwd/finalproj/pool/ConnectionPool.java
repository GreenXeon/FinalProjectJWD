package by.epam.jwd.finalproj.pool;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> takenConnections;

    private final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static final int INITIAL_POOL_SIZE = 8;
    private static final int INCREMENT_SIZE = 4;
    private static final int MAX_POOL_SIZE = 32;

    private static final ReentrantLock lock = new ReentrantLock();

    private static ConnectionPool instance;

    private ConnectionPool(){
        availableConnections = new ArrayDeque<>(INITIAL_POOL_SIZE);
        takenConnections = new ArrayDeque<>();
        init();
    }

    public static ConnectionPool getInstance() {
        if (instance == null){
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
            finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection retrieveConnection(){
        lock.lock();
        try{
            //todo: pool extension
            Connection connection = availableConnections.peek();
            takenConnections.add((ProxyConnection) connection);
            return connection;
        }
        finally {
            lock.unlock();
        }
    }

    public void returnConnection(Connection connection){
        lock.lock();
        try{
            if (takenConnections.contains(connection)) {
                takenConnections.remove(connection);
                availableConnections.add((ProxyConnection) connection);
            }
        } finally {
            lock.unlock();
        }
    }

    public void init(){
        registerDrivers();
        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                final Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_model?serverTimezone=Europe/Minsk&allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "root");
                //todo: take info from properties
                final ProxyConnection proxyConnection = new ProxyConnection(conn);
                availableConnections.add(proxyConnection);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void registerDrivers(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            DriverManager.registerDriver(DriverManager.getDriver("jdbc:mysql://localhost:3306/mydb_model"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    };

    public void destroy(){
        lock.lock();
        try{
            availableConnections.forEach(ProxyConnection::closeConnection);
            takenConnections.forEach(ProxyConnection::closeConnection);
            deregisterDrivers();
        }
        finally {
            lock.unlock();
        }
    }

    private static void deregisterDrivers(){
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                //logging
                e.printStackTrace();
            }
        }
        AbandonedConnectionCleanupThread.uncheckedShutdown();
    }
}
