import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.sql.*;

import org.json.JSONException;
import org.json.JSONObject;

public class SampleUrlTest {
	 public static String getOnlyStrings(String s) {
		    Pattern pattern = Pattern.compile("[^a-z A-Z]");
		    Matcher matcher = pattern.matcher(s);
		    String number = matcher.replaceAll("");
		    return number;
		 }
	 
	public static void main(String[] args) {


		  try {
//	String sampleText="This is some sample text that is going to be used for testing.";
			  String sampleText=null;
			  BufferedReader fbr = new BufferedReader(new FileReader("D://Tweet_Abhiroop.txt"));
			  try {
			      StringBuilder sb = new StringBuilder();
			      String line = fbr.readLine();

			      while (line != null) {
			          sb.append(line);
			          sb.append(System.lineSeparator());
			          line = fbr.readLine();
			      }
			       sampleText= sb.toString();
			       sampleText  = getOnlyStrings(sampleText);
			  } finally {
			      fbr.close();
			  }
			  
			  
			  
	sampleText=sampleText.replace(" ", "+");
	
	sampleText="I+love+this+book!";
			System.out.println(sampleText);
			
			URL urlLanguage = new URL("https://api.textgain.com/1/language?q="+sampleText);
			URL urlAge = new URL("https://api.textgain.com/1/age?q="+sampleText);
			URL urlSentiment = new URL("https://api.textgain.com/1/sentiment?q="+sampleText);
			URL urlGender = new URL("https://api.textgain.com/1/gender?q="+sampleText);
			URL urlEducation = new URL("https://api.textgain.com/1/education?q="+sampleText);
			URL urlPersonality = new URL("https://api.textgain.com/1/personality?q="+sampleText);
			URL urlGenre = new URL("https://api.textgain.com/1/genre?q="+sampleText);
			URL urlConcepts = new URL("https://api.textgain.com/1/concepts?q="+sampleText);
			
			List<URL> urlList= new ArrayList();
			urlList.add(urlAge);
			urlList.add(urlSentiment);
			urlList.add(urlLanguage);
			urlList.add(urlEducation);
			urlList.add(urlGender);
			urlList.add(urlPersonality);
			urlList.add(urlGenre);
			urlList.add(urlConcepts);
			
			HashMap<String, String> AgeMap = new HashMap<String, String>();
			HashMap<String, String> SentimentMap = new HashMap<String, String>();
			HashMap<String, String> LanguageMap = new HashMap<String, String>();
			HashMap<String, String> GenderMap = new HashMap<String, String>();
			HashMap<String, String> EducationMap = new HashMap<String, String>();
			HashMap<String, String> PersonalityMap = new HashMap<String, String>();
			HashMap<String, String> GenreMap = new HashMap<String, String>();
			HashMap<String, String> ConceptsMap = new HashMap<String,String>();
			
			
			
			HttpURLConnection conn;
			String output="";			
//			for (int i = 0; i < urlList.size(); i++) {
//
//				conn=  (HttpURLConnection) urlList.get(i).openConnection();
////				conn.setRequestMethod("POST");
//				conn.setRequestProperty("Accept", "application/json");
//
//				if (conn.getResponseCode() != 200) {
//					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//				}
//
//				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
////				String output;
//				
//				System.out.println("Output from Server .... \n");
//				while ((output = br.readLine()) != null) {
//					System.out.println(output);
////					output=output.replaceAll("\"", "");
////					System.out.println("After format"+output);
//
//					try{
//						JSONObject jObject = new JSONObject(output);
//						Iterator<?> keys = jObject.keys();
//
//						if(output.contains("age")&& !output.contains("language")){
//							System.out.println("age yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								AgeMap.put(key, value);
//							}
//							System.out.println("AgeJson : "+jObject);
//							System.out.println("AgeMap : "+AgeMap);
//						} 
//						else if(output.contains("language")){
//							System.out.println("language yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								LanguageMap.put(key, value);
//							}
//							System.out.println("LanguageJson : "+jObject);
//							System.out.println("LanguageMap : "+LanguageMap);
//						}
//						else if(output.contains("polarity")){
//							System.out.println("polarity yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								SentimentMap.put(key, value);
//							}
//							System.out.println("SentimentJson : "+jObject);
//							System.out.println("SentimentMap : "+SentimentMap);
//						}
//						else if(output.contains("gender")){
//							System.out.println("gender yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								GenderMap.put(key, value);
//							}
//							System.out.println("GenderJson : "+jObject);
//							System.out.println("GenderMap : "+GenderMap);
//						}
//						else if(output.contains("education")){ 
//							System.out.println("education yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								EducationMap.put(key, value);
//							}
//							System.out.println("EducationJson : "+jObject);
//							System.out.println("EducationMap : "+EducationMap);
//						}
//						else if(output.contains("personality")){ 
//							System.out.println("personality yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								PersonalityMap.put(key, value);
//							}
//							System.out.println("PersonalityJson : "+jObject);
//							System.out.println("PersonalityMap : "+PersonalityMap);
//						}
//						else if(output.contains("genre")){ 
//							System.out.println("genre yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								GenreMap.put(key, value);
//							}
//							System.out.println("GenreJson : "+jObject);
//							System.out.println("GenreMap : "+GenreMap);
//						}
//						else if(output.contains("concepts")){ 
//							System.out.println("concepts yes!");
//							while( keys.hasNext() ){
//								String key = (String)keys.next();
//								String value = ""+jObject.get(key) ;
//								ConceptsMap.put(key, value);
//							}
//							System.out.println("ConceptsJson : "+jObject);
//							System.out.println("ConceptsMap : "+ConceptsMap);
//						}
//
//
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				}
//
//				conn.disconnect();
//
//			}
			
			
			Connection DBcon=null;
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				DBcon= DriverManager.getConnection("jdbc:mysql://localhost:3306/SmartThinkers","root","Tech1234");  
				//here sonoo is database name, root is username and password  
				String insertSQL="INSERT INTO user_parameter_info (DATA_CREATE_DT,LANGUAGE,"+
  "LANG_CONFIDENCE,  SENTIMENT,  SENTIMENT_CONFIDENCE,  AGE,  AGE_CONFIDENCE,  GENDER,  GENDER_CONFIDENCE,  CONCEPTS,"+
  "EDUCATION,  EDUCATION_CONFIDENCE,  PERSONALITY,  PERSONALITY_CONFIDENCE,  GENRE,  GENRE_CONFIDENCE,  INPUT_TEXT) " +
  "VALUES(NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
		
				
// Uncomment when TextGainAPI server is reset				
//				PreparedStatement psUserParam = DBcon.prepareStatement(insertSQL);
//				psUserParam.setString(1,(LanguageMap.get("language")!=null)?LanguageMap.get("language").trim():"" );
//				psUserParam.setString(2,(LanguageMap.get("confidence")!=null)?LanguageMap.get("confidence").trim():"");
//				psUserParam.setString(3,(SentimentMap.get("polarity")!=null)?SentimentMap.get("polarity").trim():"" );
//				psUserParam.setString(4,(SentimentMap.get("confidence")!=null)?SentimentMap.get("confidence").trim():"" );
//				psUserParam.setString(5,(AgeMap.get("age")!=null)?AgeMap.get("age").trim():"" );
//				psUserParam.setString(6,(AgeMap.get("confidence")!=null)?AgeMap.get("confidence").trim():"" );
//				psUserParam.setString(7,(GenderMap.get("gender")!=null)?GenderMap.get("gender").trim():"" );
//				psUserParam.setString(8,(GenderMap.get("confidence")!=null)?GenderMap.get("confidence").trim():"" );
//				psUserParam.setString(9,(ConceptsMap.get("concepts")!=null)?ConceptsMap.get("concepts").trim():"" );
//				psUserParam.setString(10,(EducationMap.get("education")!=null)?EducationMap.get("education").trim():"" );
//				psUserParam.setString(11,(EducationMap.get("confidence")!=null)?EducationMap.get("confidence").trim():"" );
//				psUserParam.setString(12,(PersonalityMap.get("personality")!=null)?PersonalityMap.get("pesonality").trim():"" );
//				psUserParam.setString(13,(PersonalityMap.get("confidence")!=null)?PersonalityMap.get("confidence").trim():"" );
//				psUserParam.setString(14,(GenreMap.get("genre")!=null)?GenreMap.get("genre").trim():"" );
//				psUserParam.setString(15,(GenreMap.get("confidence")!=null)?GenreMap.get("confidence").trim():"" );
//				psUserParam.setString(16,output);
				PreparedStatement psUserParamTrunc = DBcon.prepareStatement("TRUNCATE user_parameter_info ");
				psUserParamTrunc.execute();
				System.out.println("user parameters truncated..");
				
				PreparedStatement psUserParam = DBcon.prepareStatement(insertSQL);
				psUserParam.setString(1,"en");
				psUserParam.setString(2,"0.95");
				psUserParam.setString(3,"+1.0" );
				psUserParam.setString(4,"0.68" );
				psUserParam.setString(5,"25+" );
				psUserParam.setString(6,"0.75" );
				psUserParam.setString(7,"m" );
				psUserParam.setString(8,"0.68" );
				psUserParam.setString(9,"book" );
				psUserParam.setString(10,"+" );
				psUserParam.setString(11,"0.70" );
				psUserParam.setString(12,"I" );
				psUserParam.setString(13,"0.80" );
				psUserParam.setString(14,"status" );
				psUserParam.setString(15,"0.85" );
				psUserParam.setString(16,sampleText);
				psUserParam.execute();
				System.out.println("records inserted..");
				
				
				String resultSQL="SELECT static.INVESTMENT_1,static.INVESTMENT_2,static.INVESTMENT_3,"+
 " static.PERSONALITY,static.EDUCATION, static.GENDER, static.AGE, static.SENTIMENT ,"+
" user.LANGUAGE, user.LANG_CONFIDENCE,user.SENTIMENT_CONFIDENCE , user.AGE_CONFIDENCE,user.EDUCATION_CONFIDENCE, user.PERSONALITY_CONFIDENCE,"+
" user.GENRE_CONFIDENCE   "+
" FROM PARAMETER_STATIC_ENTRIES static, user_parameter_info user "+
						 " WHERE static.PERSONALITY=user.PERSONALITY  "+
						" AND static.EDUCATION=user.EDUCATION "+
						" AND static.GENDER=user.GENDER "+
						" AND static.AGE=user.AGE "+
						" AND static.SENTIMENT=user.SENTIMENT";	
				PreparedStatement psResults = DBcon.prepareStatement(resultSQL);
				ResultSet rsResults= psResults.executeQuery();
				while(rsResults.next()){
					System.out.println(rsResults.getString(1));
					System.out.println(rsResults.getString(2));
					System.out.println(rsResults.getString(3));
					System.out.println(rsResults.getString(4));
					System.out.println(rsResults.getString(5));
					System.out.println(rsResults.getString(6));
					System.out.println(rsResults.getString(7));
					System.out.println(rsResults.getString(8));
					System.out.println(rsResults.getString(9));
					System.out.println(rsResults.getString(10));
					System.out.println(rsResults.getString(11));
					System.out.println(rsResults.getString(12));
					System.out.println(rsResults.getString(13));
					System.out.println(rsResults.getString(14));
					System.out.println(rsResults.getString(15));
					
					
				}
						
				
				
//				ResultSet rs=stmt.executeQuery("select * from PARAMETER_STATIC_ENTRIES");  
//				while(rs.next())  
//				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
  
				}catch(SQLException sqle){ 
					System.out.println("SQL Exception occured while inserting user parameters: "+sqle);sqle.printStackTrace();}
				catch (ClassNotFoundException cnfe) {
					System.out.println("ClassNOtFoundException: "+cnfe); cnfe.printStackTrace();}  
				finally{
				try {
//					DBcon.commit();
					DBcon.close();
				} catch (SQLException sqle) {
					System.out.println("SQLException while committing changes into DB: "+sqle);
					sqle.printStackTrace();
				}}
				 

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		}

	



}
