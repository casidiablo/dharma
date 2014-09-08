package dharma;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorFactoryListener;

import org.jetbrains.annotations.NotNull;

public class Focused implements ApplicationComponent {

    private EditorFactoryListener li;

    public void initComponent() {
        System.out.println("Focused.initComponent()");
        li = new EditorListener();
        Disposable disposable = new Disposable() {
            @Override
            public void dispose() {
                li = null;
            }
        };

        EditorFactory.getInstance().addEditorFactoryListener(li, disposable);
    }

    public void disposeComponent() {

    }

    @NotNull
    public String getComponentName() {
        return "Focused";
    }
}