package com.jap.mobile;

import com.jap.mobile.Mobile;
import com.jap.mobile.MobileStockService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class MobileServiceTests {
    MobileStockService mobileStockService;
    List<Mobile> mobileList;

    @BeforeEach
    void setUp()
    {
        mobileStockService = new MobileStockService();
        mobileList = Arrays.asList(new Mobile("Redmi 9","A9",399,7),
                new Mobile("Vivo","X60 Pro",399,7),
                new Mobile("Samsung","W21",810,13),
                new Mobile("SonyXperia","X70",450,17),
                new Mobile("OPPO","F17 Pro",599,10)
        );
    }
    @AfterEach
    void tearDown()
    {
        mobileStockService = null;
    }

    @Test
    public void givenBrandNameReturnTotalCost()
    {
        Predicate<Mobile> mobilePredicate = p -> p.getBrandName().equals("Redmi 9");
        List<Mobile> mobilesFiltered = mobileStockService.filterByBrandName(mobileList,mobilePredicate);
        Predicate<Mobile> mobilePredicate1 = p -> p.getBrandName().equals("OPPO");
        List<Mobile> mobilesFiltered1 = mobileStockService.filterByBrandName(mobileList,mobilePredicate1);
        assertEquals(2793.0,mobileStockService.calculateInvoiceAmountForABrand(mobilesFiltered));
        assertEquals(5990.0,mobileStockService.calculateInvoiceAmountForABrand(mobilesFiltered1));
    }
    @Test
    public void givenBrandNameNotInListReturnZero()
    {
        Predicate<Mobile> mobilePredicate = p -> p.getBrandName().equals("Red");
        List<Mobile> mobilesFiltered = mobileStockService.filterByBrandName(mobileList,mobilePredicate);
        assertEquals(0.0,mobileStockService.calculateInvoiceAmountForABrand(mobilesFiltered));
    }



    @Test
    public void givenMobileListAndConditionReturnListThatSatisfiesCondition()
    {
        List<Mobile> list = Arrays.asList(new Mobile("Vivo","X60 Pro",399,7));
        List<Mobile> listLG = Arrays.asList(new Mobile("LG","G4",399,7));
        Predicate<Mobile> mobilePredicate = p -> p.getBrandName().equals("Vivo");
        List<Mobile> mobilesFiltered = mobileStockService.filterByBrandName(mobileList,mobilePredicate);
        assertTrue(list.size() == mobilesFiltered.size() && list.get(0).getBrandName().equals("Vivo") && mobilesFiltered.get(0).getBrandName().equals("Vivo"));
        assertFalse(listLG.size() == mobilesFiltered.size() && listLG.get(0).getBrandName().equals("Vivo") && mobilesFiltered.get(0).getBrandName().equals("Vivo"));


    }


}
