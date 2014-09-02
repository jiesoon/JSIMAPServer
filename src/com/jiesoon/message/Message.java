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

    private int mSize;
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

    // resend fields
    private String mResendFrom;
    private String mResendDate;
    private String mResendSender;
    private String mResendTo;
    private String mResendCc;
    private String mResendBcc;
    private String mResendMessageId;

    private String mBody;

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmFrom() {
        return mFrom;
    }

    public void setmFrom(String mFrom) {
        this.mFrom = mFrom;
    }

    public String getmSender() {
        return mSender;
    }

    public void setmSender(String mSender) {
        this.mSender = mSender;
    }

    public String getmReplyTo() {
        return mReplyTo;
    }

    public void setmReplyTo(String mReplyTo) {
        this.mReplyTo = mReplyTo;
    }

    public String getmTo() {
        return mTo;
    }

    public void setmTo(String mTo) {
        this.mTo = mTo;
    }

    public String getmCc() {
        return mCc;
    }

    public void setmCc(String mCc) {
        this.mCc = mCc;
    }

    public String getmBcc() {
        return mBcc;
    }

    public void setmBcc(String mBcc) {
        this.mBcc = mBcc;
    }

    public String getmMessageId() {
        return mMessageId;
    }

    public void setmMessageId(String mMessageId) {
        this.mMessageId = mMessageId;
    }

    public String getmInReplyTo() {
        return mInReplyTo;
    }

    public void setmInReplyTo(String mInReplyTo) {
        this.mInReplyTo = mInReplyTo;
    }

    public String getmReferences() {
        return mReferences;
    }

    public void setmReferences(String mReferences) {
        this.mReferences = mReferences;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmComments() {
        return mComments;
    }

    public void setmComments(String mComments) {
        this.mComments = mComments;
    }

    public String getmKeywords() {
        return mKeywords;
    }

    public void setmKeywords(String mKeywords) {
        this.mKeywords = mKeywords;
    }

    public String getmResendFrom() {
        return mResendFrom;
    }

    public void setmResendFrom(String mResendFrom) {
        this.mResendFrom = mResendFrom;
    }

    public String getmResendDate() {
        return mResendDate;
    }

    public void setmResendDate(String mResendDate) {
        this.mResendDate = mResendDate;
    }

    public String getmResendSender() {
        return mResendSender;
    }

    public void setmResendSender(String mResendSender) {
        this.mResendSender = mResendSender;
    }

    public String getmResendTo() {
        return mResendTo;
    }

    public void setmResendTo(String mResendTo) {
        this.mResendTo = mResendTo;
    }

    public String getmResendCc() {
        return mResendCc;
    }

    public void setmResendCc(String mResendCc) {
        this.mResendCc = mResendCc;
    }

    public String getmResendBcc() {
        return mResendBcc;
    }

    public void setmResendBcc(String mResendBcc) {
        this.mResendBcc = mResendBcc;
    }

    public String getmResendMessageId() {
        return mResendMessageId;
    }

    public void setmResendMessageId(String mResendMessageId) {
        this.mResendMessageId = mResendMessageId;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }
}
