package xyz.twoladsandacat.javafxmail;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import xyz.twoladsandacat.javafxmail.controller.services.FetchFoldersService;
import xyz.twoladsandacat.javafxmail.controller.services.FolderUpdaterService;
import xyz.twoladsandacat.javafxmail.model.EmailAccount;
import xyz.twoladsandacat.javafxmail.model.EmailMessage;
import xyz.twoladsandacat.javafxmail.model.EmailTreeItem;

import java.util.ArrayList;
import java.util.List;

public class EmailManager {

    private EmailMessage selectedMessage;
    private EmailTreeItem<String> selectedFolder;

    public void setSelectedMessage(EmailMessage selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public void setSelectedFolder(EmailTreeItem<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }

    // Folder handling:
    private FolderUpdaterService folderUpdaterService;
    private final EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public List<Folder> getFoldersList() {
        return foldersList;
    }

    private List<Folder> foldersList = new ArrayList<>();

    public EmailManager() {
        folderUpdaterService = new FolderUpdaterService(foldersList);
        folderUpdaterService.start();
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<>(emailAccount.getAddress());
        foldersRoot.getChildren().add(treeItem);
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem, foldersList);
        fetchFoldersService.start();
    }

    public void setRead() {
        try {
            selectedMessage.setRead(true);
            selectedMessage.getMessage().setFlag(Flags.Flag.SEEN, true);
            selectedFolder.decrementMessageCount();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
