package tsn_java_poi_docx;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class JavaApplication1 {

    public static void main(String[] args) throws Exception {
        String dir = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                + System.getProperty("file.separator");
        XWPFDocument doc = new XWPFDocument(OPCPackage.open(dir + "input.docx"));
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("user")) {
                        text = text.replace("user", "T.S.N.");
                        r.setText(text, 0);
                    }
                }
            }
        }
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text != null && text.contains("user")) {
                                text = text.replace("user", "T.S.N.");
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(dir + "output.docx");
        doc.write(fos);
        fos.close();
    }
}
