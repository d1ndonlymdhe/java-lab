package react;

import java.awt.Component;

public interface Clearable<CompType extends Component> {
    public void clearer(CompType comp);
}
