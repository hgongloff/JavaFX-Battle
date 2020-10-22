package application;

import javafx.scene.image.ImageView;

public class Card
{

	private String cardName;
	private int cardValue;
	private ImageView cardImage;

	public Card(String cardName, int cardValue)
	{
		
		this.cardName = cardName;
		this.cardValue = cardValue;
		
	}
	

	public void setCardImage(ImageView cardImage)
	{
		this.cardImage = cardImage;
	}

	public String getCardName()
	{
		return cardName;
	}

	public int getCardValue()
	{
		return cardValue;
	}

	public ImageView getCardImage()
	{
		return cardImage;
	}
	

}




















































































