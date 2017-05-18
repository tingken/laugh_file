package com.quark.api.auto.bean;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class MessageInfo {

    private String comment;//评论

    private String content;//

    private String username;//

    private String date;



    public MessageInfo(String comment, String content, String username, String date) {
        this.comment = comment;
        this.content = content;
        this.username = username;
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "comment='" + comment + '\'' +
                ", content='" + content + '\'' +
                ", username='" + username + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
