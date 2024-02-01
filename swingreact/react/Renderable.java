package react;

import java.awt.Component;

public interface Renderable<CompType extends Component, HookType> {

    void renderer(CompType comp, HookType hook);
}
