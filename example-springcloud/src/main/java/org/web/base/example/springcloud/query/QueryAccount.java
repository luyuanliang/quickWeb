package org.web.base.example.springcloud.query;

import lombok.Getter;
import lombok.Setter;
import org.web.base.base.domain.QueryBase;

import java.util.List;

@Setter
@Getter
public class QueryAccount extends QueryBase {


    private Long accountId;

    private List < Long > accountIdList;

    private String accountName;

    private List < String > accountNameList;

    private Long accountNo;

    private Long indistinctAccountNo;

    private String isDelete;
}

