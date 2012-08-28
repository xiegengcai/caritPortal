package cn.com.carit.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 按比例缩放图片帮助类
 * @author <a href="mailto:xiegengcai@gmail.com">Xie Gengcai</a>
 * 2012-7-11
 */
public class ImageUtils {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private Image srcImage = null;
	private File srcFile = null;
	private File destFile = null;
	private String fileSuffix = null;

	private int imageWidth = 0;
	private int imageHeight = 0;

	public ImageUtils(String fileName) throws Exception {
		this(new File(fileName));
	}

	public ImageUtils(File fileName) throws Exception {
		File _file = fileName;
//		_file.setReadOnly();
		this.srcFile = _file;
		this.fileSuffix = _file.getName().substring(
				(_file.getName().indexOf(".") + 1), (_file.getName().length()));
		this.destFile = new File(this.srcFile.getPath().substring(0,
				(this.srcFile.getPath().lastIndexOf(".")))
				+ "_thumb." + this.fileSuffix);
		srcImage = ImageIO.read(_file);
		// 得到图片的原始大小， 以便按比例压缩。
		imageWidth = srcImage.getWidth(null);
		imageHeight = srcImage.getHeight(null);
	}

	/**
	 * 强制压缩/放大图片到固定的大小
	 * 
	 * @param w
	 *            int 新宽度
	 * @param h
	 *            int 新高度
	 * @throws IOException
	 */
	public void resize(int w, int h) {
		// 得到合适的压缩大小，按比例。
		if (imageWidth >= imageHeight) {
			h = (int) Math.round((imageHeight * w * 1.0 / imageWidth));
		} else {
			w = (int) Math.round((imageWidth * h * 1.0 / imageHeight));
		}

		// 构建图片对象
		BufferedImage _image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		// 绘制缩小后的图
		_image.getGraphics().drawImage(srcImage, 0, 0, w, h, null);
		// 输出到文件流
		FileOutputStream out=null;
		try {
			out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(_image);
			out.flush();
		} catch (FileNotFoundException e) {
			logger.error("dest file not found.", e);
		} catch (ImageFormatException e) {
			logger.error("ImageF ormat Mismatch.", e);
		} catch (IOException e) {
			logger.error("write src image file error.", e);
		} finally {
			if (out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("close OutputStream error.", e);
				}
			}
		}


	}

	public static void main(String[] args) throws Exception {
		ImageUtils imgUtils = new ImageUtils("D:/market/Sina Weibo.png");
		imgUtils.resize(30, 30);
	}

}
