package org.web.base.example.springcloud.view;


import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.web.base.base.domain.view.ViewResult;
import org.web.base.example.springcloud.domain.AccountDO;
import org.web.base.example.springcloud.query.QueryAccount;
import org.web.base.example.springcloud.service.AccountService;

import javax.annotation.Resource;

@Controller
@RequestMapping("account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "insertAccount", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String insertAccount(AccountDO accountDO) {
        ViewResult<AccountDO> resultDO = new ViewResult<>();

        return new Gson().toJson(resultDO);
    }

    @RequestMapping(value = "selectAccountByAccountId", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String selectAccountByAccountId(QueryAccount queryAccount) {
        RequestContextHolder.getRequestAttributes();
        ViewResult<AccountDO> resultDO = new ViewResult<>(true);
        AccountDO accountDO = accountService.selectAccountByAccountId(queryAccount.getAccountId());
        resultDO.setData(accountDO);
        return new Gson().toJson(resultDO);
    }
}
