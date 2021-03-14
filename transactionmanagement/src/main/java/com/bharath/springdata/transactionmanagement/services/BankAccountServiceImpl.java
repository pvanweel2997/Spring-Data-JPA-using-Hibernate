package com.bharath.springdata.transactionmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bharath.springdata.transactionmanagement.entities.BankAccount;
import com.bharath.springdata.transactionmanagement.repos.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	BankAccountRepository repository;

	@Override
	@Transactional
	public void transfer(int amount) {
		BankAccount obamasAccount = repository.findById(1).get();
		obamasAccount.setBalance(obamasAccount.getBalance() - amount);
		repository.save(obamasAccount);
		
		BankAccount trumpsAccount = repository.findById(2).get();
		trumpsAccount.setBalance(trumpsAccount.getBalance() + amount);
		repository.save(trumpsAccount);
	}

}
