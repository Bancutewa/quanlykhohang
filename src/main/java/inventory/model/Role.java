package inventory.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "ROLE_NAME", nullable = false, length = 50)
    private String roleName;

    @Column(name = "DESCRIPTION", nullable = false, length = 50)
    private String description;

    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;

    @Column(name = "CREATE_DATE", nullable = false)
    private Instant createDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    private Instant updateDate;

    @OneToMany(mappedBy = "role")
    private Set<Auth> auths = new LinkedHashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<inventory.model.UserRole> userRoles = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<Auth> getAuths() {
        return auths;
    }

    public void setAuths(Set<Auth> auths) {
        this.auths = auths;
    }

    public Set<inventory.model.UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<inventory.model.UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}