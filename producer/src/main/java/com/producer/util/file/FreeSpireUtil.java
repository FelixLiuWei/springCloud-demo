package com.producer.util.file;

public class FreeSpireUtil {
//    public static void main(String[] args) {
//
//        //加载Word文档
//        Document document = new Document();
//        document.loadFromFile("C:\\Users\\Administrator\\Desktop\\sample.docx");
//
//        //获取第一个section
//        Section section = document.getSections().get(0);
//
//        //调用insertHeaderAndFooter方法插入页眉页脚到第一个section
//        insertHeaderAndFooter(section);
//
//        //保存文档
//        document.saveToFile("output/HeaderAndFooter.docx", FileFormat.Docx);
//    }
//
//    private static void insertHeaderAndFooter(Section section) {
//
//        //分别获取section的页眉页脚
//        HeaderFooter header = section.getHeadersFooters().getHeader();
//        HeaderFooter footer = section.getHeadersFooters().getFooter();
//
//        //添加段落到页眉
//        Paragraph headerParagraph = header.addParagraph();
//
//        //插入图片到页眉的段落
//        DocPicture headerPicture = headerParagraph.appendPicture("C:\\Users\\Administrator\\Desktop\\Logo.png");
//        headerPicture.setHorizontalAlignment(ShapeHorizontalAlignment.Left);
//        headerPicture.setVerticalOrigin(VerticalOrigin.Top_Margin_Area);
//        headerPicture.setVerticalAlignment(ShapeVerticalAlignment.Bottom);
//
//        //添加文字到页眉的段落
//        TextRange text = headerParagraph.appendText("Demo of Spire.Doc");
//        text.getCharacterFormat().setFontName("Arial");
//        text.getCharacterFormat().setFontSize(10);
//        text.getCharacterFormat().setItalic(true);
//        headerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
//
//        //设置文字环绕方式
//        headerPicture.setTextWrappingStyle(TextWrappingStyle.Behind);
//
//        //设置页眉段落的底部边线样式
//        headerParagraph.getFormat().getBorders().getBottom().setBorderType(BorderStyle.Single);
//        headerParagraph.getFormat().getBorders().getBottom().setLineWidth(1f);
//
//        //添加段落到页脚
//        Paragraph footerParagraph = footer.addParagraph();
//
//        //添加Field_Page和Field_Num_Pages域到页脚段落，用于显示当前页码和总页数
//        footerParagraph.appendField("page number", FieldType.Field_Page);
//        footerParagraph.appendText(" of ");
//        footerParagraph.appendField("number of pages", FieldType.Field_Num_Pages);
//        footerParagraph.getFormat().setHorizontalAlignment(HorizontalAlignment.Right);
//
//        //设置页脚段落的顶部边线样式
//        footerParagraph.getFormat().getBorders().getTop().setBorderType(BorderStyle.Single);
//        footerParagraph.getFormat().getBorders().getTop().setLineWidth(1f);
//    }
}
