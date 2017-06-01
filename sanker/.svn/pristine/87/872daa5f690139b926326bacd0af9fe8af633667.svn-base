package com.sanker.service.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;


/**
 * 图像处理
 * @author Administrator
 *
 */
public class ImageUtils {
	
	public static String[] typeStrings = {"jpg", "jpeg", "png", "gif", "bmp"};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//原始图片
    	String  imageString = "F:\\servers\\apache-tomcat-6.0.14\\webapps\\newsSrv\\File\\ImageFiles\\2012-09-20\\27\\Koala.jpg";
    	//处理后的图片保存地址
    	String pathString = "d:/testImage";
        // 1-缩放图像：
        // 方法一：按比例缩放
//    	ImageUtils.scale(imageString, pathString + "/abc_scale.jpg", 240, "jpg");//测试OK
        // 方法二：按高度和宽度缩放
    	ImageUtils.scale2(imageString, pathString + "/abc_scale2.png", 89, 70, "png", false);//测试OK
	}
	
	/**
     * 缩放图像（按图片宽度等比例缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param needWidth 需要的图片的宽度
     * @param type 类型（jpg，png等）
     */
    public final static BufferedImage scale(String srcImageFile, String result, int needWidth, String type) {
    	BufferedImage tag = null;
    	if( needWidth > 0 && needWidth < 5000){
	        try {
	        	File file = new File(srcImageFile);
	        	if(file.exists()){
		            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
		            int width = src.getWidth(); // 得到源图宽
		            int height = src.getHeight(); // 得到源图长
		            double scale = Double.valueOf(needWidth) / Double.valueOf(width);
		            System.out.println(scale);
	//	            if (flag) {// 放大
		                width = (int)(width * scale);
		                height = (int)(height * scale);
	//	            } else {// 缩小
	//	                width = (int)(width / scale);
	//	                height = (int)(height / scale);
	//	            }
		            System.out.println(width);
		            System.out.println(height);
		            Image image = src.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		            tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		            Graphics g = tag.getGraphics();
		            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		            g.dispose();
		            ImageIO.write(tag, checkType(type), new File(result));// 输出到文件流
	        	}
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
        return tag;
    }
    
    /**
     * 缩放图像（按高度和宽度缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param height 缩放后的高度
     * @param width 缩放后的宽度
     * @param bb 比例不对时是否需要补白：true为补白; false为不补白;
     */
    public final static Image scale2(String srcImageFile, String result, int width, int height, String type, boolean bb) {
    	Image itemp = null;
        try {
            double ratiow = 0.0; // 缩放比例
            double ratioh = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            if(f.exists()){
	            BufferedImage bi = ImageIO.read(f);
	            itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	            // 计算比例
	            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
//	                if (bi.getHeight() > bi.getWidth()) {
//	                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
//	                } else {
//	                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
//	                }
	            	ratiow = (new Integer(width)).doubleValue() / bi.getWidth();
	            	ratioh = (new Integer(height)).doubleValue() / bi.getHeight();
	                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratiow, ratioh), null);
	                itemp = op.filter(bi, null);
	            }
	            if (bb) {//补白
	                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	                Graphics2D g = image.createGraphics();
	                g.setColor(Color.white);
	                g.fillRect(0, 0, width, height);
	                if (width == itemp.getWidth(null))
	                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
	                            itemp.getWidth(null), itemp.getHeight(null),
	                            Color.white, null);
	                else
	                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
	                            itemp.getWidth(null), itemp.getHeight(null),
	                            Color.white, null);
	                g.dispose();
	                itemp = image;
	            }
	            ImageIO.write((BufferedImage) itemp, checkType(type), new File(result));
//	            readImageStream(itemp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemp;
    }
    
    /**
     * 检查图片类型
     * @param type
     * @return
     */
    private static String checkType(String type){
    	String t = "png";
    	if(type != null){
    		for(String s: typeStrings){
    			if(type.toLowerCase().equals(s)){
    				t = type;
    				break;
    			}
    		}
    	}
    	return t;
    }
    
    public static byte[] readImageStream(Image img){
//    	ImageOutputStream iiStream = null;
    	ByteArrayOutputStream baos = null;
    	byte[] bal = null;
    	try {
//    		iiStream = ImageIO.createImageOutputStream(new ByteArrayOutputStream());
    		baos = new ByteArrayOutputStream();
    		ImageIO.write((BufferedImage)img, "png", baos);
			System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			if(baos != null){
				bal = baos.toByteArray();
//				for(int i = 0, n = bal.length; i< n; i++){
//					System.out.println(bal[i]);
//				}
			}
//			//一次读多个字节  
//			byte[] tempbytes = new byte[100];
//			int byteread = 0;
//			//读入多个字节到字节数组中，byteread为一次读入的字节数  
//			while ((byteread = iiStream.read(tempbytes)) != -1) {
//				System.out.write(tempbytes, 0, byteread);
//				System.out.println(byteread);
//			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return bal;
    }
    
    /** 
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。 
	 * @param fileName 文件的名 
	 */
	public static void readFileByBytes(String fileName) {
		File file = new File(fileName);
		InputStream in = null;
		try {
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			// 一次读一个字节  
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				System.out.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			//一次读多个字节  
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in = new FileInputStream(fileName);
			//读入多个字节到字节数组中，byteread为一次读入的字节数  
			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	} 

}
