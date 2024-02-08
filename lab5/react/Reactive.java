package react;

import java.awt.Component;

public class Reactive<CompType extends Component, HookType> {
    public CompType comp;
    public HookType hook;
    Renderable<CompType, HookType> renderer;
    Clearable<CompType> clearer;

    
    public Reactive(CompType comp, HookType hook, Renderable<CompType, HookType> renderable,
            Clearable<CompType> clearable) {
        this.comp = comp;
        this.renderer = renderable;
        this.hook = hook;
        this.clearer = clearable;
        renderable.renderer(comp, hook);
        comp.revalidate();
        // comp.repaint();
    }
    public Reactive(Reactive<CompType,HookType> Rcomp, HookType hook, Renderable<CompType, HookType> renderable,
            Clearable<CompType> clearable) {
        this.comp = Rcomp.comp;
        this.renderer = renderable;
        this.hook = hook;
        this.clearer = clearable;
        renderable.renderer(comp, hook);
        comp.revalidate();
        comp.repaint();
    }
    public void setRenderable(Renderable<CompType, HookType> renderable){
        this.renderer = renderable;
        renderable.renderer(comp, hook);
    }
    public void setClearer(Clearable<CompType> clearable) {
        this.clearer = clearable;
        renderer.renderer(comp, hook);
    }
    public void setHook(HookType newHook) {
        hook = newHook;
        clearer.clearer(comp);
        renderer.renderer(comp, hook);
    }
    
}