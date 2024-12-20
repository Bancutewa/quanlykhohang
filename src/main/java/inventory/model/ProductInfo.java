package inventory.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "product_info")
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATE_ID", nullable = false)
    private Category cate;

    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMG_URL", nullable = false, length = 200)
    private String imgUrl;

    @Column(name = "ACTIVE_FLAG", nullable = false)
    private Integer activeFlag;

    @Column(name = "CREATE_DATE", nullable = false)
    private Instant createDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    private Instant updateDate;

    @OneToMany(mappedBy = "product")
    private Set<History> histories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Invoice> invoices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductInStock> productInStocks = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCate() {
        return cate;
    }

    public void setCate(Category cate) {
        this.cate = cate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Set<ProductInStock> getProductInStocks() {
        return productInStocks;
    }

    public void setProductInStocks(Set<ProductInStock> productInStocks) {
        this.productInStocks = productInStocks;
    }

//    Không ánh xạ vô database
    @Transient // Không ánh xạ vào cơ sở dữ liệu
    private MultipartFile multipartFile;

    // Getters và setters cho MultipartFile
    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}