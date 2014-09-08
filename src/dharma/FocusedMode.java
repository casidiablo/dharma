package dharma;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.EditorFactory;

public class FocusedMode extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        EmptyGutterProvider.enabled = !EmptyGutterProvider.enabled;
        EditorFactory.getInstance().refreshAllEditors();
    }
}