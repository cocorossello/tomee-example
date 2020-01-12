package com.example;

import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;
import java.io.IOException;

@ResourceDependency(library = "javax.faces", name = "jsf.js")
public class HeadRenderer extends Renderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        context.getResponseWriter().startElement("head", component);
    }

    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        // NOOP.
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        for (UIComponent resource : context.getViewRoot().getComponentResources(context, "head")) {
            resource.encodeAll(context);
        }

        context.getResponseWriter().endElement("head");
    }

}