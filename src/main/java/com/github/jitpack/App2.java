package com.github.jitpack;




/**
 * Hello world!
 *
 */
public class App2
{
    public static void main(String[] args)
    {
        System.out.println(new App().greet("world"));
    }

    public String greet(String name) {
        return "Hello " + name;
    }





public static void deleteAllFiles(String path){
		
		File file = new File(path);
		
		File[] tempFile = file.listFiles();

		if(tempFile.length >0){
			
			for (int i = 0; i < tempFile.length; i++) {
				
				if(tempFile[i].isFile()){
					tempFile[i].delete();
				}else{
				
					deleteAllFiles(tempFile[i].getPath());
				}
				tempFile[i].delete();
				
			}
			file.delete();
			
		}
		
	}























}
