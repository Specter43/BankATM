package Factory;

import Account.AccountChecking;
import Account.AccountSaving;
import Account.AccountSecurity;

/**
 * This class is a factory that generates checking account, saving account, and security account.
 */
public class AccountFactory {
    private int accountID = 0;

    /**
     * Constructor
     */
    public AccountFactory() {
    }


    /**
     * It generates a checking account and returns it.
     * @return AccountChecking: a checking account
     */
    public AccountChecking generateCheckingAccount(){
        AccountChecking checkingAccount = new AccountChecking();
        return checkingAccount;
    }

    /**
     * It generates a saving account and returns it.
     * @return AccountSaving: a saving account
     */
    public AccountSaving generateSavingAccount(){
        return null;
    }


    /**
     * It generates a security account and returns it
     * @return AccountSecurity: a security account
     */
    public AccountSecurity generateSecurityAccount(){
        return null;
    }

}
