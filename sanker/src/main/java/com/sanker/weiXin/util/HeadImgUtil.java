package com.sanker.weiXin.util;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 头像util
 * @author 滕洁
 * @date 2016-12-30
 */
public class HeadImgUtil {
	
	public static String getImgFromUrl(String urlstr){
		 
        int num = urlstr.indexOf('/',8);
        int extnum = urlstr.lastIndexOf('.');
        String u = urlstr.substring(0,num);
        System.out.println(u);
        String ext = urlstr.substring(extnum+1,urlstr.length());
        try{
            long curTime = System.currentTimeMillis();
            Random random = new Random(100000000);
            String fileName = String.valueOf(curTime) + "_"
                    + random.nextInt(100000000) + ".jpg";
            System.out.println(fileName+"  "+ext);
            // 图片的路径
            String realPath = "C:/apache-tomcat-8.5.9/webapps/sanker/headImg/";
            
            URL url = new URL(urlstr);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("referer", u);       //通过这个http头的伪装来反盗链
            BufferedImage image = ImageIO.read(connection.getInputStream());
            FileOutputStream fout=new FileOutputStream(realPath+fileName);
            if("gif".equals(ext)||"png".equals("png"))
            {
                 ImageIO.write(image, ext, fout);
            }
            ImageIO.write(image, "jpg", fout);
            fout.flush();
            fout.close();
                   
            return fileName;
        }       
        catch(Exception e)
        {
            System.out.print(e.getMessage().toString());
        }
        return "";
    }
	public static void main(String[] args) {

        String strImg = getImageStr();  
        System.out.println(strImg);  
        generateImage(strImg);
	}
	
	
	 //图片转化成base64字符串  
    public static String getImageStr(){//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        String imgFile = "d://1483035658643_17944680.jpg";//待处理的图片  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    //base64字符串转化成图片  
    public static boolean generateImage(String imgStr){   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) //图像数据为空  
            return false;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = "d://222.jpg";//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
}
