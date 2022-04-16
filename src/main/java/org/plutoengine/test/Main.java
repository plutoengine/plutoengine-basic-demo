package org.plutoengine.test;

import org.lwjgl.opengl.GL33;
import org.plutoengine.PlutoApplication;
import org.plutoengine.graphics.ImmediateFontRenderer;
import org.plutoengine.graphics.RectangleRenderer2D;
import org.plutoengine.libra.paint.LiPaint;
import org.plutoengine.libra.text.shaping.TextStyleOptions;
import org.plutoengine.math.ProjectionMatrix;
import org.plutoengine.shader.uniform.auto.AutomaticUniforms;
import org.plutoengine.util.color.Color;

public class Main extends PlutoApplication
{
    public static void main(String[] args)
    {
        var app = new Main();
        var cfg = new PlutoApplication.StartupConfig();
        cfg.vsync(1);
        app.run(args, cfg);
    }

    @Override
    protected Class<?> getMainModule()
    {
        return TestMod.class;
    }

    @Override
    protected void loop()
    {
        GL33.glEnable(GL33.GL_BLEND);
        GL33.glBlendFunc(GL33.GL_SRC_ALPHA, GL33.GL_ONE_MINUS_SRC_ALPHA);

        var projection = ProjectionMatrix.createOrtho2D(this.display.getWidth(), this.display.getHeight());
        AutomaticUniforms.VIEWPORT_PROJECTION.fire(projection);


        // Do any sort of rendering here and place your assets in the `mods` directory

        RectangleRenderer2D.draw()
                           .at(5, 5, 64, 64)
                           .texture(TestMod.plutoLogo)
                           .flush();

        var style = new TextStyleOptions(50)
            .setHorizontalAlign(TextStyleOptions.TextAlign.CENTER)
            .setVerticalAlign(TextStyleOptions.TextAlign.CENTER)
            .setPaint(LiPaint.solidColor(Color.WHITE));

        ImmediateFontRenderer.drawString(this.display.getWidth() / 2.0f, this.display.getHeight() / 2.0f, "Welcome to Pluto!", TestMod.font, style);
    }
}
