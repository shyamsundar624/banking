package com.banking.shyam.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.shyam.dto.AccountDto;
import com.banking.shyam.entity.Account;
import com.banking.shyam.mapper.AccountMapper;
import com.banking.shyam.repository.AccountRepository;
import com.banking.shyam.service.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);

		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't Exist"));

		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't exist"));
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);

		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't Exist"));
		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient balance");
		}
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<AccountDto> list = accountRepository.findAll().stream().map(AccountMapper::mapToAccountDto).toList();
		return list;
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account doesn't Exist"));
		accountRepository.delete(account);
	}

}
