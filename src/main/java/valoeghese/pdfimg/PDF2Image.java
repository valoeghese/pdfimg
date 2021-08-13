package valoeghese.pdfimg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDF2Image {
	public static void main(String[] args) throws IOException {
		PDDocument doc = PDDocument.load(new File(args[0]));
		PDFRenderer renderer = new PDFRenderer(doc);
		final File folder = new File("output");
		folder.mkdir();

		for (int page = 0; page < doc.getNumberOfPages(); ++page) {
			System.out.println("Rendering page " + (page+1));
			BufferedImage img = renderer.renderImageWithDPI(page, 300, ImageType.RGB);
			System.out.println("Writing page " + (page+1));
			ImageIO.write(img, "png", new File(folder, args[0] + "-page" + (page+1) + ".png"));
		}
	}
}
