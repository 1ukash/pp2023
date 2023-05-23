package ru.sbstu.icst.hsai.pp;

public class ClassWithSecretData {
	
	protected int notForPublicRead;

	protected int getNotForPublicRead() {
		return notForPublicRead;
	}

	protected void setNotForPublicRead(int notForPublicRead) {
		this.notForPublicRead = notForPublicRead;
	}
	
	

}
