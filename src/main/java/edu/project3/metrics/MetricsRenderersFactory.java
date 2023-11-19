package edu.project3.metrics;

import edu.project3.metrics.renderers.AdocRenderer;
import edu.project3.metrics.renderers.MarkdownRenderer;
import edu.project3.metrics.renderers.Renderer;
import edu.project3.types.Format;
import java.util.HashMap;

public class MetricsRenderersFactory {
    private final HashMap<Integer, Renderer> renderers = new HashMap<>();

    public Renderer getRenderer(Format renderFormat) {
        Renderer renderer = renderers.get(renderFormat.ordinal());
        if (renderer == null) {
            renderer = switch (renderFormat) {
                case ADOC -> new AdocRenderer();
                case MARKDOWN -> new MarkdownRenderer();
            };
            renderers.put(renderFormat.ordinal(), renderer);
        }
        return renderer;
    }
}
