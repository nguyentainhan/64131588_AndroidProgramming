package ntu.nguyentainhan.easychat.model;

import com.google.firebase.Timestamp;

public class UserModel {
    private String phone;
    private String username;
    private Timestamp createdTimestamp;
    private String userId;

    // BỔ SUNG THÊM:
    private String major;
    private String subject;
    private String teacher;

    public UserModel() {}

    public UserModel(String username, String phone, Timestamp createdTimestamp, String userId) {
        this.username = username;
        this.phone = phone;
        this.createdTimestamp = createdTimestamp;
        this.userId = userId;
    }

    // GETTER + SETTER cho các trường cũ
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Timestamp getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(Timestamp createdTimestamp) { this.createdTimestamp = createdTimestamp; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    // GETTER + SETTER mới
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getTeacher() { return teacher; }
    public void setTeacher(String teacher) { this.teacher = teacher; }
}
