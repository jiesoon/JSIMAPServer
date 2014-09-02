package com.jiesoon.message;

/**
 * http://tools.ietf.org/html/rfc2822 Cut off from RFC2822
 * 
 * @author jiesoon
 * 
 */
public class Message {
    public final int MAX_LINE_LEN = 998;
    public final int LINE_LEN = 78;

    // origination date
    private String mDate;

    // origination fields
    private String mFrom;
    private String mSender;

    private String mReplyTo;

    // Destination fields
    private String mTo;
    private String mCc;
    private String mBcc;

    // identification fields
    private String mMessageId;
    private String mInReplyTo;
    private String mReferences;

    // informational fields
    private String mSubject;
    private String mComments;
    private String mKeywords;

    // Resend fields
    private String mResendFrom;
    private String mResendDate;
    private String mResendSender;
    private String mResendTo;
    private String mResendCc;
    private String mResendBcc;
    private String mResendMessageId;

    private String mBody;

    public int getSize() {
        return mSize;
    }

    public void setSize(int mSize) {
        this.mSize = mSize;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String mFrom) {
        this.mFrom = mFrom;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String mSender) {
        this.mSender = mSender;
    }

    public String getReplyTo() {
        return mReplyTo;
    }

    public void setReplyTo(String mReplyTo) {
        this.mReplyTo = mReplyTo;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String mTo) {
        this.mTo = mTo;
    }

    public String getCc() {
        return mCc;
    }

    public void setCc(String mCc) {
        this.mCc = mCc;
    }

    public String getBcc() {
        return mBcc;
    }

    public void setBcc(String mBcc) {
        this.mBcc = mBcc;
    }

    public String getMessageId() {
        return mMessageId;
    }

    public void setMessageId(String mMessageId) {
        this.mMessageId = mMessageId;
    }

    public String getInReplyTo() {
        return mInReplyTo;
    }

    public void setInReplyTo(String mInReplyTo) {
        this.mInReplyTo = mInReplyTo;
    }

    public String getReferences() {
        return mReferences;
    }

    public void setReferences(String mReferences) {
        this.mReferences = mReferences;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getComments() {
        return mComments;
    }

    public void setComments(String mComments) {
        this.mComments = mComments;
    }

    public String getKeywords() {
        return mKeywords;
    }

    public void setKeywords(String mKeywords) {
        this.mKeywords = mKeywords;
    }

    public String getResendFrom() {
        return mResendFrom;
    }

    public void setResendFrom(String mResendFrom) {
        this.mResendFrom = mResendFrom;
    }

    public String getResendDate() {
        return mResendDate;
    }

    public void setResendDate(String mResendDate) {
        this.mResendDate = mResendDate;
    }

    public String getResendSender() {
        return mResendSender;
    }

    public void setResendSender(String mResendSender) {
        this.mResendSender = mResendSender;
    }

    public String getResendTo() {
        return mResendTo;
    }

    public void setResendTo(String mResendTo) {
        this.mResendTo = mResendTo;
    }

    public String getResendCc() {
        return mResendCc;
    }

    public void setResendCc(String mResendCc) {
        this.mResendCc = mResendCc;
    }

    public String getResendBcc() {
        return mResendBcc;
    }

    public void setResendBcc(String mResendBcc) {
        this.mResendBcc = mResendBcc;
    }

    public String getResendMessageId() {
        return mResendMessageId;
    }

    public void setResendMessageId(String mResendMessageId) {
        this.mResendMessageId = mResendMessageId;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String mBody) {
        this.mBody = mBody;
    }
    
    //Flags Message Attribute
    private int mFlags;
    public final int FLAGS = 1;
    public final int SEEN = FLAGS << 0;
    public final int ANSWERED = FLAGS << 1;
    public final int FLAGGED = FLAGS << 2;
    public final int DELETED = FLAGS << 3;
    public final int DRAFT = FLAGS << 4;
    public final int RECENT = FLAGS << 5;
    
    //Internal Date Message Attribute
    private long mInternalDate;
    
    
    //[RFC-2822] Size Message Attribute
    private int mSize;
    
    //Envelope Structure Message Attribute
    private Envelop mEnvelop;
    
    //Body Structure Message Attribute
    private BodyStructure mBodyStructure;
}
