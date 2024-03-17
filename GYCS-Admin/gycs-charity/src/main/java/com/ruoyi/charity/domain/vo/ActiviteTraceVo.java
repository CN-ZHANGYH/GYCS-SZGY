package com.ruoyi.charity.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author zyh
 * @date 2024/3/17 17:37
 * @desc IntelliJ IDEA
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiviteTraceVo  implements Serializable {


    private Long charityId;


    private BigInteger materialType;


    private String materialName;


    private BigInteger materialCount;


    private String sourceAddress;

    private String logisticAddress;


    private String destAddress;


    private String transTime;


    private Boolean isSign;


    private BigInteger activitId;


    private Integer status;


    private String traceAddress;


    private String traceTime;


    private String transactionHash;

    private BigInteger blockNumber;

    private BigInteger activiteId;

}
