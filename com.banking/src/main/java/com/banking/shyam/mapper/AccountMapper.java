package com.banking.shyam.mapper;

import com.banking.shyam.dto.AccountDto;
import com.banking.shyam.entity.Account;

public class AccountMapper {

	public static Account mapToAccount(AccountDto accountDto) {
		Account account=new Account(accountDto.getId(),accountDto.getAccountHolderName(),accountDto.getBalance());
		return account;
	}
	
	public static AccountDto mapToAccountDto(Account account) {
		return new AccountDto(account.getId(),account.getAccountHolderName(),account.getBalance());
	}
}
