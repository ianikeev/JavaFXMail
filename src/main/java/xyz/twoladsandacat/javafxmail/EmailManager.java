package xyz.twoladsandacat.javafxmail;

import xyz.twoladsandacat.javafxmail.controller.services.FetchFoldersService;
import xyz.twoladsandacat.javafxmail.model.EmailAccount;
import xyz.twoladsandacat.javafxmail.model.EmailTreeItem;

public class EmailManager {

    // Folder handling:
    private final EmailTreeItem<String> foldersRoot = new EmailTreeItem<>("");

    public EmailTreeItem<String> getFoldersRoot() {
        return foldersRoot;
    }

    public void addEmailAccount(EmailAccount emailAccount) {
        EmailTreeItem<String> treeItem = new EmailTreeItem<>(emailAccount.getAddress());
        foldersRoot.getChildren().add(treeItem);
        FetchFoldersService fetchFoldersService = new FetchFoldersService(emailAccount.getStore(), treeItem);
        fetchFoldersService.start();
    }
}
