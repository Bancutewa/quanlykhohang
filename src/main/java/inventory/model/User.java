package inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "USER_NAME", nullable = false, length = 50)
    private String userName;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;

    @Column(name = "CREATE_DATE", nullable = false)
    private Instant createDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    private Instant updateDate;

    public User() {
        // Gán giá trị mặc định cho activeFlag, createDate và updateDate
        this.activeFlag = 1; // Giá trị mặc định (1: active)
        this.createDate = Instant.now(); // Gán ngày giờ hiện tại
        this.updateDate = Instant.now(); // Gán ngày giờ hiện tại
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

}