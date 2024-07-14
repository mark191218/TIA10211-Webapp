package com.menu.model;

public class MenuVO implements java.io.Serializable {
	private Integer menuNumber;
	private byte[] menuImage;

	public Integer getMenuNumber() {
		return menuNumber;
	}

	public void setMenuNumber(Integer menuNumber) {
		this.menuNumber = menuNumber;
	}

	public byte[] getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(byte[] menuImage) {
		this.menuImage = menuImage;
	}

}
