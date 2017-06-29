package dago.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Order
 *
 * @author <a href="mailto:morezzww@gmail.com">More</a>
 */


@Table(name = "order_info")
@Entity
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "order_number")
    private String orderNum;    // 订单号
    @Column(name = "user_id")
    private String userId;
    @Column(name = "address")
    private String address;     //收货地址
    @Column(name = "zip_code")
    private String zipCode;     // 邮政编码
    @Column(name = "cosignee")
    private String cosignee;    // 收件人
    @Column(name = "status")
    private Integer status;
    @Column(name = "final_price")
    private Long finalPrice;   // 实际付款
    @Column(name = "total_price")
    private Long totalPrice;   // 总价
    @Column(name = "pay_timestamp")
    private Date payTime;      //付款时间
    @Column(name = "ship_timestamp")
    private Date shipTime;     //发货时间
    @Column(name = "confirm_timestamp")
    private Date confirmTime;  //确认收货时间
    @Column(name = "freight")
    private Long freight;      // 运费
    @Column(name = "commission")
    private Long commission;   // 手续费
    @Column(name = "total_tax")
    private Long totalTax;     // 总税

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCosignee() {
        return cosignee;
    }

    public void setCosignee(String cosignee) {
        this.cosignee = cosignee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Long getFreight() {
        return freight;
    }

    public void setFreight(Long freight) {
        this.freight = freight;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public Long getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Long totalTax) {
        this.totalTax = totalTax;
    }
}
