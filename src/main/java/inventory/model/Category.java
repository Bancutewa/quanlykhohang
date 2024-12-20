package inventory.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;

    @Column(name = "CREATE_DATE", nullable = false)
    private Instant createDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    private Instant updateDate;

    @OneToMany(mappedBy = "cate")
    private Set<inventory.model.ProductInfo> productInfos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Set<inventory.model.ProductInfo> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(Set<inventory.model.ProductInfo> productInfos) {
        this.productInfos = productInfos;
    }

    @Override
    public String toString() {
        return "Category [id=" + id +
                ", name=" + name +
                ", code=" + code +
                ", description=" + description +
                ", activeFlag=" + activeFlag +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate + "]";
    }

}