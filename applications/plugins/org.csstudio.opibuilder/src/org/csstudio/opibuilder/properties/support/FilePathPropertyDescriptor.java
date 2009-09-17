package org.csstudio.opibuilder.properties.support;


import org.csstudio.opibuilder.visualparts.FilePathCellEditor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * Descriptor for a property that has a value which should be edited with a path
 * cell editor.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * <p>
 * Example:
 * 
 * <pre>
 * IPropertyDescriptor pd = new ResourcePropertyDescriptor(&quot;surname&quot;, &quot;Last Name&quot;);
 * </pre>
 * 
 * </p>
 * 
 * @author Kai Meyer, Xihui Chen
 */
public class FilePathPropertyDescriptor extends TextPropertyDescriptor {
	
	/**
	 * The accepted file extensions.
	 */
	private String[] _fileExtensions;
	
	/**
	 * Creates an property descriptor with the given id and display name.
	 * 
	 * @param id
	 *            the id of the property
	 * @param displayName
	 *            the name to display for the property
	 * @param fileExtensions
	 * 			  The accepted file extensions
	 */
	public FilePathPropertyDescriptor(final Object id, final String displayName,
			final String[] fileExtensions) {
		super(id, displayName);
		_fileExtensions = fileExtensions;
		this.setLabelProvider(new PathLabelProvider());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CellEditor createPropertyEditor(final Composite parent) {
		CellEditor editor = new FilePathCellEditor(parent, _fileExtensions);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}
	
	/**
	 * A label provider for a IResource.
	 * 
	 * @author Kai Meyer
	 */
	private final class PathLabelProvider extends LabelProvider {
		/**
		 * {@inheritDoc}
		 */
		@Override
		public String getText(final Object element) {
			if (element instanceof IPath) {
				IPath path = (IPath) element;
				return path.toString();
			} else {
				return element.toString();
			}
		}

	}
}
