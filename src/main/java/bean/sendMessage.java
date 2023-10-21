/**
 * 作者：兰文捷
 * 时间：2023.8.14
 * 功能：发送消息的实体类
 */
package bean;

public class sendMessage {
    private String sender;

    private String recipient;

    private String message;

    private String sendTime;

    public sendMessage(String sender, String recipient, String message, String sendTime) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
        this.sendTime = sendTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public sendMessage() {
    }
}
