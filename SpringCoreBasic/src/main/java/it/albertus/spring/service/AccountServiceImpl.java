package it.albertus.spring.service;

import it.albertus.spring.dao.AccountDao;

import org.springframework.beans.factory.annotation.Required;

public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	@Required
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public String toString() {
		return "AccountServiceImpl [accountDao=" + accountDao + "]";
	}

}
