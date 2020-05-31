/**
 * Books POJO with setter and getter methods . Represents books table.
 */

public class Books {

	protected String username;
	protected String bookname;
	protected String category;

	public Books() {
	}

	public Books(String username, String bookname, String category) {
		this.username = username;
		this.bookname = bookname;
		this.category = category;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
