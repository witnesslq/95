package com.dhcc.bussiness.sxydidc.qrcode;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {
	private static final int BLACK = 0xff000000; 
    private static final int WHITE = 0xFFFFFFFF;
    
    private QRCode() {  
    }
    
    private static final QRCode instance = new QRCode();
    public static QRCode getInstance() {  
        return instance;
    }
    
    public void generateQRCode(String path, String assetsName, String params, String logo, int width, int height) {
    	try {
    		File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            
            String fileName = assetsName.concat(".png");
            path = path.concat(fileName);
            file = new File(path);
            if (!file.exists()) {
            	file.createNewFile();
            }
            
            Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            
            BitMatrix bitMatrix = new MultiFormatWriter().encode(params, BarcodeFormat.QR_CODE, width, height, hints);
            writeToFile(bitMatrix, "png", file);
            
            String imagePath = file.getPath();
            BufferedImage image = ImageIO.read(file);
            int logoW = image.getWidth() / 5;
            int logoH = image.getHeight() / 5;
            int logoX = (image.getWidth() - logoW) / 2;
            int logoY = (image.getHeight() - logoH) / 2;
            Graphics2D graphics = image.createGraphics();
            graphics.drawImage(this.getLogoImg(logo), logoX, logoY, logoW, logoH, null);
            graphics.dispose(); 
            ImageIO.write(image, imagePath.substring(imagePath.lastIndexOf(".") + 1), new File(imagePath));
    	} catch (IOException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private BufferedImage getLogoImg(String content) {
    	int w = 80;  
        int h = 80;
        Font font = new Font("Serif", Font.BOLD, 44);
        
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fill(new RoundRectangle2D.Float(0, 0, w, h, 30, 30));
        graphics2D.setPaint(Color.BLACK);
        graphics2D.setFont(font);
        
        FontRenderContext context = graphics2D.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (w - bounds.getWidth()) / 2;
        double y = (h - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        graphics2D.drawString(content, (int)x, (int)baseY);
        
        return image;
    }
    
    public void parseQRCode(String imgPath) {
    	try {
			BufferedImage image = ImageIO.read(new File(imgPath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
            
            System.out.println("[QRCode : " + result.getText() + "]");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
    }
    
    private void writeToFile(BitMatrix matrix, String format, File file) throws Exception {
    	int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
            }
        }
        ImageIO.write(image, format, file);
    }
}