/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jwebservice;

/**
 *
 * @author 18359
 */

import com.independentsoft.exchange.FindItemResponse;
import com.independentsoft.exchange.IsEqualTo;
import com.independentsoft.exchange.Message;
import com.independentsoft.exchange.MessagePropertyPath;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.ServiceException;
import com.independentsoft.exchange.StandardFolder;

public class JWebService {

    public static void main(String[] args)
    {
        try
        {
            Service service = new Service("outlook.nnpcgroup.com", "18359", "foxjuser");

            IsEqualTo restriction = new IsEqualTo(MessagePropertyPath.IS_READ, false);

            FindItemResponse response = service.findItem(StandardFolder.INBOX, MessagePropertyPath.getAllPropertyPaths(), restriction);

            for (int i = 0; i < response.getItems().size(); i++)
            {
                if (response.getItems().get(i) instanceof Message)
                {
                    Message message = (Message) response.getItems().get(i);

                    System.out.println("Subject = " + message.getSubject());
                    System.out.println("ReceivedTime = " + message.getReceivedTime());

                    if (message.getFrom() != null)
                    {
                        System.out.println("From = " + message.getFrom().getName());
                    }

                    System.out.println("Body Preview = " + message.getBodyPlainText());
                    System.out.println("----------------------------------------------------------------");

                    //If you want to get complete message with entire Body uncomment following line
                    //message = service.getMessage(response.getItems().get(i).getItemId());

                    //If you want to set message as read uncomment following lines
                    //Property readProperty = new Property(MessagePropertyPath.IS_READ, true);
                    //ItemId itemId = service.updateItem(response.getItems().get(i).getItemId(), readProperty);
                }
            }
        }
        catch (ServiceException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getXmlMessage());

            e.printStackTrace();
        }
    }
}
