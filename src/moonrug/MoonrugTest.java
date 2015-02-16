/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moonrug;

import com.moonrug.exchange.DefaultFolder;
import com.moonrug.exchange.ExchangeException;
import com.moonrug.exchange.IContentItem;
import com.moonrug.exchange.IFolder;
import com.moonrug.exchange.IMapiSession;
import com.moonrug.exchange.IStore;
import com.moonrug.exchange.Property;
import com.moonrug.exchange.Session;
import com.moonrug.exchange.internal.Folder;
import com.moonrug.exchange.internal.Message;
import com.moonrug.exchange.internal.Store;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author 18359
 */
public class MoonrugTest {

// Session session = IEws Session. Factory .create (
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new MoonrugTest().retrieveEmail();
    }

    public void retrieveEmail() {
        try {
            // JavaMail API - Exchange server to allow IMAP access

            // mail server connection parameters
            String user = "18359";
            String password = "foxjuser";
            String host = "outlook.nnpcgroup.com";
            String domain = "chq";

            Map<String, String> map = new HashMap<>();
            map.put(Session.USERNAME, user);
            map.put(Session.PASSWORD, password);
            map.put(Session.DOMAIN, domain);
            map.put(Session.SERVER, host);
            Session session = IMapiSession.Factory.create(map);

            IFolder inbox;

            inbox = session.getStore().getFolder(DefaultFolder.INBOX);
            int totalMsg = inbox.getContentCount();
            int unread = inbox.getUnreadCount();
            
            IContentItem[] content = inbox.getItems();

//            IStore store = session.getStore();
//            store.registerNotification(new MessageHandler());

            String subject;
            String from;
            String id;
            String body;

            for (IContentItem ci : content) {
                subject = ci.getSubject();
                id=ci.getId();
                body = ci.getBody();

                System.out.println("\nID: "+id+"\nSubject: " + subject
                       
                        + "\nBody: " + body
                        + "\n");
            }

            System.out.println("Total msg: " + totalMsg + " Unread: " + unread);
            // session.close();
        } catch (ExchangeException ex) {
            Logger.getLogger(MoonrugTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
