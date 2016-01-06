package net.dragberry.expman.business;

import java.io.Serializable;

import net.dragberry.expman.bean.AccountBalanceTO;
import net.dragberry.expman.bean.AccountTO;
import net.dragberry.expman.bean.ResultListTO;
import net.dragberry.expman.query.AccountBalanceQuery;
import net.dragberry.expman.query.AccountQuery;

public interface AccountService extends Serializable {

	ResultListTO<AccountBalanceTO> fetchAccountBalances(AccountBalanceQuery query);

	ResultListTO<AccountTO> fetchAccounts(AccountQuery accountQuery);

}
