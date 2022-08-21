package xyz.twoladsandacat.javafxmail.view;

import xyz.twoladsandacat.javafxmail.EmailManager;

public class ViewFactory {

    private EmailManager emailManager;

    public ViewFactory(EmailManager emailManager) {
        this.emailManager = emailManager;
    }

    public void showLoginWindow(){
        System.out.println("show login");
    }
}
