import java.io.* ;
import java.net.* ;


class updater{
	public static void main(String args[]){
    
    String version_info=loadversion("version.txt");
    String get_version=tcp_text("check_ip");
    System.out.println("Server now version:"+get_version);
    if(  !(version_info.equals(get_version))  )
    {
      System.out.println("Server Update Start");
      String recive=tcp_text("end_flag");
      System.out.println("get end_flag="+recive);

      if("end_ping".equals(recive))
      {
        System.out.println("Restart Server");
        String hoge=tcp_text("last_ping");

        System.out.println(hoge);
        TCPserver tcpserver=new TCPserver();
        String[] arg={"hoge","hoge"};
        tcpserver.main(arg);
      }
    }
    else
    {
      System.out.println("up to date!!");
    }

  }
  
  public static String tcp_text(String data)
  {
    try{
      String host="127.0.0.1";
      //new socket
			Socket s = new Socket(host, 6005);
      //set stream
      OutputStream osStr = s.getOutputStream();
      InputStream is = s.getInputStream();

      //output server
      osStr.write( data.getBytes() , 0 , data.length() );

      for(int i=0;i<1000;i++){;};
      //receve
      byte[] inputBuff = new byte[512];
      int recvByteLength = is.read(inputBuff);
      String buff = new String(inputBuff , 0 , recvByteLength);

      s.close();
      //return receive;
      return buff;
    }
		catch(Exception e){
			return e.toString();
    }
  }


  //ファイルからversion情報を読み取る
  public static String loadversion(String filepass)
   {
     String str="";
     try{
       File file = new File(filepass);
       if (checkBeforeReadfile(file)){
       BufferedReader br = new BufferedReader(new FileReader(file));
       str=br.readLine();
       System.out.println("lead version is "+str);
       br.close();
      
      }else{System.out.println("File not found");}
    }catch(FileNotFoundException e){
      System.out.println(e);
    }catch(IOException e){
      System.out.println(e);
    }
    
    return str;
  }
  
  //ファイルチェッカー
  public static boolean checkBeforeReadfile(File file)
  {
    if (file.exists()){if (file.isFile() && file.canRead()){return true;}}return false;
  }

}