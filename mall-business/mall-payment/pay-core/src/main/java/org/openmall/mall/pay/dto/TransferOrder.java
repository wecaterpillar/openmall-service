package org.openmall.mall.pay.dto;



import java.math.BigDecimal;
import java.util.Map;

/**
 * 转账订单
 */
@lombok.Data
public class TransferOrder{

    /**
     * 转账批次订单单号
     */
    private String batchNo;

    /**
     * 转账订单单号
     */
    private String outNo;

    /**
     * 收款方账户, 用户openid,卡号等等
     */
    private String  payeeAccount ;

    /**
     * 转账金额
     */
    private BigDecimal amount ;

    /**
     * 付款人名称
     */
    private String payerName;

    /**
     * 收款人名称
     */
    private String payeeName;
    /**
     * 收款人地址
     */
    private String payeeAddress;

    /**
     * 备注
     */
    private String remark;

    /**
     * 收款开户行
      */
    //private Bank bank;
    private String bankCode;
    private String bankName;

    /**
     *  收款开户行地址
     */
    private String payeeBankAddress;

    /**
     * 币种 curType
     */
    private String curCode;
    private String curName;
    /**
     * 国家代码 countryCode
     */
    private String countryCode;
    private String countryName;
    /**
     * 转账类型，收款方账户类型，TransferType 比如支付宝账户或者银行卡
     */
    private String transferType;
    private String transferMethod;

    /**
     * 操作者ip，根据支付平台所需进行设置
     */
    private String ip;


    /**
     * 订单附加信息，可用于预设未提供的参数，这里会覆盖以上所有的订单信息，
     */
    private Map<String, Object> attr;




}
