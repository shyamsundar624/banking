package com.banking.shyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.shyam.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long>{

}
