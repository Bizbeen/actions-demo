package com.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    @Test
    public void testWithdrawSuccess() {
        BankAccount account = new BankAccount();
        int remainingBalance = account.withdraw(5000, 1000);
        assertEquals(4000, remainingBalance, "Balance after withdrawing 1000 from 5000 should be 4000");
    }
}

