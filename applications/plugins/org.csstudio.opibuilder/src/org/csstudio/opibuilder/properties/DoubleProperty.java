package org.csstudio.opibuilder.properties;

import org.csstudio.opibuilder.properties.support.DoublePropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.jdom.Element;


/**
 * A property, which is able to handle Double values.
 * 
 * @author Xihui Chen
 * 
 */
public final class DoubleProperty extends AbstractWidgetProperty {


	/**
	 * Lower border for the property value.
	 */
	private double min;

	/**
	 * Upper border for the property value.
	 */
	private double max;
	
	public DoubleProperty(String propId, String description,
			WidgetPropertyCategory category, double defaultValue) {
		super(propId, description, category, Double.valueOf(defaultValue));
		min = -Double.MAX_VALUE;
		max = Double.MAX_VALUE;
	}
	
	public DoubleProperty(String propId, String description,
			WidgetPropertyCategory category, double defaultValue,
			double min, double max) {
		super(propId, description, category, Double.valueOf(defaultValue));
		this.min = min;
		this.max = max;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object checkValue(final Object value) {
		if(value == null)
			return null;

		Double acceptedValue = null;

		// check type
		if (!(value instanceof Double)) {
			if (value instanceof Number) {
				acceptedValue = ((Number) value).doubleValue();
			} else {
				try {
					acceptedValue = Double.parseDouble(value.toString());
				} catch (NumberFormatException e) {
					acceptedValue = null;
				}
			}
		} else {
			acceptedValue = (Double) value;
		}

		// check borders
		if (acceptedValue != null) {
			if (acceptedValue > max) {
				acceptedValue = max;
			} else if (acceptedValue < min) {
				acceptedValue = min;
			}
		}

		return acceptedValue;
	}

	@Override
	protected PropertyDescriptor createPropertyDescriptor() {
		return new DoublePropertyDescriptor(prop_id, description);
	}

	@Override
	public void writeToXML(Element propElement) {
		propElement.setText(getPropertyValue().toString());
	}
	
	@Override
	public Object readValueFromXML(Element propElement) {
		return Double.parseDouble(propElement.getValue());
	}
}
