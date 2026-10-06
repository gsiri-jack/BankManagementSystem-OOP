package org.JBank.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankOperations implements BankFunctions{

    private Tools tools = new Tools();

    @Override
    public int balance(BankAccount bankAccount) {
        return bankAccount.getAmount();
    }

    @Override
    public List<Object> deposit(BankAccount bankAccount, int amount) {
        List<Object> res = bankAccount.deposit_amount(amount);
        tools.display_message(res);
        return res;

    }

    @Override
    public Map<Boolean, String> withdraw(BankAccount bankAccount, int amount) {

        Map<Boolean, String> res= new HashMap<>();
        try {
            boolean status = bankAccount.withdraw_amount(amount);

            if(status){
                res.put(true, "Successful");
            }else{
                res.put(false, "Failed");
            }
        } catch (Exception e) {
            System.out.println(e+"some error while try block of withdraw");
        }

        return res;
    }

    @Override
    public Map<Boolean, String> transfer(
            BankAccount bankAccount,
            BankAccount destinBankAccount,
            int amount
    ) {
        Map<Boolean, String> res= new HashMap<>();
        return res;
    }


}

class Tools{
    public void display_message(List<Object> response){
        if((boolean) response.get(1)){
            System.out.println(response.get(0)+": Transaction Successful ");
            System.out.println("Initial: "+response.get(2)+" after: "+response.get(3));
        }else{
            System.out.println(response.get(0)+": Transaction Failed !! ");
            System.out.println(response.get(2));

        }
    }
}
