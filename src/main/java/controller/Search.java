package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import beans.Book;

public class Search implements Comparator<Book> {

	public static void main(String[] args){
		for(Book book: new Search().execute("", "a")) {
			System.out.println(book.getTitle());
		}
	}
	
	String so1;
	String so2;

	@Override
	public int compare(Book book1, Book book2) {
		so1 = book1.getTitle();
		so2 = book2.getTitle();
		return so1.compareTo(so2);
	}
	
	public List<Book> execute(String isbn, String title){
		String requestUrl = null;
		if(isbn.equals("")) {
			requestUrl = "https://www.googleapis.com/books/v1/volumes?q=intitle:" + title;
		}else {
			requestUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;
		}
		URL url = null;
		HttpURLConnection con = null;
        try{
			url = new URL(requestUrl);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");// GETリクエスト
			con.setReadTimeout(10000);	// 10秒
			con.setConnectTimeout(10000);// 10秒
		} catch (Exception e) {
			System.out.println("URLConnection" + e);
		}
        StringBuilder builder = null;
        try {
	        InputStreamReader isr = new InputStreamReader(con.getInputStream());
	        BufferedReader responseReader = new BufferedReader(isr);
	        builder = new StringBuilder();
	        String line = responseReader.readLine();
	        while (line != null){
	        	builder.append(line);
	        	line = responseReader.readLine();
	        }
        }catch(Exception e) {
        	return null;
        }
        String responseString = builder.toString();
//        System.out.println(responseString);
        con.disconnect();
        
        JSONObject jsonObject = null;
        List<Book> list;
        try {
        	jsonObject = new JSONObject(responseString);
        	int count = jsonObject.getInt("totalItems");
        	if(count == 0){
        		return null;
        	}
        	JSONArray jsonArray = jsonObject.getJSONArray("items");
        	list = new ArrayList<Book>();
        	count = jsonArray.length();
        	for (int i = 0; i < count; i++) {
        		JSONObject item = jsonArray.getJSONObject(i);
        		JSONObject volumeInfo = item.getJSONObject("volumeInfo");
        		String booktitle = volumeInfo.getString("title");
        		JSONArray authors = null;
        		String firstAuthor = null;
        		try{
        			authors = volumeInfo.getJSONArray("authors");
        			firstAuthor = authors.getString(0);
        		}catch(JSONException e){
        			firstAuthor = "未登録";
        		}
        		String publisher = null;
        		try{
	        		publisher = volumeInfo.getString("publisher");
        		}catch(JSONException e){
        			publisher = "未登録";
        		}
        		String bookISBN = null;
        		try{
        			JSONArray industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");
        			for(int j=0; j<industryIdentifiers.length(); j++) {
        				JSONObject identifier = industryIdentifiers.getJSONObject(j);
        				bookISBN = identifier.getString("identifier");
            			if(identifier.getString("type").equals("ISBN_13")) {
            				break;
            			}
        			}
        		}catch(JSONException e){
        			bookISBN = "未登録";
        		}
//        		System.out.println(bookISBN);
        		String imageURL = null;
        		try{
	        		JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
	        		imageURL = imageLinks.getString("smallThumbnail");
        		}catch(JSONException e){
        			imageURL = "未登録";
        		}
//        		System.out.println(imageURL);
        		String description = null;
        		try{
        			description = volumeInfo.getString("description");	
        		}catch(JSONException e){
        			description = "未登録";
        		}
        		list.add(new Book(booktitle, firstAuthor, publisher, bookISBN, imageURL, description));
        	}
        	Collections.sort(list, new Search());
        	return list;
        }catch(Exception e){
        	return null;
        }
	}

}
