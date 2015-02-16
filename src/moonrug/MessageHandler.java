/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moonrug;

import com.moonrug.exchange.INotification;

/**
 *
 * @author 18359
 */
public class MessageHandler implements INotification {

    @Override
    public void newMail() {
        System.out.println("You've got mail!");
    }

    @Override
    public int getCheckInterval() {
        return 30;
    }
    
}
