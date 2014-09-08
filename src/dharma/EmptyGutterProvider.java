package dharma;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.TextAnnotationGutterProvider;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorFontType;

import org.jetbrains.annotations.Nullable;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class EmptyGutterProvider implements TextAnnotationGutterProvider {
    private static int minWidth;
    private static int leftMargin;

    static {
        calculateWidthAndMargin(-1);
    }

    public static boolean enabled = false;

    private static void calculateWidthAndMargin(int idealWidth) {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        if (idealWidth <= 0) {
            minWidth = screenWidth * 2 / 3;
        } else {
            minWidth = idealWidth;
        }

        leftMargin = (int) ((screenWidth - minWidth) / 2D);
    }

    @Nullable
    public String getLineText(int line, Editor ed) {
        if (enabled && line == 0) {
            int widthOfIdealEditingArea = idealSizeFromRightMargin(ed);
            calculateWidthAndMargin(widthOfIdealEditingArea);
            return calcNewGutter(ed);
        }
        return "";
    }

    private int idealSizeFromRightMargin(Editor ed) {
        int rightMargin = ed.getSettings().getRightMargin(ed.getProject());
        return widthOfEmptyChar(ed) * (rightMargin + 8);
    }

    private String calcNewGutter(Editor ed) {
        JComponent ec = ed.getComponent();
        if (ec.getWidth() < minWidth) {
            return "";
        }

        Point p = ec.getLocation();
        SwingUtilities.convertPointToScreen(p, ec);
        int w = leftMargin - p.x;

        int chars = w / widthOfEmptyChar(ed);

        // create empty string with `chars` size
        String s = "";
        for (int i = 0; i < chars; i++) {
            s += " ";
        }
        return s;
    }

    private int widthOfEmptyChar(Editor ed) {
        Font font = ed.getColorsScheme().getFont(EditorFontType.PLAIN);
        FontMetrics fm = ed.getContentComponent().getFontMetrics(font);
        return fm.charWidth(' ');
    }

    @Nullable
    public String getToolTip(int line, Editor editor) {
        return null;
    }

    public EditorFontType getStyle(int line, Editor editor) {
        return null;
    }

    @Nullable
    public ColorKey getColor(int line, Editor editor) {
        return null;
    }

    @Nullable
    public Color getBgColor(int line, Editor editor) {
        return null;
    }

    public List<AnAction> getPopupActions(int line, Editor editor) {
        return null;
    }

    public void gutterClosed() {
    }
}