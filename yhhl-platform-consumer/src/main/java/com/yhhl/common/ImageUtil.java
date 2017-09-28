package com.yhhl.common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.yhhl.product.controller.ProductsController;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private Logger log = Logger.getLogger(ProductsController.class);

	private static String DEFAULT_PREVFIX = "thumb_";
	private static Boolean DEFAULT_FORCE = false;// 建议该值为false

	/**
	 * <p>
	 * Title: thumbnailImage
	 * </p>
	 * <p>
	 * Description: 根据图片路径生成缩略图
	 * </p>
	 * 
	 * @param imagePath
	 *            原图片路径
	 * @param w
	 *            缩略图宽
	 * @param h
	 *            缩略图高
	 * @param prevfix
	 *            生成缩略图的前缀
	 * @param force
	 *            是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
	 */
	public void thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force) {
		File imgFile = new File(imagePath);
		if (imgFile.exists()) {
			try {
				BufferedImage image = ImageIO.read(new File(imagePath));
				Builder<BufferedImage> builder = null;
				if (!force) {
					// 根据原图与要求的缩略图比例，找到最合适的缩略图比例
					int imageWidth = image.getWidth();  
					int imageHeitht = image.getHeight();
					if ((float)h / w != (float)imageWidth / imageHeitht) {  
					    if (imageWidth > imageHeitht) {  
					        image = Thumbnails.of(imagePath).outputQuality(0.8f).height(h).asBufferedImage();  
					    } else {  
					        image = Thumbnails.of(imagePath).outputQuality(0.8f).width(w).asBufferedImage();  
					    }  
					    builder = Thumbnails.of(image).outputQuality(0.8f).sourceRegion(Positions.CENTER, w, h).size(w, h);  
					} else {  
					    builder = Thumbnails.of(image).outputQuality(0.8f).size(w, h);  
					}  
				}
				
				String p = imgFile.getPath();
				// 将图片保存在原目录并加上前缀
				File smallImg = new File(
						p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prevfix + imgFile.getName().substring(2));
				builder.outputFormat("jpg").toFile(smallImg);
				log.debug("缩略图在原路径下生成成功");
			} catch (IOException e) {
				log.error("generate thumbnail image failed.", e);
			}
		} else {
			log.warn("the image is not exist.");
		}
	}
	
	public String getFilePath(String path){
		File file = new File(path);
		return file.getParentFile().getParentFile().getParentFile().getAbsolutePath();
	}

	public static void main(String[] args) {
		new ImageUtil().thumbnailImage("C:/Users/cm/Desktop/我的页面/images/girlNoImg.jpg", 100, 150, DEFAULT_PREVFIX,
				DEFAULT_FORCE);
	}
}
