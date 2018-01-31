/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gokaju.almundocomtest.controllers;

import com.gokaju.almundocomtest.objects.Call;
import com.gokaju.almundocomtest.objects.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author sebas
 */
public class DistpatcherTest {

    public DistpatcherTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public void testDispatchCall() {
        try {
            for (int i = 0; i < 10; i++) {
                Call call = new Call(new Customer("customer"+i));
                Distpatcher instance = Distpatcher.getDistpatcher(5);
                instance.dispatchCall(call);
            }
        } catch (Exception e) {
            fail("The test case is a prototype.");

        }

    }

}
