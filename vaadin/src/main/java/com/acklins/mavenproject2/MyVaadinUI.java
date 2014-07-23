package com.acklins.mavenproject2;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.ChartClickEvent;
import com.vaadin.addon.charts.ChartClickListener;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.FilesystemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import elemental.html.File;
import java.nio.file.Files;
import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.acklins.mavenproject2.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
	final VerticalLayout layout = new VerticalLayout();
	layout.setMargin(true);
	setContent(layout);

	Button button = new Button("Click Me");
	button.addClickListener(new Button.ClickListener() {
	    public void buttonClick(ClickEvent event) {
		layout.addComponent(new Label("Thank you for clicking"));
	    }
	});
	layout.addComponent(button);

	// FilesystemContainer docs = new FilesystemContainer(new Files(""));
	// ComboBox docList = new ComboBox("Documents");
	// setContent(docList);

	Chart chart = new Chart();
	chart.getConfiguration().addSeries(new ListSeries(1, 2, 3));
	layout.addComponent(chart);

	chart.addChartClickListener(new ChartClickListener() {
	    @Override
	    public void onClick(ChartClickEvent event) {
		Notification.show("Clicked on " + event.getxAxisValue());
	    }

	});

    }

}
