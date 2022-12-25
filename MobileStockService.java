package com.jap.mobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class MobileStockService {
    //Display all the products
    public void displayAllProducts(List<Mobile> mobileList)
    {
        Consumer<Mobile> ref =(data)-> System.out.println(data);
        mobileList.forEach(ref);
    }

    // This method will filter by the brand name
    public List<Mobile> filterByBrandName(List<Mobile> mobiles, Predicate<Mobile> pre) {

        List<Mobile> mob = new ArrayList<>();

        for(Mobile m : mobiles)
        {
            boolean res = pre.test(m);
            if(res)
            {
                mob.add(m);
            }
        }

        return mob;
    }

    //Calculate total invoice amount for a particular brand of phone
    public double calculateInvoiceAmountForABrand(List<Mobile> mobileList) {
       // Use the Function Interface to perform the calculations

        double res=0;

        for(Mobile m : mobileList)
        {
            res = m.getAmount()*m.getQuantity();
        }

        return res;
    }


}
