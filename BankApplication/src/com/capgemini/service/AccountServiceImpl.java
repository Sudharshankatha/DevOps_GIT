package com.capgemini.service;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialAmountException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */

	AccountRepository accountRepository;

	static int intialAmount = 0;
	int acNumber = 101;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public Account createAccount(int accountNumber, int amount)
			throws InsufficientInitialAmountException {
		int a, b, c;
		if (amount < 500) {
			throw new InsufficientInitialAmountException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);

		account.setAmount(amount);

		if (accountRepository.save(account)) {
			return account;
		}

		return null;

	}

	@Override
	public int depositAmmount(int accounNumber, int amount)
			throws InvalidAccountNumberException {
		if (acNumber != accounNumber) {
			throw new InvalidAccountNumberException();
		} else {
			amount = intialAmount + amount;
		}

		return amount;
	}

	@Override
	public int withdrAmount(int accounNumber, int amount)
			throws InvalidAccountNumberException, InsufficientBalanceException {
		int balance = 1000;

		if (acNumber != accounNumber) {
			throw new InvalidAccountNumberException();
		}

		if (balance > amount) {
			balance = balance - amount;
		}
		if (balance <=500) {
			throw new InsufficientBalanceException();
		}

		return balance;
	}

}
