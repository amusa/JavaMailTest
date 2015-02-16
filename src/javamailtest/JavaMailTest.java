/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamailtest;

import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

/**
 *
 * @author 18359
 */
public class JavaMailTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new JavaMailTest().retrieveEmail();
    }

    public void retrieveEmail() {
        // JavaMail API - Exchange server to allow IMAP access

        // mail server connection parameters
        String user = "18359";
        String password = "foxjuser";
        String host = "outlook.nnpcgroup.com";
        String port = "995";// "110";

        // connect to my pop3 inbox
        Properties properties = new Properties();

        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", port);
        properties.put("mail.pop3.user", user);
        properties.put("mail.pop3.timeout", "158000");
        properties.put("mail.pop3.connectiontimeout", "158000");
        properties.put("mail.pop3.ssl.enable", "true");

        Session session = Session.getInstance(properties);
        session.setDebug(true);

        Store store = null;
        Folder inbox = null;

        try {
            store = session.getStore("pop3");
            store.connect(host, user, password);
            inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);

            // get the list of inbox messages
            int totalMsg = inbox.getMessageCount();
            int unread = inbox.getUnreadMessageCount();
            Message[] messages = inbox.getMessages();
            Message[] msg = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

            System.out.print("size " + messages.length);
            if (messages.length == 0) {
                System.out.println("No messages found.");
            }

            for (int i = 0; i < messages.length; i++) {

                // stop after listing ten messages
                if (i > 10) {
                    System.exit(0);
                    inbox.close(true);
                    store.close();
                }

                System.out.println("Message " + (i + 1));
                System.out.println("From : " + messages[i].getFrom()[0]);
                System.out.println("Subject : " + messages[i].getSubject());
                Object content = messages[i].getContent();

                if (content instanceof String) {
                    System.out.print((String) content);
                }
                /* text/plain = String
                 * multipart" = Multipart
                 * MimeMessage
                 * input stream = Unknown Data Handler 
                 */

            }

            inbox.close(true);
            store.close();

        } catch (MessagingException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
