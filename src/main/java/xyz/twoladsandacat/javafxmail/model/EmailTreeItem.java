package xyz.twoladsandacat.javafxmail.model;

import jakarta.mail.Flags;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import xyz.twoladsandacat.javafxmail.EmailManager;

public class EmailTreeItem<String> extends TreeItem<String> {

    private String name;
    private ObservableList<EmailMessage> emailMessages;
    private int unreadMessagesCount;

    public EmailTreeItem(String name) {
        super(name);
        this.name = name;
        this.emailMessages = FXCollections.observableArrayList();
    }

    public ObservableList<EmailMessage> getEmailMessages() {
        return emailMessages;
    }

    public void addEmail(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchEmail(message);
        emailMessages.add(emailMessage);
    }

    public void addEmailToTop(Message message) throws MessagingException {
        EmailMessage emailMessage = fetchEmail(message);
        emailMessages.add(0, emailMessage);
    }

    private EmailMessage fetchEmail(Message message) throws MessagingException {
        boolean messageIsRead = message.getFlags().contains(Flags.Flag.SEEN);
        EmailMessage emailMessage = new EmailMessage(
                message.getSubject(),
                message.getFrom()[0].toString(),
                message.getRecipients(MimeMessage.RecipientType.TO)[0].toString(),
                message.getSize(),
                message.getSentDate(),
                messageIsRead,
                message
        );
        if (!messageIsRead) {
            incrementMessagesCount();
        }
        return emailMessage;
    }

    public void incrementMessagesCount() {
        unreadMessagesCount++;
        updateName();
    }

    public void decrementMessageCount() {
        unreadMessagesCount--;
        updateName();
    }

    private void updateName() {
        if (unreadMessagesCount > 0) {
            this.setValue((String) (name + " (" + unreadMessagesCount + ")"));
        } else {
            this.setValue(name);
        }
    }
}
