package xyz.twoladsandacat.javafxmail;

import jakarta.mail.Folder;
import xyz.twoladsandacat.javafxmail.controller.services.FetchFoldersService;
import xyz.twoladsandacat.javafxmail.controller.services.FolderUpdaterService;
import xyz.twoladsandacat.javafxmail.model.EmailAccount;
import xyz.twoladsandacat.javafxmail.model.EmailTreeItem;

import java.util.ArrayList;
import java.util.List;

public class EmailManager {

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
}
