package com.example.util;

import com.luciad.imageio.webp.WebPWriteParam;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageConverter {

    private void convertJpegToWebpLossless() throws Exception {
        BufferedImage image = ImageIO.read(new File("/home/gc/sample.jpg"));

        ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();

        WebPWriteParam writeParam = new WebPWriteParam(writer.getLocale());
        //Notify encoder to consider WebPWriteParams
        writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        //Set lossless compression
        writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSLESS_COMPRESSION]);

        // Save the image
        writer.setOutput(new FileImageOutputStream(new File("/home/gc/sample.webp")));
        writer.write(null, new IIOImage(image, null, null), writeParam);
    }
}
