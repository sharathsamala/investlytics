package com.twitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.SentimentApi.convertTxt;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Servlet implementation class TwitterSearch
 */
@WebServlet(description = "TwitterSearch", urlPatterns = { "/TwitterSearch" })
public class TwitterSearch1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String pure=null;
		PrintWriter pr = response.getWriter();
		String username = request.getParameter("username");
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("I5f0jrKUTvwaXKku4OfpIsStj")
			.setOAuthConsumerSecret("9iq7c5cmhnGxxXkxLUibFaFzNoj7zLsfmE7ljfFw4YuNut6tlP")
			.setOAuthAccessToken("3298416996-0lnL5bxn80PcnvfEa0G4Xj5vxIMwhGmZns3UvrB")
			.setOAuthAccessTokenSecret("ra56T1n8TNO2dLHL7DwCnvghkg87KdJMSBvhkUljEC7Mb");
	TwitterFactory tf = new TwitterFactory(cb.build());
	Twitter twitter = tf.getInstance();
	
	List<Status> statuses1 = null;
	
	try {
		int pageno = 1;
		Paging page = new Paging(pageno++, 100);
//		String screenname = twitter.getScreenName();
		try {
			statuses1 = twitter.getUserTimeline(username, page);
		} catch (Exception e) {
			System.out.println("No twitter record found for the username you enterted");
		}
		
		for (Status status1 : statuses1) {
			// if(status1.getText().toLowerCase().contains("money")){
			// System.out.println( status1.getText());
			String tweet = status1.getText();
			pure = tweet.replaceAll("@\\p{L}+", "");
			System.out.println(pure);
			
			
			// }
		}
		
		
		String option1 = null;
        String option2 = null;
        String option3 = null;
        String personality = null;
        String education = null;
        String gender = null;
        String age= null;
        String sentiment = null;
        String language = null;
        String lang_confidence = null;
        String sentiment_Confidence = null;
        String age_confidence = null;
        String educ_confidence = null;
        String personality_confidece = null;
        String gender_confidence = null;
		
		
	//////////////////////////////////////// SENTIMENT API ////////////////////////////////////////
		
		



		  try {
//	String sampleText="This is some sample text that is going to be used for testing.";
			  String sampleText=pure;
//			  BufferedReader fbr = new BufferedReader(new FileReader("D://Tweet_Abhiroop.txt"));
//			  try {
//			      StringBuilder sb = new StringBuilder();
//			      String line = fbr.readLine();
//
//			      while (line != null) {
//			          sb.append(line);
//			          sb.append(System.lineSeparator());
//			          line = fbr.readLine();
//			      }
//			       sampleText= sb.toString();
//			       convertTxt conTxt = new convertTxt();
//			       sampleText  = conTxt.getOnlyStrings(sampleText);
//			  } finally {
//			      fbr.close();
//			  }
//			  
			  convertTxt conTxt = new convertTxt();
		       sampleText  = conTxt.getOnlyStrings(sampleText);
			  
			  
	sampleText=sampleText.replace(" ", "+");
	
//	sampleText="I+love+this+book!";
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
			for (int i = 0; i < urlList.size(); i++) {

				conn=  (HttpURLConnection) urlList.get(i).openConnection();
//				conn.setRequestMethod("POST");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

//				String output;
				
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
//					output=output.replaceAll("\"", "");
//					System.out.println("After format"+output);

					try{
						JSONObject jObject = new JSONObject(output);
						Iterator<?> keys = jObject.keys();

						if(output.contains("age")&& !output.contains("language")){
							System.out.println("age yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								AgeMap.put(key, value);
							}
							System.out.println("AgeJson : "+jObject);
							System.out.println("AgeMap : "+AgeMap);
						} 
						else if(output.contains("language")){
							System.out.println("language yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								LanguageMap.put(key, value);
							}
							System.out.println("LanguageJson : "+jObject);
							System.out.println("LanguageMap : "+LanguageMap);
						}
						else if(output.contains("polarity")){
							System.out.println("polarity yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								SentimentMap.put(key, value);
							}
							System.out.println("SentimentJson : "+jObject);
							System.out.println("SentimentMap : "+SentimentMap);
						}
						else if(output.contains("gender")){
							System.out.println("gender yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								GenderMap.put(key, value);
							}
							System.out.println("GenderJson : "+jObject);
							System.out.println("GenderMap : "+GenderMap);
						}
						else if(output.contains("education")){ 
							System.out.println("education yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								EducationMap.put(key, value);
							}
							System.out.println("EducationJson : "+jObject);
							System.out.println("EducationMap : "+EducationMap);
						}
						else if(output.contains("personality")){ 
							System.out.println("personality yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								PersonalityMap.put(key, value);
							}
							System.out.println("PersonalityJson : "+jObject);
							System.out.println("PersonalityMap : "+PersonalityMap);
						}
						else if(output.contains("genre")){ 
							System.out.println("genre yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								GenreMap.put(key, value);
							}
							System.out.println("GenreJson : "+jObject);
							System.out.println("GenreMap : "+GenreMap);
						}
						else if(output.contains("concepts")){ 
							System.out.println("concepts yes!");
							while( keys.hasNext() ){
								String key = (String)keys.next();
								String value = ""+jObject.get(key) ;
								ConceptsMap.put(key, value);
							}
							System.out.println("ConceptsJson : "+jObject);
							System.out.println("ConceptsMap : "+ConceptsMap);
						}


					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

				conn.disconnect();

			}
			

			
			Connection DBcon=null;
			try{  
				Class.forName("com.mysql.jdbc.Driver");  
				DBcon= DriverManager.getConnection("jdbc:mysql://localhost:3306/smartthinkers","root","Tech1234");  
				//here sonoo is database name, root is username and password  
				
				//truncate table
				PreparedStatement psUserParamTrunc = DBcon.prepareStatement("TRUNCATE user_parameter_info ");
				psUserParamTrunc.execute();
				System.out.println("user parameters truncated..");
				
				
				String insertSQL="INSERT INTO user_parameter_info (DATA_CREATE_DT,LANGUAGE,"+
"LANG_CONFIDENCE,  SENTIMENT,  SENTIMENT_CONFIDENCE,  AGE,  AGE_CONFIDENCE,  GENDER,  GENDER_CONFIDENCE,  "+
"EDUCATION,  EDUCATION_CONFIDENCE,  PERSONALITY,  PERSONALITY_CONFIDENCE,  GENRE,  GENRE_CONFIDENCE) " +
"VALUES(NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				
		
				
//Uncomment when TextGainAPI server is reset				
				PreparedStatement psUserParam = DBcon.prepareStatement(insertSQL);
				psUserParam.setString(1,(LanguageMap.get("language")));
				psUserParam.setString(2,(LanguageMap.get("confidence")));
				psUserParam.setString(3,(SentimentMap.get("polarity")));
				psUserParam.setString(4,(SentimentMap.get("confidence")));
				psUserParam.setString(5,(AgeMap.get("age")));
				psUserParam.setString(6,(AgeMap.get("confidence")));
				psUserParam.setString(7,(GenderMap.get("gender")));
				psUserParam.setString(8,(GenderMap.get("confidence")));
		//		psUserParam.setString(9,(ConceptsMap.get("concepts")!=null)?ConceptsMap.get("concepts").trim():"" );
				psUserParam.setString(9,(EducationMap.get("education")));
				psUserParam.setString(10,(EducationMap.get("confidence")));
				psUserParam.setString(11,(PersonalityMap.get("personality")));
				psUserParam.setString(12,(PersonalityMap.get("confidence")));
				psUserParam.setString(13,(GenreMap.get("genre")));
				psUserParam.setString(14,(GenreMap.get("confidence")));
				psUserParam.execute();
				System.out.println("records inserted..");
	//			psUserParam.setString(15,(GenreMap.get("gender")!=null)?GenderMap.get("gender").trim():"" );

				
//				PreparedStatement psUserParam = DBcon.prepareStatement(insertSQL);
//				psUserParam.setString(1,"en");
//				psUserParam.setString(2,"0.95");
//				psUserParam.setString(3,"+1.0" );
//				psUserParam.setString(4,"0.68" );
//				psUserParam.setString(5,"25+" );
//				psUserParam.setString(6,"0.75" );
//				psUserParam.setString(7,"m" );
//				psUserParam.setString(8,"0.68" );
//				psUserParam.setString(9,"book" );
//				psUserParam.setString(10,"+" );
//				psUserParam.setString(11,"0.70" );
//				psUserParam.setString(12,"I" );
//				psUserParam.setString(13,"0.80" );
//				psUserParam.setString(14,"status" );
//				psUserParam.setString(15,"0.85" );
//				psUserParam.setString(16,sampleText);
				
				
	
				
				
				String resultSQL="SELECT static.INVESTMENT_1,static.INVESTMENT_2,static.INVESTMENT_3,"+
" static.PERSONALITY,static.EDUCATION, static.GENDER, static.AGE, static.SENTIMENT ,"+
" user.LANGUAGE, user.LANG_CONFIDENCE,user.SENTIMENT_CONFIDENCE , user.AGE_CONFIDENCE,user.EDUCATION_CONFIDENCE, user.PERSONALITY_CONFIDENCE,"+
" user.GENDER_CONFIDENCE   "+
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
					
					option1 = (rsResults.getString(1));
					option2 = (rsResults.getString(2));
					option3 = (rsResults.getString(3));
					personality = (rsResults.getString(4).contains("+") ? "Highly Qualified" : "Basic");
					education = (rsResults.getString(5));
					gender = (rsResults.getString(6));
					age= (rsResults.getString(7));
					sentiment = (rsResults.getString(8).contains("+") ? "Positive" : "negative");
					language = (rsResults.getString(9));
					lang_confidence = (rsResults.getString(10));
					sentiment_Confidence = (rsResults.getString(11));
					age_confidence = (rsResults.getString(12));
					educ_confidence = (rsResults.getString(13));
					personality_confidece = (rsResults.getString(14));
					gender_confidence = (rsResults.getString(15));
					
					
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

		
		  
		  ///////////////////////////////////////////////////////End of sentiment api ////////////
		
		
		
		
		
		
		
		
		
		
		
		 pr.println("<html>");
		 pr.println("<body>");

pr.println("<!-- #######  YAY, I AM THE SOURCE EDITOR! #########-->                                         																");
pr.println("<h1 style='color: #FA8807;'>Hi "+username+"&nbsp;</h1>                                                                                                              ");
pr.println("<h2 style='color: #FA8807;'>We are glad to inform you that below are results for the&nbsp;sentiment/personality analysis we made on your twitter data</h2>     ");
pr.println("<table style='height: 59px;' width='553'>                                                                                                                      ");
pr.println("<tbody>                                                                                                                                                        ");
pr.println("<tr>                                                                                                                                                           ");
pr.println("<td>Parameter</td>                                                                                                                                             ");
pr.println("<td>Language</td>                                                                                                                                              ");
pr.println("<td>Polarity</td>                                                                                                                                              ");
pr.println("<td>Age</td>                                                                                                                                                   ");
pr.println("<td>Gender</td>                                                                                                                                                ");
pr.println("<td>Education</td>                                                                                                                                             ");
pr.println("<td>Personality</td>                                                                                                                                           ");
pr.println("</tr>                                                                                                                                                          ");
pr.println("<tr>                                                                                                                                                           ");
pr.println("<td>Result</td>                                                                                                                                                ");
pr.println("<td>&nbsp;"+language+"</td>                                                                                                                                         ");
pr.println("<td>&nbsp;"+sentiment+"</td>                                                                                                                                        ");
pr.println("<td>&nbsp;"+age+"</td>                                                                                                                                              ");
pr.println("<td>"+gender+"</td>                                                                                                                                                  ");
pr.println("<td>"+education+"</td>                                                                                                                                         ");
pr.println("<td>"+personality+"</td>                                                                                                                                            ");
pr.println("</tr>                                                                                                                                                          ");
pr.println("<tr>                                                                                                                                                           ");
pr.println("<td>Confidence</td>                                                                                                                                            ");
pr.println("<td>&nbsp;"+lang_confidence+"</td>                                                                                                                                              ");
pr.println("<td>"+sentiment_Confidence+"</td>                                                                                                                                                    ");
pr.println("<td>"+age_confidence+"</td>                                                                                                                                                    ");
pr.println("<td>"+gender_confidence+"</td>                                                                                                                                                    ");
pr.println("<td>"+educ_confidence+"</td>                                                                                                                                                    ");
pr.println("<td>"+personality_confidece+"</td>                                                                                                                                                    ");
pr.println("</tr>                                                                                                                                                          ");
pr.println("</tbody>                                                                                                                                                       ");
pr.println("</table>                                                                                                                                                       ");
pr.println("<h2 style='color: #FA8807;'>Investment Options :&nbsp;</h2>                                                                                                    ");
pr.println("<p><strong>&nbsp;</strong></p>                                                                                                                                 ");
pr.println("<table style='height: 21px;' width='472'>                                                                                                                      ");
pr.println("<tbody>                                                                                                                                                        ");
pr.println("<tr>                                                                                                                                                           ");
pr.println("<td>Option 1&nbsp;</td>                                                                                                                                        ");
pr.println("<td>Option 2</td>                                                                                                                                              ");
pr.println("<td>Option 3</td>                                                                                                                                              ");
pr.println("</tr>                                                                                                                                                          ");
pr.println("<tr>");
pr.println("<td>"+option1+"&nbsp;</td>");
pr.println("<td>"+option2+"</td>");
				pr.println("<td>"+option3+"</td>");
						pr.println("</tr>");
pr.println("</tbody>                                                                                                                                                       ");
pr.println("</table>                                                                                                                                                       ");

pr.println("<h2 style='color: #FA8807;'>Analyzed Tweets/txt  :&nbsp;</h2>                                                                                                    ");
pr.println("<p><strong>&nbsp;</strong></p>                                                                                                                                 ");
pr.println("<table style='height: 21px;' width='472'>                                                                                                                      ");
pr.println("<tbody>                                                                                                                                                        ");
pr.println("<tr>                                                                                                                                                           ");
pr.println("<td>"+pure+"&nbsp;</td>                                                                                                                                        ");
pr.println("</tr>                                                                                                                                                          ");
pr.println("</tbody>                                                                                                                                                       ");
pr.println("</table>                                                                                                                                                       ");


		 pr.println("</body>");
		 pr.println("</html>");

	
	} catch (Exception e) {
		// TODO: handle exception
	}



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
