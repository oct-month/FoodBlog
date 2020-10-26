package cn.ablocker.FoodBlog.entity;

public class BlogUser
{
	private String userName;
	private String passWord;
	private String email;
	
	public BlogUser()
	{
	}

	public BlogUser(String userName, String passWord, String email)
	{
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassWord()
	{
		return passWord;
	}

	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public String toString()
	{
		return "BlogUser [userName=" + userName + ", passWord=" + passWord + ", email=" + email + "]";
	}
}
