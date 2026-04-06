package com.opencart.qa.pages;

import org.openqa.selenium.By;

public class CartPage {

	By quantity=By.id("cart");
	
	public void addToCart() {
		System.out.println(quantity);
		System.out.println("cart is updated...");
	}
}
