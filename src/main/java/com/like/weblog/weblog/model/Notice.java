package com.like.weblog.weblog.model;

public class Notice {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.notifier
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private String notifier;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.receiver
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private String receiver;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.status
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.type
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.notified_cotent
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private String notifiedCotent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.notified_time
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private Long notifiedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.notifier_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private Long notifierId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.receiver_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private Long receiverId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column notice.content_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    private Integer contentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.id
     *
     * @return the value of notice.id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.id
     *
     * @param id the value for notice.id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.notifier
     *
     * @return the value of notice.notifier
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public String getNotifier() {
        return notifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.notifier
     *
     * @param notifier the value for notice.notifier
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setNotifier(String notifier) {
        this.notifier = notifier == null ? null : notifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.receiver
     *
     * @return the value of notice.receiver
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.receiver
     *
     * @param receiver the value for notice.receiver
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.status
     *
     * @return the value of notice.status
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.status
     *
     * @param status the value for notice.status
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.type
     *
     * @return the value of notice.type
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.type
     *
     * @param type the value for notice.type
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.notified_cotent
     *
     * @return the value of notice.notified_cotent
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public String getNotifiedCotent() {
        return notifiedCotent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.notified_cotent
     *
     * @param notifiedCotent the value for notice.notified_cotent
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setNotifiedCotent(String notifiedCotent) {
        this.notifiedCotent = notifiedCotent == null ? null : notifiedCotent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.notified_time
     *
     * @return the value of notice.notified_time
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public Long getNotifiedTime() {
        return notifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.notified_time
     *
     * @param notifiedTime the value for notice.notified_time
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setNotifiedTime(Long notifiedTime) {
        this.notifiedTime = notifiedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.notifier_id
     *
     * @return the value of notice.notifier_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public Long getNotifierId() {
        return notifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.notifier_id
     *
     * @param notifierId the value for notice.notifier_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setNotifierId(Long notifierId) {
        this.notifierId = notifierId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.receiver_id
     *
     * @return the value of notice.receiver_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.receiver_id
     *
     * @param receiverId the value for notice.receiver_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column notice.content_id
     *
     * @return the value of notice.content_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column notice.content_id
     *
     * @param contentId the value for notice.content_id
     *
     * @mbg.generated Tue Jul 16 19:37:37 CST 2019
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}