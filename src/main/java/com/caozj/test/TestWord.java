package com.caozj.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class TestWord {

  // table \ charts
  public static void main(String[] args) throws InvalidFormatException, FileNotFoundException {
    String wordFile = "d://test.docx";
    int picWidth = 240;
    int picHeight = 150;
    int picType = XWPFDocument.PICTURE_TYPE_JPEG;
    XWPFParagraph paragraph = null;
    XWPFTable table = null;
    XWPFDocument doc = new XWPFDocument();
    paragraph = doc.createParagraph();
    doc.addPictureData(new FileInputStream(new File("F:\\test.bmp")), picType);
  }

}
