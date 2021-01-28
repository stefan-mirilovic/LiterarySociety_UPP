package upp.demo.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private Double amount;
    private String merchantId;
    private String paymentMethod;
    private String successUrl;
    private String failedUrl;
    private String errorUrl;
    private String paymentUrl;
}