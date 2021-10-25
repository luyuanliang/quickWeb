package org.web.base.example.springcloud.view;


import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.web.base.domain.view.ViewResultDO;
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
        ViewResultDO<AccountDO> resultDO = new ViewResultDO<>();
        return new Gson().toJson(resultDO);
    }

    @RequestMapping(value = "selectAccountByAccountId", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String selectAccountByAccountId(QueryAccount queryAccount) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ViewResultDO<AccountDO> resultDO = new ViewResultDO<>(true);
        AccountDO accountDO = accountService.selectAccountByAccountId(queryAccount.getAccountId());
        resultDO.setData(accountDO);
        return new Gson().toJson(resultDO);
    }
}
