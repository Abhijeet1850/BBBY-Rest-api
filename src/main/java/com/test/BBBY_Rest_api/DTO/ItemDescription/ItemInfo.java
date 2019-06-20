package com.test.BBBY_Rest_api.DTO.ItemDescription;

public class ItemInfo {

	private String unitPrice;

	private String itemType;

	private String productClassId;

	private String productClass;

	private String color;

	private String formattedStorePrice;

	private String currencySymbol;

	private String showCurrency;

	private String baseUOM;

	private DeliveryOptions deliveryOptions;

	private String itemId;

	private String itemName;

	private String storePrice;

	private String imageURL;

	private String formattedUnitPrice;

	private String style;

	private String itemDescription;

	private String availabilityStatus;

	private String currencyCode;

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getProductClassId() {
		return productClassId;
	}

	public void setProductClassId(String productClassId) {
		this.productClassId = productClassId;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFormattedStorePrice() {
		return formattedStorePrice;
	}

	public void setFormattedStorePrice(String formattedStorePrice) {
		this.formattedStorePrice = formattedStorePrice;
	}

	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public String getShowCurrency() {
		return showCurrency;
	}

	public void setShowCurrency(String showCurrency) {
		this.showCurrency = showCurrency;
	}

	public String getBaseUOM() {
		return baseUOM;
	}

	public void setBaseUOM(String baseUOM) {
		this.baseUOM = baseUOM;
	}

	public DeliveryOptions getDeliveryOptions() {
		return deliveryOptions;
	}

	public void setDeliveryOptions(DeliveryOptions deliveryOptions) {
		this.deliveryOptions = deliveryOptions;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(String storePrice) {
		this.storePrice = storePrice;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getFormattedUnitPrice() {
		return formattedUnitPrice;
	}

	public void setFormattedUnitPrice(String formattedUnitPrice) {
		this.formattedUnitPrice = formattedUnitPrice;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "ClassPojo [unitPrice = " + unitPrice + ", itemType = " + itemType + ", productClassId = "
				+ productClassId + ", productClass = " + productClass + ", color = " + color
				+ ", formattedStorePrice = " + formattedStorePrice + ", currencySymbol = " + currencySymbol
				+ ", showCurrency = " + showCurrency + ", baseUOM = " + baseUOM + ", deliveryOptions = "
				+ deliveryOptions + ", itemId = " + itemId + ", itemName = " + itemName + ", storePrice = " + storePrice
				+ ", imageURL = " + imageURL + ", formattedUnitPrice = " + formattedUnitPrice + ", style = " + style
				+ ", itemDescription = " + itemDescription + ", availabilityStatus = " + availabilityStatus
				+ ", currencyCode = " + currencyCode + "]";
	}

}
