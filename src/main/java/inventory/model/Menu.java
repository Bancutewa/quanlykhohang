package inventory.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "PARENT_ID")
    private int parentId; // Giữ parentId là int

    @Column(name = "URL", nullable = false, length = 100)
    private String url;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "ORDER_INDEX", nullable = false)
    private Integer orderIndex;

    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;

    @Column(name = "CREATE_DATE", nullable = false)
    private Instant createDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    private Instant updateDate;

    @OneToMany(mappedBy = "menu") // Quan hệ với Auth (nếu có)
    private Set<Auth> auths = new LinkedHashSet<>();

    @Transient
    private List<Menu> child;

    @Transient
    private String idMenu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
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

    public List<Menu> getChild() {
        return child;
    }

    public void setChild(List<Menu> child) {
        this.child = child;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }
}
