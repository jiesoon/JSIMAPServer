package com.jiesoon.message.store;

import java.util.ArrayList;
import java.util.List;

import com.jiesoon.message.Message;

interface MailBoxListener{
    void addMessage(Message msg);
    void updateMessage(Message msg);
    void removeMessage(Message msg);
}

public class MemoryStore extends Store implements MailBoxListener {
    private List<Message> mMessages = new ArrayList<Message>();
    private long mMessageId;

    private final String mMailboxName = "INBOX";
    
    public void init() {
        Message msg = new Message();

        msg.setSubject("Hello from www.jiesoon");
        msg.setSender("Jiesoon<jiesoon@jiesoon.com>");
        msg.setTo("lincea@jiesoon.com");
        msg.setDate("18:20:20 Thu, 8 9 2014");
        msg.setBody("This is the first message stored in JSIMAP server");
        add(msg);

        msg = new Message();
        msg.setSubject("Glad to see you!");
        msg.setSender("jiesoon@jiesoon.com");
        msg.setTo("lincea@jiesoon.com");
        msg.setDate("18:20:20 Thu, 8 9 2014");
        msg.setBody("This is the 2nd message stored in JSIMAP server");
        add(msg);

    }

    public void add(Message msg) {
        if (msg != null) {
            msg.setMessageId(String.valueOf(++mMessageId));
            mMessages.add(msg);
            
            addMessage(msg);
            
        }
    }

    public void remove(Message msg) {
        mMessages.remove(msg);
        
        removeMessage(msg);
        
    }

    public void remove(int idx) {
        mMessages.remove(idx);
    }

    public void update(Message msg){
        for(Message message: mMessages){
            if(message.getMessageId() == msg.getMessageId()){
                //update message flags.
                break;
            }
        }
    }
    
    //Mailbox size
    public int size() {
        return mMessages.size();
    }

    @Override
    public void addMessage(Message msg) {
        // TODO Tell client I have added a new message
        
    }

    @Override
    public void updateMessage(Message msg) {
        // TODO Tell client I have update message's flags
        
    }

    @Override
    public void removeMessage(Message msg) {
        // TODO Tell client I have removed a message.
        
    }
}
