package com.qa.crm2.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.crm2.utils.ElementUtil;
import com.qa.crm2.constants.AppConstants;
import com.qa.crm2.pages.AddToCartPage;


public class ProductInfoPage {
	
	
		private WebDriver driver;
		private ElementUtil eleUtil;

		private Map<String, String> productMap = new HashMap<String, String>();

	

		private By productHeader = By.tagName("h1");
		private By images = By.cssSelector("ul.thumbnails img");
		private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
		private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
		private By additem = By.xpath("//input[@name='quantity']");
		private By addToCartbutton = By.xpath("//button[@id='button-cart']");
		private By addToCartText= By.xpath("//a[text()='shopping cart']");

		
		public ProductInfoPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
		}

		public String getProductHeader() {
			String header = eleUtil.doGetElementText(productHeader);
			System.out.println(header);
			return header;
		}

		public int getProductImagesCount() {
			int totalImages = eleUtil.waitForElementsVisible(images, 10).size();
			System.out.println("Image count for " + getProductHeader() + " : " + totalImages);
			return totalImages;
		}

//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		private void getProductMetaData() {
			List<WebElement> metaList = eleUtil.getElements(productMetaData);
			for (WebElement e : metaList) {
				String text = e.getText();
				String metakey = text.split(":")[0].trim();
				String metaValue = text.split(":")[1].trim();
				productMap.put(metakey, metaValue);
			}
		}

//		$2,000.00
//		Ex Tax: $2,000.00
		private void getProductPriceData() {
			System.out.println("getting price data");
			List<WebElement> priceList = eleUtil.getElements(productPriceData);
			String price = priceList.get(0).getText();
			String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
			productMap.put("productprice", price);
			productMap.put("extaxprice", exTaxPrice);

		}

		public Map<String, String> getProductDetailsMap() {
			productMap.put("header", getProductHeader());
			productMap.put("productimages", String.valueOf(getProductImagesCount()));
			getProductMetaData();
			getProductPriceData();
			System.out.println("product Details: \n" + productMap);
			return productMap;
		}
		
		 public void increaseItem() {
			  eleUtil.doSendKeys(additem,"2");
		  }
		  
		  public String addToCart() {
			  eleUtil.doClick(addToCartbutton);
		      return eleUtil.waitForElementVisible(addToCartText, AppConstants.SHORT_DEFAUTT_WAIT).getText();
		 }
		  
		 public AddToCartPage clickOnShoppingCart() {
			 eleUtil.doClick(addToCartText);
			 //eleUtil.waitForElementVisible(addToCartText, 10).click();
			return new AddToCartPage(driver);
		 }


}
