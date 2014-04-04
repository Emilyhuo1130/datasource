package org.zhang.tuils.lang;



import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Test {
	public static void main(String[] args) throws Exception {
		String s = "aaaaaaa aaaaaaaaa aaaa aaaddddddddddd aaaaaaaaaaaaaaaaa "
				+ "aadddddddddddd dddddddddddssss ssaaaaaaaaa aaaaaaaa "
				+ "aaaaaaa aaaaaaaaa aaaa aaaddddddddddd aaaaaaa aaaaaaaaa aaaa aaadddd "
				+ "ddddddd aaaaaaa aaaaaaaaa aaaa aaaddddddddddd ";
		String filePath = "C:/Users/0064/Desktop/test" + ".png";
		File imageFile = new File(filePath);
        if (!imageFile.exists()) {
            imageFile.createNewFile();
            Test.createImage(s, imageFile, true);
        }
        //System.out.println(Test.check(s));
	}

	public static String check(String s) {
		StringBuilder sb = new StringBuilder(s);
		int count = 0;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '\n') {
				count = 0;
			} else if (count == 10) {
				sb.insert(i, "\n");
				count = 0;
			} else {
				count++;
			}
		}
		return sb.toString();
	}

	public static void createImage(String str, File outFile, boolean waterMark)
			throws Exception {

		int width = 500;
		int height = 500;
		Font font = new Font("handelgothic_regular", Font.PLAIN, 12);
		BufferedImage image = new BufferedImage(Test.check(str).length(), height,
				BufferedImage.TYPE_INT_BGR);

		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.setFont(font);
		g.drawString(Test.check(str), 0, font.getSize());
		g.dispose();
		ImageIO.write(image, "png", outFile);
		if (waterMark) {
			pressImage(outFile, null);
		}
	}
	
	private final static void pressImage(File targetImg, Integer degree) {
		OutputStream os = null;
		try {

			Image srcImg = ImageIO.read(targetImg);

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

			Graphics2D g = buffImg.createGraphics();
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			if (null != degree) {
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2,
						(double) buffImg.getHeight() / 2);
			}

			ImageIcon imgIcon = new ImageIcon("C:/Users/Public/Pictures/Sample Pictures/waterMark.jpg");

			Image img = imgIcon.getImage();

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.5f));

			g.drawImage(img, 0, 0, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();

			ImageIO.write(buffImg, "png", targetImg);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}