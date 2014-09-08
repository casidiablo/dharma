package dharma;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.editor.ex.EditorGutterComponentEx;

import org.jetbrains.annotations.NotNull;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class EditorListener implements EditorFactoryListener {
    public void editorCreated(@NotNull EditorFactoryEvent event) {
        final Editor ed = event.getEditor();
        ed.getGutter().registerTextAnnotation(new EmptyGutterProvider());

        ed.getContentComponent().addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                ((EditorGutterComponentEx) ed.getGutter()).revalidateMarkup();
            }

            public void componentMoved(ComponentEvent e) {
                ((EditorGutterComponentEx) ed.getGutter()).revalidateMarkup();
            }

            public void componentShown(ComponentEvent e) {
            }

            public void componentHidden(ComponentEvent e) {
            }
        });
    }

    public void editorReleased(@NotNull EditorFactoryEvent event) {
    }
}