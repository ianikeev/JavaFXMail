package xyz.twoladsandacat.javafxmail.model;

import jakarta.mail.Store;

import java.util.Properties;

public class EmailAccount {

    private final String address;
    private final String password;
    private Properties properties;
    private Store store;

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public EmailAccount(String address, String password) {
        this.address = address;
        this.password = password;
        properties = new Properties();
        properties.put("incomingHost", "imap.yandex.com");
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.yandex.com");
        properties.put("mail.smtps.auth", true);
        properties.put("outgoingHost", "smtp.yandex.com");
    }

}
