package com.github.jitpack;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Hello world!
 *
 */
public class testGitCode 
{
    public static void main(String[] args)
    {
        System.out.println(new App().greet("world"));
    }

    public String greet(String name) {
        return "Hello " + name;
    }















static String Base64_ET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+-";
	    static char Base64_DT[] = new char[256];

	    static boolean bIsBase64Init = false;

	    
	    void base64_Init()
	    {
		//byte b;
		//b =0;
		    
	      for(int i = 0; i<256; i++)
	      {
		  
		  Base64_DT[i] = (char) -1;
		  //Log.d("iv","byte b: "+b);
		  //b++;
	      }
	      for(int i = 0; i<64; i++)
	      {
	          Base64_DT[Base64_ET.charAt(i)] = (char)i;
	      }
	      bIsBase64Init = true;
	      return;
	    }

	    String base64_decode(String text)
	    {
	  //    char cp;
	      int numBytes;
	      char dest_byte[] = new char[text.length()];
	      
	      char src_byte[] = new char[text.length()+1];
	      
	      int space_idx = 0, phase;
	      int d, prev_d = 0;
	      char c;
	      char ch;
	      int i;
	      int k;
	      
	      if(!bIsBase64Init)
		base64_Init(); 
	      
	      int count;
	      count =0;
	      
	      numBytes = text.length();
	      if(numBytes <=0) return "";
	      //Log.d("iv","base64_decode:===========================================");
	      //Log.d("iv","numBytes:"+numBytes);
	      space_idx = 0;
	      phase = 0;
	      
	      for(k =0;k<text.length();k++)
	      {
		  src_byte[k] = text.charAt(k);
		  dest_byte[k] = 0;
		//  Log.d("iv","base64_decode:" +Integer.toHexString(src_byte[k]));
	      }
	      src_byte[k] =0;
	      i =0;    
	      ch = src_byte[i];
	      
	      for (;ch != 0;i++ ) 
	      {
		//Log.d("iv","i: "+i);
		//Log.d("iv","base64_decode space_idx: "+space_idx);
		ch = src_byte[i];
	        d = (int)Base64_DT[(int) ch];
	       
	        if ( d != 65535 ) //-1
	        {
	          switch ( phase ) 
	          {
	            case 0:
	                    ++phase;
	                    
	                    break;
	            case 1:
	                    c = (char) ( ( prev_d << 2 ) | ( ( d & 0x30 ) >> 4 ) );
	                    if ( space_idx < numBytes )
	                	dest_byte[space_idx++] = (char)c;
	                    ++phase;
	                    break;
	            case 2:
	                    c = (char) ( ( ( prev_d & 0xf ) << 4 ) | ( ( d & 0x3c ) >> 2 ) );
	                    if ( space_idx < numBytes )
	                    dest_byte[space_idx++] = (char)c;
	                    ++phase;
	                    break;
	            case 3:
	                    c = (char) ( ( ( prev_d & 0x03 ) << 6 ) | d );
	                    if ( space_idx < numBytes )
	                    dest_byte[space_idx++] = (char)c;
	                    phase = 0;
	                    count++;
	                    break;
	          }
	          prev_d = d;
	        }
	      }
	      dest_byte[space_idx]='\0';
	      //Log.d("iv","base64_decode space_idx: "+space_idx);
	      
	      /*for(k =0;k<space_idx;k++)
	      {
		  Log.d("iv","base64_decoded :" +Integer.toHexString(dest_byte[k]));
	      }*/
	      count =  space_idx /2;
	      char dest[] = new char[count];
	      
	      for(k =0;k<count;k++)
	      {
		  i = k*2;
		  dest[k] = (char)((dest_byte[i] & 0xff) | ((dest_byte[i+1] <<8)&0xff00)) ;
	      }
	      /*
	      for(k =0;k<count;k++)
	      {
		  Log.d("iv","base64_decoded dest :" +Integer.toHexString(dest[k]));
	      }
	      */
	      //dest[count] = 0;
	      
	      return new String(dest).trim();
	    }

	    String base64_encode(char[] text,int numBytes)
	    {
	      char input[] = {0,0,0};
	      char output[] = {0,0,0,0};
	      int index, i, j, size;
	      int k;
	   
	      //char p, plen;
	      int p;
	      
	      char encodedText_char[];
	      char src_byte[] = new char[numBytes];
	      
	      int l;
	      for(k=0,l=0;k < text.length;k++,l+=2)
	      {
		  src_byte[l] = (char)(text[k] & 0xff);//
	      	  src_byte[l+1] = (char)(text[k]>>8 & 0xff);//
	      }
		
	      if(!bIsBase64Init)
	          base64_Init();
	    
	      //plen = text + numBytes -1;
	      size = (4 * (numBytes / 3));
	      if((numBytes % 3) != 0)
	          size +=4 +1;
	      else 
	          size += 1 ;
	      
	      //Log.d("iv", "size:"+ size);
	      encodedText_char = new char[size+1];
	      
	      j = 0;
	      for (i = 0, p = 0; p <src_byte.length; i++, p++) 
	      {
	        index = i % 3; 
	        
	        input[index] = src_byte[p];
	        if (index == 2 || p == src_byte.length-1) 
	        {
	          output[0] = (char) ((input[0] & 0xFC) >> 2);
	          output[1] = (char) (((input[0] & 0x3) << 4) | ((input[1] & 0xF0) >> 4));
	          output[2] = (char) (((input[1] & 0xF) << 2) | ((input[2] & 0xC0) >> 6));
	          output[3] = (char) (input[2] & 0x3F);
	        
	        /*  Log.d("iv","input[0]:" +Integer.toHexString(input[0]));
	          Log.d("iv","input[1]:" +Integer.toHexString(input[1]));
	          Log.d("iv","input[2]:" +Integer.toHexString(input[2]));
	          Log.d("iv","----------");
	          
	          Log.d("iv","output[0]:" +Integer.toHexString(output[0]));
	          Log.d("iv","output[1]:" +Integer.toHexString(output[1]));
	          Log.d("iv","output[2]:" +Integer.toHexString(output[2]));
	          Log.d("iv","output[3]:" +Integer.toHexString(output[3]));
	          
	          */
	          encodedText_char[j++] = (char)Base64_ET.charAt(output[0]);
	          //Log.d("iv","j : "+j+" output[1]:" +Integer.toString(output[1]));
	          encodedText_char[j++] = (char)Base64_ET.charAt(output[1]);
	          
	          if(index == 0)
	              encodedText_char[j++] = '=';
	          else 
	              encodedText_char[j++] = (char)Base64_ET.charAt(output[2]);
	          if(index < 2)
	              encodedText_char[j++] = '=';
	          else 
	              encodedText_char[j++] = (char)Base64_ET.charAt(output[3]);
	          input[0] = input[1] = input[2] = 0;
	        }
	      }
	      encodedText_char[j] = '\0';
	      encodedText_char[j+1] = '\0';
	      //for(k =0;k<size;k++)
	      //{
//		  Log.d("iv","encodedText_char:" +Integer.toHexString(encodedText_char[k]));
	  //    }
	      String dest = new String(encodedText_char);
	    //  for(k =0;k<size;k++)
	    //  {
//		  Log.d("iv","dest string:" +Integer.toHexString(dest.charAt(k)));
	  //    }
	      return dest;
	    }
	    
	    private String encode3(String src)
	    {
		int i,len;
		char t1;
		char t2;
		char t3;
		int j;
		
		char tmp[] = new char[4];
		
		tmp[0] = 6;
		tmp[1] = 8;
		tmp[2] = 4;
		tmp[3] = 7;
		
		String dest;
		//Log.d("iv","src encode : " + src);
		len = src.length();
		if (len ==0) return "";
		
		char[] buff = new char[len];
		//Log.d("iv","encode len : " + len);
		
		src.getChars(0,len,buff,0);
		
		for(j=0;j<len;j++)
		{
		   buff[j] = (char)(buff[j] ^ tmp[j%4]);  
		}
		
		/*for(j=0;j<len;j++)
		{
		    Log.d("iv","src: "+Integer.toHexString(buff[j]));
		}*/
		
		t1 = (char)(buff[len-1] & 0x5555);//0x3333);//0x0f0f); //    
		for(i=0;i<len;i++)
		{
		    t3 = (char)(buff[i] & 0x5555);//0x3333); //0x0f0f);
		    t2 = (char)(buff[i] & 0xaaaa);//0xcccc);//0xf0f0);
		    buff[i] = (char)(t1 | t2); 
		    t1 = t3;
		}
		/*
		for(j=0;j<len;j++)
		{
		    Log.d("iv","dest: "+Integer.toHexString(buff[j]));
		}
		*/
		dest = new String(buff);
			
		dest = base64_encode(buff,len*2).trim();
	/*
		for(j=0;j<dest.length();j++)
		{
		    Log.d("iv","destb64: "+Integer.toHexString(dest.charAt(j)));
		}
	*///	String aa = new String(dest).replace("/", "-");
		return dest;
		
	    }
	    
	    public String encode(String src)
	    {
	    	return encode3(src);
//	    	return src;
	    }
	    
	    private String decode3(String src)
	    {
		int i,len;
		char t1;
		char t2;
		char t3;
		String dest1;
		char tmp[] = new char[4];
		
		
	/*	
		for(int j=0;j<src.length();j++)
		{
		    Log.d("iv","src decode: "+Integer.toHexString(src.charAt(j)));
		}
	*/	
//		String aa = new String(src).replace("-", "/");
		
		dest1 = base64_decode(src);
	/*	
		for(int j=0;j<dest1.length();j++)
		{
		    Log.d("iv","de b64 decode: "+Integer.toHexString(dest1.charAt(j)));
		}
	*/	
		src = dest1;
		
		len = src.length();
		if (len ==0) return "";
		char[] buff = new char[len];
//		Log.d("iv","encode len : " + len);
		
		src.getChars(0,len,buff,0);
		t1 = (char)(buff[0] & 0x5555);//0x3333);//0x0f0f);
		
		for(i=len-1;i>=0;i--)
		{
		    t3 = (char)(buff[i] & 0x5555);//0x3333);//0x0f0f);
		    t2 = (char)(buff[i] & 0xaaaa);//0xcccc);//0xf0f0);
		    buff[i] = (char)(t1 | t2); 
		    t1 = t3;
		}
	/*	for( i =0;i<len ;i++)
		{
		    Log.d("iv", "char at " + i+ " " + (int)buff[i]);
		}
		
	*/	tmp[0] = 6;
		tmp[1] = 8;
		tmp[2] = 4;
		tmp[3] = 7;
		for(int j=0;j<len;j++)
		{
		   buff[j] = (char)(buff[j] ^ tmp[j%4]);  
		}
		String dest = new String(buff);
		return dest;
	    }
	    
	    public String decode(String src)
	    {
	    	return decode3(src);
//	    	return src;
	    }    
	    public String replaceChar(String targetStr)
	    {
	    	String returnStr = targetStr;

	    	if ("".equals(returnStr))
	    		return returnStr;

	    	try
	    	{
	    		returnStr = returnStr.replace("'","`");
	    		returnStr = returnStr.replace('"','〃');
	    		returnStr = returnStr.replace('a','b');
	    		returnStr = returnStr.replace("&","＆");
	    		returnStr = returnStr.replace("<","[");
	    		returnStr = returnStr.replace(">","]");
	    		returnStr = returnStr.replace("%","％");
	    		returnStr = returnStr.replace("#","＃");
	    		returnStr = returnStr.replace("@","＠");
	    		returnStr = returnStr.replace(",","，");
	    		returnStr = returnStr.replace("(","（");
	    		returnStr = returnStr.replace(")","）");
	    		
	    	}catch(Exception e){}
	    	return returnStr;
	    }
	
	public String sendSMS(String to, String message) //발신번호, 수신번호, 메세지
    {
    	//http://rest.ibizplus.co.kr:6001/sms/xml?id=plannplan&pwd=17014KZD99373ID1620P&from=025110216&to_country=82&to=1090760889&message=메세지입니다&report_req=1
    	String to_country = "82"; //국가코드(한국)
    	String report_req = "1";
    	String requestURL = "http://rest.ibizplus.co.kr:6001/sms/xml"; 
    	String id = "plannplan";
    	String pwd = "17014KZD99373ID1620P";
    	try
		{
    		String xmlData = new String();
			URL tmpUrl = new URL(requestURL+"?id="+id+"&pwd="+pwd+"&from=025110216&to_country="+to_country+"&to="+to+"&message="+URLEncoder.encode(message, "UTF-8")+"&report_req="+report_req+"&title=[웨딩큐]");
			URLConnection url_conn = null;
			url_conn = (HttpURLConnection)tmpUrl.openConnection(); //Http 커넥션 객체를 연다.
			
			String line = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(url_conn.getInputStream())); 

			while ((line = br.readLine()) != null)
			{
				xmlData += line.trim();
			}
			br.close();
			return xmlData;

		}
    	catch(Exception e543){
    		return e543.toString();
    	}
    }
	
	public static void fileMove(String inFileName, String outFileName) {
		try {
			FileInputStream fis = new FileInputStream(inFileName);
			FileOutputStream fos = new FileOutputStream(outFileName);

			int data = 0;
			while ((data = fis.read()) != -1) {
				fos.write(data);
			}
			fis.close();
			fos.close();

			// 복사한뒤 원본파일을 삭제함
			fileDelete(inFileName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fileDelete(String deleteFileName) {
		File I = new File(deleteFileName);
		I.delete();
	}
	
	public static void zipDirectory(String dir, String zipfile)
			throws IOException, IllegalArgumentException {
		// 디렉토리 존재 유무 체크 및 해당 파일 리스트를 가져오기 위하여 객체 생성
		File d = new File(dir);
		// 디렉토리 존재 유무 체크
		if (!d.isDirectory())
			throw new IllegalArgumentException("Not a directory:  " + dir);

		// 해당 경로의 파일을 배열로 가져옴
		String[] entries = d.list();

		// 파일 복사를 위한 버퍼
		byte[] buffer = new byte[4096];
		int bytesRead;
		// zip파일을 생성하기 위한 객체 생성
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
		// 해당경로의 파일들을 루프
		for (int i = 0; i < entries.length; i++) {
			File f = new File(d, entries[i]);
			if (f.isDirectory())
				continue;// Ignore directory

			// 스트림으로 파일을 읽음
			FileInputStream in = new FileInputStream(f); // Stream to read file

			// zip파일을 만들기 위하여 out객체에 write하여 zip파일 생성
			ZipEntry entry = new ZipEntry(f.getPath()); // Make a ZipEntry
			out.putNextEntry(entry); // Store entry
			while ((bytesRead = in.read(buffer)) != -1)
				out.write(buffer, 0, bytesRead);
			in.close();
		}
		out.close();
	}
	
	public static void createZip(String location, String filename) throws Exception
	{
		File f = new File(location);
		  
	    String path = location;
	    String files[] = f.list(); // f object 에 있는 파일목록
	    
	    // Create a buffer for reading the files
	    byte[] buf = new byte[1024];
	    
	    try {
	   
	        // Create the ZIP file
	        String outFilename = filename;
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(outFilename));
	    
	        // Compress the files
	        for (int i=0; i<files.length; i++) {
	    
	            FileInputStream in = new FileInputStream( path + "/" + files[i]);
	    
	            // Add ZIP entry to output stream.
	            out.putNextEntry(new ZipEntry(files[i])); // Zip 파일에 경로를 정하여 저장할수 있다.
	    
	            // Transfer bytes from the file to the ZIP file
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	    
	            // Complete the entry
	            out.closeEntry();
	            in.close();
	        }
	    
	        // Complete the ZIP file
	        out.close();
	    }catch(Exception e) {
	        throw e;
	    }
	}
	public static void deleteAllFiles(String path){
		
		File file = new File(path);
		//폴더내 파일을 배열로 가져온다.
		File[] tempFile = file.listFiles();

		if(tempFile.length >0){
			
			for (int i = 0; i < tempFile.length; i++) {
				
				if(tempFile[i].isFile()){
					tempFile[i].delete();
				}else{
					//재귀함수
					deleteAllFiles(tempFile[i].getPath());
				}
				tempFile[i].delete();
				
			}
			file.delete();
			
		}
		
	}































}