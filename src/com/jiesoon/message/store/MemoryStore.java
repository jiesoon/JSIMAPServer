package com.jiesoon.message.store;

import java.util.ArrayList;
import java.util.List;

import com.jiesoon.message.Message;

public class MemoryStore extends Store {
    private List<Message> mMessages = new ArrayList<Message>();
    private long mMessageId;

    public void init() {
        Message msg = new Message();

        msg.setmSubject("Hello from www.jiesoon");
        msg.setmSender("Jiesoon<jiesoon@jiesoon.com>");
        msg.setmTo("lincea@jiesoon.com");
        msg.setmDate("18:20:20 Thu, 8 9 2014");
        msg.setmBody("This is the first message stored in JSIMAP server");
        add(msg);

        msg = new Message();
        msg.setmSubject("Glad to see you!");
        msg.setmSender("jiesoon@jiesoon.com");
        msg.setmTo("lincea@jiesoon.com");
        msg.setmDate("18:20:20 Thu, 8 9 2014");
        msg.setmBody("This is the 2nd message stored in JSIMAP server");
        add(msg);

    }

    public void add(Message msg) {
        if (msg != null) {
            msg.setmMessageId(String.valueOf(++mMessageId));
            mMessages.add(msg);
        }
    }

    public void remove(Message msg) {
        mMessages.remove(msg);
    }

    public void remove(int idx) {
        mMessages.remove(idx);
    }

    public int size() {
        return mMessages.size();
    }
}
